package com.hacknife.atlas.ui;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.DataSourceAdapter;
import com.hacknife.atlas.adapter.DownAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.service.DownloadBinder;
import com.hacknife.atlas.service.DownloadListener;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IDownView;
import com.hacknife.atlas.ui.viewmodel.impl.DownViewModel;
import com.hacknife.atlas.ui.viewmodel.IDownViewModel;
import com.hacknife.atlas.databinding.ActivityDownBinding;

public class DownActivity extends BaseActivity<IDownViewModel, ActivityDownBinding> implements IDownView, ServiceConnection, DownloadListener {

    private DownAdapter adapter;

    @Override
    protected IDownViewModel performViewModel() {
        return new DownViewModel(this, dataBinding);
    }

    @Override
    protected ActivityDownBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_down);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(view -> onBackPressed());
        adapter = new DownAdapter();
        dataBinding.rcDown.setAdapter(adapter);
        dataBinding.rcDown.addItemDecoration(new StaggeredDividerItemDecoration(1, 20, true));
        dataBinding.rcDown.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dataBinding.refresh.setEnableRefresh(false);
        dataBinding.refresh.setEnableLoadMore(false);
        bindService(new Intent(this, DownloadService.class), this, BIND_AUTO_CREATE);
    }

    DownloadBinder binder;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        binder = ((DownloadBinder) iBinder);
        binder.registDownloadListener(this);
        adapter.bindData(binder.getAtlases());
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        binder.unRegistDownloadListener();
    }

    @Override
    public void onProgress(int index, int len) {
        adapter.data().get(0).setCurrent(index);
        adapter.data().get(0).setSize(len);
        adapter.notifyItemChanged(0);
    }

    @Override
    public void onComplete() {
        adapter.data().remove(0);
    adapter.notifyItemRemoved(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }
}
