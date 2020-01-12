package com.hacknife.atlas.ui.model.impl;


import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.databinding.Image;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IImageViewerModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewerViewModel;

import org.jsoup.Jsoup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageViewerModel extends BaseModel<IImageViewerViewModel> implements IImageViewerModel {
    public ImageViewerModel(IImageViewerViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void loadMore(String url) {
        HttpClient.create(Api.class)
                .url(url)
                .map(Jsoup::parse)
                .doOnNext(System.out::println)
                .map(JsoupHelper::parserImages)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Images>(disposable){
                    @Override
                    public void onNext(Images images) {
                        viewModel.loadMore(images);
                    }
                });
    }
}
