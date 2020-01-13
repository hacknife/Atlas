package com.hacknife.atlas.ui.model.impl;

import android.util.Log;

import com.hacknife.atlas.app.AtlasApplication;
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
    public void loadMore(Images images) {
        HttpClient.create(Api.class)
                .url(images.getNext())
                .map(Jsoup::parse)
                .map(JsoupHelper::parserImages)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Images>(disposable) {
                    @Override
                    public void onNext(Images imgs) {
                        Log.i("dzq", "onNext: " + Thread.currentThread().getName());
                        images.getImages().addAll(imgs.getImages());
                        images.setNext(imgs.getNext());
                        if (images.getImages().size() >= AtlasApplication.PAGE_SIZE || imgs.getImages().size() == 0)
                            viewModel.loadMore(images);
                        else {
                            loadMore(images);
                        }
                    }
                });
    }
}
