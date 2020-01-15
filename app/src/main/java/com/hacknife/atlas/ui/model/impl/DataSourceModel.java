package com.hacknife.atlas.ui.model.impl;

import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.bean.DataSourceLite;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IDataSourceModel;
import com.hacknife.atlas.ui.viewmodel.IDataSourceViewModel;
import com.hacknife.onlite.OnLiteFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                    List<DataSource> list = OnLiteFactory.create(DataSourceLite.class).select(null);
                    for (DataSource atlas : list) {
                        if (atlas.checked()) {
                            int id = atlas.getId();
                            for (DataSource lite : atlasLites) {
                                if (lite.getId() == id) {
                                    lite.setChecked(1);
                                    break;
                                }
                            }
                            return;
                        }
                    }
                })
                .doOnNext(atlasLites -> OnLiteFactory.create(DataSourceLite.class).delete(null))
                .doOnNext(dataSources -> OnLiteFactory.create(DataSourceLite.class).insert(dataSources))
                .onErrorReturn(throwable -> OnLiteFactory.create(DataSourceLite.class).select(null))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataSource>>(disposable) {
                    @Override
                    public void onNext(List<DataSource> atlasLites) {
                        viewModel.refresh(atlasLites);
                    }
                })
        ;
    }
}
