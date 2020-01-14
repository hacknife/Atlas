package com.hacknife.atlas.ui.model.impl;

import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IAtlasModel;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AtlasModel extends BaseModel<IAtlasViewModel> implements IAtlasModel {
    public AtlasModel(IAtlasViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void loadMore(int page) {
        if (AtlasResource.get().page_url == null) {
            viewModel.atlas(new ArrayList<>());
            return;
        }
        HttpClient.create(Api.class)
                .url(String.format(AtlasResource.get().page_url, page))
                .map(Jsoup::parse)
                .map(JsoupHelper::parserAtlas)
                .map(JsoupHelper::atlasAtlas)
                .onErrorReturn(throwable -> new ArrayList<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Atlas>>(disposable) {
                    @Override
                    public void onNext(List<Atlas> atlases) {
                        viewModel.atlas(atlases);
                    }
                });
    }

    @Override
    public void refresh() {
        if (AtlasResource.get().page_url == null) {
            viewModel.refresh(new ArrayList<>());
            return;
        }
        HttpClient.create(Api.class)
                .url(String.format(AtlasResource.get().page_url, 1))
                .map(Jsoup::parse)
                .map(JsoupHelper::parserAtlas)
                .map(JsoupHelper::atlasAtlas)
                .onErrorReturn(throwable -> new ArrayList<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Atlas>>(disposable) {
                    @Override
                    public void onNext(List<Atlas> atlases) {
                        viewModel.refresh(atlases);
                    }
                });
    }
}
