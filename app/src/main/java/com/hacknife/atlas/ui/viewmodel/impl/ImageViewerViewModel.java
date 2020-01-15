package com.hacknife.atlas.ui.viewmodel.impl;

import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IImageViewerModel;
import com.hacknife.atlas.ui.model.impl.ImageViewerModel;
import com.hacknife.atlas.ui.view.IImageViewerView;
import com.hacknife.atlas.ui.viewmodel.IImageViewerViewModel;
import com.hacknife.atlas.databinding.ActivityImageViewerBinding;

public class ImageViewerViewModel extends BaseViewModel<IImageViewerView, IImageViewerModel, ActivityImageViewerBinding> implements IImageViewerViewModel {
    String nextPage;

    public ImageViewerViewModel(IImageViewerView view, ActivityImageViewerBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityImageViewerBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IImageViewerModel createModel() {
        return new ImageViewerModel(this);
    }

}
