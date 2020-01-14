package com.hacknife.atlas.ui.model.impl;

import android.util.Log;

import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IImageModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;

import org.jsoup.Jsoup;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageModel extends BaseModel<IImageViewModel> implements IImageModel {
    public ImageModel(IImageViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void loadMore(Images images) {
        HttpClient.create(Api.class)
                .url(images.getNext())
                .map(Jsoup::parse)
                .map(JsoupHelper::parserImages)
                .onErrorReturn(throwable -> new Images(null, new ArrayList<>()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Images>(disposable) {
                    @Override
                    public void onNext(Images imgs) {
                        Log.i("dzq", "onNext: " + imgs.toString());
                        images.getImages().addAll(imgs.getImages());
                        images.setNext(StringHelper.link(images.getNext(), imgs.getNext()));
                        if (images.getImages().size() >= AppConfig.PAGE_SIZE || imgs.getImages().size() == 0 || images.getNext() == null || images.getNext().length() == 0)
                            viewModel.loadMore(images);
                        else {
                            loadMore(images);
                        }
                    }
                });
    }

    @Override
    public void refresh(Images images) {
        HttpClient.create(Api.class)
                .url(images.getNext())
                .map(Jsoup::parse)
                .map(JsoupHelper::parserImages)
                .onErrorReturn(throwable -> new Images(null, new ArrayList<>()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Images>(disposable) {
                    @Override
                    public void onNext(Images imgs) {
                        Log.i("dzq", "onNext: " + imgs.toString());
                        images.getImages().addAll(imgs.getImages());
                        images.setNext(StringHelper.link(images.getNext(), imgs.getNext()));
                        if (images.getImages().size() >= AppConfig.PAGE_SIZE || imgs.getImages().size() == 0 || images.getNext() == null || images.getNext().length() == 0)
                            viewModel.refresh(images);
                        else {
                            refresh(images);
                        }
                    }
                });
    }
}
