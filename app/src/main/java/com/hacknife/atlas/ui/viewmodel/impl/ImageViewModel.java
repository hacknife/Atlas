package com.hacknife.atlas.ui.viewmodel.impl;


import android.util.Log;

import com.hacknife.atlas.adapter.ImageAdapter;
import com.hacknife.atlas.app.AtlasApplication;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IImageModel;
import com.hacknife.atlas.ui.model.impl.ImageModel;
import com.hacknife.atlas.ui.view.IImageView;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.atlas.databinding.ActivityImageBinding;

import java.util.ArrayList;

public class ImageViewModel extends BaseViewModel<IImageView, IImageModel, ActivityImageBinding> implements IImageViewModel {


    private String nextPage;

    public ImageViewModel(IImageView view, ActivityImageBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityImageBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IImageModel createModel() {
        return new ImageModel(this);
    }


    @Override
    public void refresh(String url) {
        nextPage = url;
        model.refresh(new Images(nextPage, new ArrayList<>()));
    }

    @Override
    public void loadMore() {
        model.loadMore(new Images(nextPage, new ArrayList<>()));
    }

    @Override
    public void loadMore(Images images) {
        ImageAdapter adapter = (ImageAdapter) binding.rcImage.getAdapter();
        adapter.insert(images.getImages());
        binding.refresh.finishLoadMore(500);
        if (images.getNext() == null || images.getNext().length() == 0)
            binding.refresh.setNoMoreData(true);
        else
            nextPage = images.getNext();
        Log.v("dzq", "loadMore size:" + images.getImages().size());
        Log.v("dzq", "loadMore page:" + nextPage);
        if (images.getImages().size() < AppConfig.PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void refresh(Images images) {
        ImageAdapter adapter = (ImageAdapter) binding.rcImage.getAdapter();
        if (adapter.data().size() > 0) {
            binding.refresh.finishRefresh(500);
            return;
        }
        adapter.bindData(images.getImages());
        binding.refresh.finishRefresh(500);
        if (images.getNext() == null || images.getNext().length() == 0)
            binding.refresh.setNoMoreData(false);
        else
            nextPage = images.getNext();
        Log.v("dzq", "refresh size:" + images.getImages().size());
        Log.v("dzq", "refresh page:" + nextPage);
        if (images.getImages().size() < AppConfig.PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
        else
            binding.refresh.setNoMoreData(false);
    }
}
