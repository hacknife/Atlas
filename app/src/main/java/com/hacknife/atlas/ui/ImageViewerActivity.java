package com.hacknife.atlas.ui;


import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.ImageViewerAdapter;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IImageViewerView;
import com.hacknife.atlas.ui.viewmodel.impl.ImageViewerViewModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewerViewModel;
import com.hacknife.atlas.databinding.ActivityImageViewerBinding;


import java.util.ArrayList;

public class ImageViewerActivity extends BaseActivity<IImageViewerViewModel, ActivityImageViewerBinding> implements IImageViewerView {

    @Override
    protected IImageViewerViewModel performViewModel() {
        return new ImageViewerViewModel(this, dataBinding);
    }

    @Override
    protected ActivityImageViewerBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_image_viewer);
    }

    @Override
    protected void init() {
        String url = getIntent().getStringExtra("URL");
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        ImageViewerAdapter mAdapter = new ImageViewerAdapter(this);
        dataBinding.indicator.setVisibility(View.VISIBLE);
        dataBinding.viewpager.setAdapter(mAdapter);
//        dataBinding.viewpager.setBackgroundColor(getApplication().getResources().getColor(R.color.white));
        dataBinding.indicator.setText(getString(R.string.indicator, 0, mAdapter.getCount()));
        dataBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dataBinding.indicator.setText(getString(R.string.indicator, position + 1, mAdapter.getCount()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dataBinding.viewpager.setOffscreenPageLimit(4);
        dataBinding.refresh.autoRefresh();
        dataBinding.refresh.setEnableAutoLoadMore(true);
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh(url));
        dataBinding.refresh.setOnLoadMoreListener(v -> viewModel.loadMore());
        dataBinding.refresh.setEnableRefresh(false);
    }
}
