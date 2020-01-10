package com.hacknife.atlas.ui;

import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;


import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.AtlasAdapter;
import com.hacknife.atlas.adapter.RecycleGridDivider;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IAtlasView;
import com.hacknife.atlas.ui.viewmodel.impl.AtlasViewModel;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;
import com.hacknife.atlas.databinding.ActivityAtlasBinding;
import com.hacknife.imagepicker.ImagePicker;

import java.util.logging.Logger;

public class AtlasActivity extends BaseActivity<IAtlasViewModel, ActivityAtlasBinding> implements IAtlasView {
    AtlasAdapter adapter;


    @Override
    protected IAtlasViewModel performViewModel() {
        return new AtlasViewModel(this, dataBinding);
    }

    @Override
    protected ActivityAtlasBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_atlas);
    }

    @Override
    protected void init() {
        HttpClient.BASE_URL = getIntent().getData().getQueryParameter("url").endsWith("/") ? getIntent().getData().getQueryParameter("url") : getIntent().getData().getQueryParameter("url") + "/";
        adapter = new AtlasAdapter();
        dataBinding.rcAtlas.setAdapter(adapter);
        dataBinding.rcAtlas.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.rcAtlas.addItemDecoration(new RecycleGridDivider());
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore());
        adapter.setOnRecyclerViewListener((OnItemClickListener<Atlas>) t -> ImagePicker.getInstance().startImageViewer(this, t.getImage()));
        dataBinding.refresh.autoRefresh();
    }
}
