package com.hacknife.atlas.ui.viewmodel.impl;

import android.util.Log;

import com.hacknife.atlas.adapter.DataSourceAdapter;
import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IDataSourceModel;
import com.hacknife.atlas.ui.model.impl.DataSourceModel;
import com.hacknife.atlas.ui.view.IDataSourceView;
import com.hacknife.atlas.ui.viewmodel.IDataSourceViewModel;
import com.hacknife.atlas.databinding.ActivityDataSourceBinding;
import com.orhanobut.logger.Logger;

import java.util.List;

public class DataSourceViewModel extends BaseViewModel<IDataSourceView, IDataSourceModel, ActivityDataSourceBinding> implements IDataSourceViewModel {


    public DataSourceViewModel(IDataSourceView view, ActivityDataSourceBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityDataSourceBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IDataSourceModel createModel() {
        return new DataSourceModel(this);
    }


    @Override
    public void refresh() {
        model.refresh();
    }

    @Override
    public void refresh(List<DataSource> atlas) {
        DataSourceAdapter adapter = (DataSourceAdapter) binding.rcDataSource.getAdapter();
        adapter.bindData(atlas);
        Logger.v( atlas.toString());
        binding.refresh.finishRefresh(500);
        binding.refresh.setNoMoreData(true);
    }
}
