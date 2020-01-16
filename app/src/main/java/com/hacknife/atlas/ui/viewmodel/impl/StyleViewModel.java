package com.hacknife.atlas.ui.viewmodel.impl;

import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IStyleModel;
import com.hacknife.atlas.ui.model.impl.StyleModel;
import com.hacknife.atlas.ui.view.IStyleView;
import com.hacknife.atlas.ui.viewmodel.IStyleViewModel;
import com.hacknife.atlas.databinding.ActivityStyleBinding;

public class StyleViewModel extends BaseViewModel<IStyleView, IStyleModel, ActivityStyleBinding> implements IStyleViewModel {


    public StyleViewModel(IStyleView view, ActivityStyleBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityStyleBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IStyleModel createModel() {
        return new StyleModel(this);
    }


}
