package com.hacknife.atlas.ui;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.ImageViewerAdapter;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IImageViewerView;
import com.hacknife.atlas.ui.viewmodel.impl.ImageViewerViewModel;
import com.hacknife.atlas.ui.viewmodel.IImageViewerViewModel;
import com.hacknife.atlas.databinding.ActivityImageViewerBinding;


import java.util.List;

public class ImageViewerActivity extends BaseActivity<IImageViewerViewModel, ActivityImageViewerBinding> implements IImageViewerView {

    @Override
    protected IImageViewerViewModel performViewModel() {
        return new ImageViewerViewModel(this, dataBinding);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ActivityImageViewerBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_image_viewer);
    }

    @Override
    protected void init() {
        List<String> urls = getIntent().getStringArrayListExtra("images");
        int position = getIntent().getIntExtra("position", 0);
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        ImageViewerAdapter adapter = new ImageViewerAdapter(this);
        adapter.addData(urls);
        dataBinding.indicator.setVisibility(View.VISIBLE);
        dataBinding.viewpager.setAdapter(adapter);
        dataBinding.viewpager.setCurrentItem(position);
        dataBinding.indicator.setText(getString(R.string.indicator, position, adapter.getCount()));
        dataBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dataBinding.indicator.setText(getString(R.string.indicator, position + 1, adapter.getCount()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dataBinding.viewpager.setOffscreenPageLimit(4);

    }
}
