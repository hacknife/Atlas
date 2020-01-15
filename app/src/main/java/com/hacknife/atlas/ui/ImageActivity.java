package com.hacknife.atlas.ui;


import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.ImageAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.adapter.base.OnItemClickListener2;
import com.hacknife.atlas.app.AtlasApplication;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IImageView;
import com.hacknife.atlas.ui.viewmodel.impl.ImageViewModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.atlas.databinding.ActivityImageBinding;
import com.hacknife.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageActivity extends BaseActivity<IImageViewModel, ActivityImageBinding> implements IImageView {

    @Override
    protected IImageViewModel performViewModel() {
        return new ImageViewModel(this, dataBinding);
    }

    @Override
    protected ActivityImageBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_image);
    }

    @Override
    protected void init() {
//        String url = getIntent().getStringExtra(Constant.URL);
//        String title = getIntent().getStringExtra(Constant.TITLE);
//        String cover = getIntent().getStringExtra(Constant.COVER);
        Atlas atlas =getIntent().getParcelableExtra(Constant.URL);
        dataBinding.toolbarTitle.setText(atlas.getTitle());
        dataBinding.ivBack.setOnClickListener(view -> onBackPressed());
        ImageAdapter adapter = new ImageAdapter();
        dataBinding.rcImage.setAdapter(adapter);
        dataBinding.rcImage.setItemAnimator(null);
        dataBinding.rcImage.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ((StaggeredGridLayoutManager) dataBinding.rcImage.getLayoutManager()).setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        dataBinding.rcImage.addItemDecoration(new StaggeredDividerItemDecoration(2, AppConfig.SPACE, true));
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh(atlas));
        dataBinding.refresh.setOnLoadMoreListener(v -> viewModel.loadMore());
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener2) (t, last, current, view) -> startActivity(ImageViewerActivity.class, Constant.IMAGES, adapter.data(), Constant.POSITION, current));
        dataBinding.toolbar.setOnMenuItemClickListener(item -> {
            startService(new Intent(ImageActivity.this, DownloadService.class));
            RxBus.post(new DownloadEvent(Arrays.asList(atlas)));
            return true;
        });
    }
}
