package com.hacknife.atlas.ui;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.DataSourceAdapter;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IDataSourceView;
import com.hacknife.atlas.ui.viewmodel.impl.DataSourceViewModel;
import com.hacknife.atlas.ui.viewmodel.IDataSourceViewModel;
import com.hacknife.atlas.databinding.ActivityDataSourceBinding;

public class DataSourceActivity extends BaseActivity<IDataSourceViewModel, ActivityDataSourceBinding> implements IDataSourceView {

    @Override
    protected IDataSourceViewModel performViewModel() {
        return new DataSourceViewModel(this, dataBinding);
    }

    @Override
    protected ActivityDataSourceBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_data_source);
    }

    @Override
    protected void init() {
        DataSourceAdapter adapter = new DataSourceAdapter();
        dataBinding.rcDataSource.setAdapter(adapter);
        dataBinding.rcDataSource.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh());
        dataBinding.refresh.autoRefresh();

    }
}
