package com.hacknife.atlas.ui.viewmodel.impl;

import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IDownModel;
import com.hacknife.atlas.ui.model.impl.DownModel;
import com.hacknife.atlas.ui.view.IDownView;
import com.hacknife.atlas.ui.viewmodel.IDownViewModel;
import com.hacknife.atlas.databinding.ActivityDownBinding;

public class DownViewModel extends BaseViewModel<IDownView, IDownModel, ActivityDownBinding> implements IDownViewModel {


    public DownViewModel(IDownView view, ActivityDownBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityDownBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IDownModel createModel() {
        return new DownModel(this);
    }


}
