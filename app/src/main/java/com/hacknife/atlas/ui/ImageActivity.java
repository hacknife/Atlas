package com.hacknife.atlas.ui;


import android.content.Intent;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.ImageAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.adapter.base.OnItemClickListener2;
import com.hacknife.atlas.app.AtlasApplication;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IImageView;
import com.hacknife.atlas.ui.viewmodel.impl.ImageViewModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.atlas.databinding.ActivityImageBinding;
import com.hacknife.imagepicker.ImagePicker;

import java.util.ArrayList;

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
        String url = getIntent().getStringExtra("URL");
        ImageAdapter adapter = new ImageAdapter();
        dataBinding.rcImage.setAdapter(adapter);
        dataBinding.rcImage.setItemAnimator(null);
        dataBinding.rcImage.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        dataBinding.rcImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[2];
                ((StaggeredGridLayoutManager) dataBinding.rcImage.getLayoutManager()).findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    ((StaggeredGridLayoutManager) dataBinding.rcImage.getLayoutManager()).invalidateSpanAssignments();
                }
            }
        });
        ((StaggeredGridLayoutManager) dataBinding.rcImage.getLayoutManager()).setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        dataBinding.rcImage.addItemDecoration(new StaggeredDividerItemDecoration(2, AppConfig.SPACE, true));
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh(url));
        dataBinding.refresh.setOnLoadMoreListener(v -> viewModel.loadMore());
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener2) (t, last, current, view) -> {
            Intent intent = new Intent(this, ImageViewerActivity.class);
            intent.putStringArrayListExtra("images", (ArrayList<String>) adapter.data());
            intent.putExtra("position", current);
            startActivity(intent);
            return false;
        });
    }
}
