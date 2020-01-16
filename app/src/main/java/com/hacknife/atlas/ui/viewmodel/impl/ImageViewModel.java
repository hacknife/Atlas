package com.hacknife.atlas.ui.viewmodel.impl;


import android.content.Intent;
import android.util.Log;

import com.hacknife.atlas.adapter.ImageAdapter;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.bean.ImageCollection;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IImageModel;
import com.hacknife.atlas.ui.model.impl.ImageModel;
import com.hacknife.atlas.ui.view.IImageView;
import com.hacknife.atlas.ui.viewmodel.IImageViewModel;
import com.hacknife.atlas.databinding.ActivityImageBinding;
import com.hacknife.onlite.OnLiteFactory;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ImageViewModel extends BaseViewModel<IImageView, IImageModel, ActivityImageBinding> implements IImageViewModel {


    private String nextPage;
    private Atlas atlas;
    private boolean noData = false;

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
    public void refresh(Atlas atlas) {
        ImageAdapter adapter = (ImageAdapter) binding.rcImage.getAdapter();
        if (adapter.data().size() > 0) {
            binding.refresh.finishRefresh();
            if (noData)
                binding.refresh.setNoMoreData(true);
            return;
        }
        nextPage = atlas.getUrl();
        this.atlas = atlas;
        model.refresh(new ImageCollection(nextPage, new ArrayList<>(), atlas.getCached()));
    }

    @Override
    public void loadMore() {
        if (noData) {
            binding.refresh.finishLoadMore();
            return;
        }
        model.loadMore(new ImageCollection(nextPage, new ArrayList<>()));
    }

    @Override
    public void loadMore(ImageCollection images) {

        ImageAdapter adapter = (ImageAdapter) binding.rcImage.getAdapter();
        adapter.insert(images.getImages());
        binding.refresh.finishLoadMore(500);
        if (images.getNext() == null || images.getNext().length() == 0) {
            binding.refresh.setNoMoreData(true);
            noData = true;
            if (!images.cached() && adapter.data().size() > 0) {
                context().startService(new Intent(context(), DownloadService.class));
                RxBus.post(new DownloadEvent(Collections.singletonList(atlas)));
            }
        } else
            nextPage = images.getNext();
        Logger.v("loadMore current size:" + images.getImages().size());
        Logger.v("loadMore next page:" + nextPage);
        if (images.getImages().size() < AppConfig.PAGE_SIZE) {
            binding.refresh.setNoMoreData(true);
            noData = true;
            if (!images.cached() && adapter.data().size() > 0) {
                context().startService(new Intent(context(), DownloadService.class));
                RxBus.post(new DownloadEvent(Collections.singletonList(atlas)));
            }
        }
    }

    @Override
    public void refresh(ImageCollection images) {
        ImageAdapter adapter = (ImageAdapter) binding.rcImage.getAdapter();
        if (adapter.data().size() > 0) {
            binding.refresh.finishRefresh(500);

            return;
        }
        adapter.bindData(images.getImages());
        binding.refresh.finishRefresh(500);

        if (images.getNext() == null || images.getNext().length() == 0) {
            binding.refresh.setNoMoreData(true);
            noData = true;
            if (!images.cached() && adapter.data().size() > 0) {
                context().startService(new Intent(context(), DownloadService.class));
                RxBus.post(new DownloadEvent(Collections.singletonList(atlas)));
            }
        } else
            nextPage = images.getNext();
        Logger.v("refresh size:" + images.getImages().size());
        Logger.v("refresh next page:" + nextPage);
        if (images.getImages().size() < AppConfig.PAGE_SIZE || images.cached()) {
            if (!images.cached() && adapter.data().size() > 0) {
                context().startService(new Intent(context(), DownloadService.class));
                RxBus.post(new DownloadEvent(Collections.singletonList(atlas)));
            }
            binding.refresh.setNoMoreData(true);
            noData = true;


        } else
            binding.refresh.setNoMoreData(false);
    }
}
