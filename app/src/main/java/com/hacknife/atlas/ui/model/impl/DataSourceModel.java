package com.hacknife.atlas.ui.model.impl;

import android.util.Log;

import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.bean.AtlasLiteLite;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IDataSourceModel;
import com.hacknife.atlas.ui.viewmodel.IDataSourceViewModel;
import com.hacknife.onlite.OnLiteFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DataSourceModel extends BaseModel<IDataSourceViewModel> implements IDataSourceModel {
    public DataSourceModel(IDataSourceViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh() {
        HttpClient.create(Api.class)
                .atlas()
                .doOnNext(atlasLites -> {
                    List<AtlasLite> list = OnLiteFactory.create(AtlasLiteLite.class).select(null);
                    for (AtlasLite atlas : list) {
                        if (atlas.checked()) {
                            int id = atlas.getId();
                            for (AtlasLite lite : atlasLites) {
                                if (lite.getId() == id) {
                                    lite.setChecked(1);
                                    break;
                                }
                            }
                            return;
                        }
                    }
                })
                .doOnNext(atlasLites -> OnLiteFactory.create(AtlasLiteLite.class).delete(null))
                .doOnNext(atlasLites -> OnLiteFactory.create(AtlasLiteLite.class).insert(atlasLites))
                .onErrorReturn(throwable -> OnLiteFactory.create(AtlasLiteLite.class).select(null))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AtlasLite>>(disposable) {
                    @Override
                    public void onNext(List<AtlasLite> atlasLites) {
                        viewModel.refresh(atlasLites);
                    }
                })
        ;
    }
}
