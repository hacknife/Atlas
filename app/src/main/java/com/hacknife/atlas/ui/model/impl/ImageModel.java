package com.hacknife.atlas.ui.model.impl;

import android.util.Log;

import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bean.ImageCollection;
import com.hacknife.atlas.bean.ImageLite;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IImageModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.onlite.OnLiteFactory;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageModel extends BaseModel<IImageViewModel> implements IImageModel {
    public ImageModel(IImageViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void loadMore(ImageCollection images) {
        HttpClient.create(Api.class)
                .url(images.getNext())
                .map(Jsoup::parse)
                .map(JsoupHelper::parserImages)
                .onErrorReturn(throwable -> new ImageCollection(null, new ArrayList<>()))
                .doOnNext(imageCollection -> {
                    ImageLite lite = OnLiteFactory.create(ImageLite.class);
                    for (Image image : imageCollection.getImages()) {
                        List<Image> imgs = lite.select(new Image(image.getUrl()));
                        if (imgs.size() > 0) {
                            image.setWidth(imgs.get(0).getWidth());
                            image.setHeight(imgs.get(0).getHeight());
                        }
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageCollection>(disposable) {
                    @Override
                    public void onNext(ImageCollection imgs) {
                        images.getImages().addAll(imgs.getImages());
                        images.setNext(StringHelper.link(images.getNext(), imgs.getNext(),true));
                        if (images.getImages().size() >= AppConfig.PAGE_SIZE || imgs.getImages().size() == 0 || images.getNext() == null || images.getNext().length() == 0)
                            viewModel.loadMore(images);
                        else {
                            loadMore(images);
                        }
                    }
                });
    }

    @Override
    public void refresh(ImageCollection images) {
        HttpClient.create(Api.class)
                .url(images.getNext())
                .map(Jsoup::parse)
                .map(JsoupHelper::parserImages)
                .onErrorReturn(throwable -> new ImageCollection(null, new ArrayList<>()))
                .doOnNext(imageCollection -> {
                    ImageLite lite = OnLiteFactory.create(ImageLite.class);
                    for (Image image : imageCollection.getImages()) {
                        List<Image> imgs = lite.select(new Image(image.getUrl()));
                        if (imgs.size() > 0) {
                            image.setWidth(imgs.get(0).getWidth());
                            image.setHeight(imgs.get(0).getHeight());
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageCollection>(disposable) {
                    @Override
                    public void onNext(ImageCollection imgs) {
                        images.getImages().addAll(imgs.getImages());
                        images.setNext(StringHelper.link(images.getNext(), imgs.getNext(),true));
                        if ((images.getImages().size() >= AppConfig.PAGE_SIZE && (!images.cached())) || imgs.getImages().size() == 0 || images.getNext() == null || images.getNext().length() == 0)
                            viewModel.refresh(images);
                        else {
                            refresh(images);
                        }
                    }
                });
    }
}
