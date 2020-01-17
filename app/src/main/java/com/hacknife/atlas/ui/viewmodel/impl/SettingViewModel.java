package com.hacknife.atlas.ui.viewmodel.impl;

import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.ISettingModel;
import com.hacknife.atlas.ui.model.impl.SettingModel;
import com.hacknife.atlas.ui.view.ISettingView;
import com.hacknife.atlas.ui.viewmodel.ISettingViewModel;
import com.hacknife.atlas.databinding.ActivitySettingBinding;

public class SettingViewModel extends BaseViewModel<ISettingView, ISettingModel, ActivitySettingBinding> implements ISettingViewModel {


    public SettingViewModel(ISettingView view, ActivitySettingBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivitySettingBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected ISettingModel createModel() {
        return new SettingModel(this);
    }


}
