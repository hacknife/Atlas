package com.hacknife.atlas.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.Target;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;

import org.jsoup.Jsoup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DownloadService extends Service {
    volatile static Object lock = new Object();
    List<Atlas> jobs;
    CompositeDisposable disposable;
    DownloadBinder binder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
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
                        jobs.addAll(downloadEvent.atlases);
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void initJob() {
        Observable.range(0, Integer.MAX_VALUE)
                .map(integer -> popAtlas())
                .doOnNext(atlas -> Log.v("dzq", "atlas:" + atlas.toString()))
                .doOnNext(atlas -> GlideApp.with(this).load(atlas.getCover()).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get())
                .filter(atlas -> atlas.getUrl() != null)
                .map(atlas -> new Images(atlas.getUrl(), new ArrayList<>()))
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Images>(disposable) {
                    @Override
                    public void onNext(Images source1) {
                        Observable.just(source1)
                                .flatMap(new Function<Images, Observable<Images>>() {
                                    @Override
                                    public Observable<Images> apply(Images images) {
                                        HttpClient.create(Api.class)
                                                .url(images.getNext())
                                                .map(Jsoup::parse)
                                                .map(JsoupHelper::parserImages)
                                                .retry(throwable -> true)
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
                                .subscribe(new Consumer<Images>(disposable) {
                                    @Override
                                    public void onNext(Images source2) {
                                        final int[] progress = {0};
                                        Observable.just(source2)
                                                .flatMap((Function<Images, Observable<String>>) images -> Observable.fromArray(images.getImages().toArray(new String[]{})))
                                                .doOnNext(s -> progress[0] = source2.getImages().indexOf(s))
                                                .map(s -> GlideApp.with(DownloadService.this).load(s).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get())
//                                                .retry(throwable -> true)

                                                .subscribe(new Consumer<File>(disposable) {
                                                    @Override
                                                    public void onNext(File s) {
                                                        Observable.just(s)
                                                                .subscribeOn(Schedulers.newThread())
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribe(file -> binder.onProgress(progress[0], source2.getImages().size()));

                                                        Log.v("dzq", "down url:  " + s.getPath());
                                                    }
                                                });
                                        jobs.remove(0);
                                        Observable.just(source2)
                                                .subscribeOn(Schedulers.newThread())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(file -> binder.onComplete());
                                    }
                                });


                    }
                })

        ;
    }

    private Atlas popAtlas() {
        synchronized (lock) {
            while (jobs.size() == 0) {
                try {
                    lock.wait(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (jobs.size() == 0) {
                stopSelf();
                return new Atlas(null, null, null);
            }
            return jobs.get(0);
        }
    }
}
