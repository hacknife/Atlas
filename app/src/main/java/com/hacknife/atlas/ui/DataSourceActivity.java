package com.hacknife.atlas.ui;


import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.DataSourceAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener2;
import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.bean.DataSourceLite;
import com.hacknife.atlas.bean.DataSelector;
import com.hacknife.atlas.bus.ChangeDataSourceEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IDataSourceView;
import com.hacknife.atlas.ui.viewmodel.impl.DataSourceViewModel;
import com.hacknife.atlas.ui.viewmodel.IDataSourceViewModel;
import com.hacknife.atlas.databinding.ActivityDataSourceBinding;
import com.hacknife.onlite.OnLiteFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        dataBinding.ivBack.setOnClickListener(view -> onBackPressed());
        DataSourceAdapter adapter = new DataSourceAdapter();
        dataBinding.rcDataSource.setAdapter(adapter);
        dataBinding.rcDataSource.addItemDecoration(new StaggeredDividerItemDecoration(1, 20, true));
        dataBinding.rcDataSource.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh());
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener2<DataSource>) (atlas, last, current, view) -> {
            if (last == current) {
                Log.i("dzq", "onItemClick: 相等");
                return false;
            } else {
                DataSelector.init(atlas);
                if (last!=-1){
                    adapter.data().get(last).setChecked(0);
                    adapter.notifyItemChanged(last);
                }
                adapter.data().get(current).setChecked(1);
                adapter.notifyItemChanged(current);
                Observable.just(adapter.data())
                        .doOnNext(l -> OnLiteFactory.create(DataSourceLite.class).delete(null))
                        .doOnNext(l -> OnLiteFactory.create(DataSourceLite.class).insert(l))
                        .subscribeOn(Schedulers.newThread())
                        .subscribe();
                RxBus.post(new ChangeDataSourceEvent());
            }
            return true;
        });

    }
}
