package com.hacknife.atlas.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.text.style.ImageSpan;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.Target;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bean.ImageCollection;
import com.hacknife.atlas.bean.ImageLite;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.onlite.OnLiteFactory;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DownloadService extends Service {
    volatile static Object lock = new Object();
    List<Atlas> jobs;
    CompositeDisposable disposable;
    DownloadBinder binder;
    long lastDownImageTime = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.v("开启 DownloadService");
        jobs = new ArrayList<>();
        disposable = new CompositeDisposable();
        binder = new DownloadBinder() {
            @Override
            public List<Atlas> getAtlases() {
                return jobs;
            }
        };
        initJob();
        RxBus.toObservable(DownloadEvent.class)
                .subscribe(new Consumer<DownloadEvent>(disposable) {
                    @Override
                    public void onNext(DownloadEvent downloadEvent) {
                        for (Atlas atlas : downloadEvent.atlases) {
                            if (!jobs.contains(atlas)) {
                                jobs.add(atlas);
                            }
                        }
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                });
        RxBus.toObservable(Image.class)
                .subscribe(new Consumer<Image>(disposable) {
                    @Override
                    public void onNext(Image image) {
                        lastDownImageTime = System.currentTimeMillis();
                        Observable.just(image)
                                .map(image1 -> image1.getUrl())
                                .map(url -> GlideApp.with(DownloadService.this).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get())
                                .subscribe(new Consumer<File>(disposable) {
                                    @Override
                                    public void onNext(File file) {
                                        lastDownImageTime = System.currentTimeMillis();
                                        BitmapFactory.Options options = new BitmapFactory.Options();
                                        options.inJustDecodeBounds = true;
                                        BitmapFactory.decodeFile(file.getPath(), options);
                                        image.setWidth(options.outWidth);
                                        image.setHeight(options.outHeight);
                                        OnLiteFactory.create(ImageLite.class).insert(image);
                                        Logger.v(String.format("down url:%s , width:%d , height:%d", file.getPath(), options.outWidth, options.outHeight));

                                    }
                                });
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void initJob() {
        Observable.range(0, Integer.MAX_VALUE)
                .map(integer -> popAtlas())
                .doOnNext(atlas -> Logger.v("down atlas:"))
                .doOnNext(atlas -> Logger.json(atlas.toString()))
                .doOnNext(atlas -> GlideApp.with(this).load(atlas.getCover()).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get())
                .filter(atlas -> {
                    boolean ret = atlas.getUrl() != null;
                    if (!ret)
                        jobs.remove(0);
                    return ret;
                })
                .filter(atlas -> {
                    boolean ret = atlas.getCached() == null || (atlas.getCached() != 1);
                    if (!ret)
                        jobs.remove(0);
                    return ret;
                })
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Atlas>(disposable) {
                    @Override
                    public void onNext(Atlas atlasSource) {
                        Observable.just(atlasSource)
                                .map(atlas -> new ImageCollection(atlas.getUrl(), new ArrayList<>()))
                                .subscribe(new Consumer<ImageCollection>(disposable) {
                                    @Override
                                    public void onNext(ImageCollection source1) {
                                        Observable.just(source1)
                                                .flatMap(new Function<ImageCollection, Observable<ImageCollection>>() {
                                                    @Override
                                                    public Observable<ImageCollection> apply(ImageCollection images) {
                                                        HttpClient.create(Api.class)
                                                                .url(images.getNext())
                                                                .map(Jsoup::parse)
                                                                .map(JsoupHelper::parserImages)
                                                                .onErrorReturn(throwable -> new ImageCollection(images.getNext(), new ArrayList<>()))
                                                                .subscribe(imgs -> {
                                                                    images.setNext(StringHelper.link(images.getNext(), imgs.getNext()));
                                                                    images.getImages().addAll(imgs.getImages());
                                                                    if (imgs.getImages().size() == 0 || images.getNext() == null || images.getNext().length() == 0) {
                                                                    } else {
                                                                        apply(images);
                                                                    }
                                                                });
                                                        return Observable.just(images);
                                                    }
                                                })
                                                .subscribe(new Consumer<ImageCollection>(disposable) {
                                                    @Override
                                                    public void onNext(ImageCollection source2) {
                                                        final int[] progress = {0};
                                                        Observable.just(source2)
                                                                .flatMap((Function<ImageCollection, Observable<Image>>) images -> Observable.fromArray(images.getImages().toArray(new Image[]{})))
                                                                .subscribe(new Consumer<Image>(disposable) {
                                                                    @Override
                                                                    public void onNext(Image image) {
                                                                        Observable.just(image)
                                                                                .doOnNext(s -> progress[0] = source2.getImages().indexOf(s))
                                                                                .map(s -> GlideApp.with(DownloadService.this).load(s.getUrl()).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get())

                                                                                .subscribe(new Consumer<File>(disposable) {
                                                                                    @Override
                                                                                    public void onNext(File s) {
                                                                                        BitmapFactory.Options options = new BitmapFactory.Options();
                                                                                        options.inJustDecodeBounds = true;
                                                                                        BitmapFactory.decodeFile(s.getPath(), options);
                                                                                        image.setWidth(options.outWidth);
                                                                                        image.setHeight(options.outHeight);
                                                                                        OnLiteFactory.create(ImageLite.class).insert(image);
                                                                                        Observable.just(s)
                                                                                                .subscribeOn(Schedulers.newThread())
                                                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                                                .subscribe(file -> binder.onProgress(progress[0], source2.getImages().size()));
                                                                                        Logger.v(String.format("down url:%s , width:%d , height:%d", s.getPath(), options.outWidth, options.outHeight));
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                        jobs.remove(0);
                                                        Observable.just(source2)
                                                                .subscribeOn(Schedulers.newThread())
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribe(file -> binder.onComplete(atlasSource));
                                                        if (source2.getImages().size() > 0) {
                                                            atlasSource.setCached(1);
                                                            OnLiteFactory.create(AtlasLite.class).insert(atlasSource);
                                                            RxBus.post(new DownloadEvent(Arrays.asList(atlasSource)));
                                                        }
                                                    }
                                                });


                                    }
                                });

                    }
                })

        ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        Logger.v("关闭 DownloadService");
    }

    private Atlas popAtlas() {
        synchronized (lock) {
            while (jobs.size() == 0) {
                try {
                    lock.wait(1000 * 20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (jobs.size() == 0) {
                Logger.v("当前没有任务，准备关闭 DownloadService");
                stopSelf();
                return new Atlas(null, null, null);
            } else if (jobs.size() > 0) {
                Logger.v("有任务，不关闭 DownloadService");
            } else if (System.currentTimeMillis() - lastDownImageTime > 20 * 1000) {
                Logger.v("当前没有任务，准备关闭 DownloadService");
                stopSelf();
                return new Atlas(null, null, null);
            } else {
                Logger.v("有任务，不关闭 DownloadService");
            }
            return jobs.get(0);
        }
    }
}
