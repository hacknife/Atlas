package com.hacknife.atlas.ui;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.ImageAdapter;
import com.hacknife.atlas.adapter.RecycleGridDivider;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IImageView;
import com.hacknife.atlas.ui.viewmodel.impl.ImageViewModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.atlas.databinding.ActivityImageBinding;

public class ImageActivity extends BaseActivity<IImageViewModel, ActivityImageBinding> implements IImageView {
    ImageAdapter adapter;

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
        String url = getIntent().getStringExtra("URL");
        adapter = new ImageAdapter();
        dataBinding.rcImage.setAdapter(adapter);
        dataBinding.rcImage.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ((StaggeredGridLayoutManager) dataBinding.rcImage.getLayoutManager()).setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        dataBinding.rcImage.addItemDecoration(new StaggeredDividerItemDecoration(2, 20, true));
        dataBinding.rcImage.setItemAnimator(null);
        dataBinding.rcImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).invalidateSpanAssignments();
            }
        });
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh(url));
        dataBinding.refresh.setOnLoadMoreListener(v -> viewModel.loadMore());
        dataBinding.refresh.autoRefresh();
    }
}
