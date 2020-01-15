package com.hacknife.atlas.ui;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hacknife.atlas.R;
import com.hacknife.atlas.Test;
import com.hacknife.atlas.adapter.AtlasAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.bean.AtlasLiteLite;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.bus.ChangeDataSourceEvent;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.ScreenHelper;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IAtlasView;
import com.hacknife.atlas.ui.viewmodel.impl.AtlasViewModel;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;
import com.hacknife.atlas.databinding.ActivityAtlasBinding;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class AtlasActivity extends BaseActivity<IAtlasViewModel, ActivityAtlasBinding> implements IAtlasView, NavigationView.OnNavigationItemSelectedListener {
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
        AppConfig.width = ScreenHelper.width(this) - AppConfig.SPACE * 4;
        dataBinding.drawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        adapter = new AtlasAdapter();
        dataBinding.menu.setNavigationItemSelectedListener(this);
        dataBinding.toolbar.setNavigationOnClickListener(view -> dataBinding.drawer.openMenu(true));
        dataBinding.rcAtlas.setAdapter(adapter);
        dataBinding.rcAtlas.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.rcAtlas.addItemDecoration(new StaggeredDividerItemDecoration(2, 10, true));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore());
        adapter.setOnRecyclerViewListener((OnItemClickListener<Atlas>) t -> startActivity(ImageActivity.class, Constant.URL, t.getUrl(), Constant.TITLE, t.getTitle(), Constant.COVER, t.getCover()));
        dataBinding.refresh.autoRefresh();
        RxBus.toObservable(ChangeDataSourceEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(changeDataSourceEvent -> viewModel.refresh());
        RxBus.toObservable(ChangeDataSourceEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(e -> dataBinding.toolbar.setTitle(AtlasResource.get().name));
        if (AtlasResource.get().name != null)
            dataBinding.toolbar.setTitle(AtlasResource.get().name);
        dataBinding.toolbar.setOnMenuItemClickListener(item -> {
            startService(new Intent(AtlasActivity.this, DownloadService.class));
            RxBus.post(new DownloadEvent(adapter.data()));
            return true;
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_datasource)
            startActivity(DataSourceActivity.class);
        else if (id == R.id.menu_download)
            startActivity(DownActivity.class);
        dataBinding.drawer.closeMenu(true);
        return true;
    }


}
