package com.hacknife.atlas.ui.model.impl;

import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IImageModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;

import org.jsoup.Jsoup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageModel extends BaseModel<IImageViewModel> implements IImageModel {
    public ImageModel(IImageViewModel viewModel) {
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
