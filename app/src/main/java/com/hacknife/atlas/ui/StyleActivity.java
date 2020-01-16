package com.hacknife.atlas.ui;


import androidx.databinding.DataBindingUtil;

import com.hacknife.atlas.R;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IStyleView;
import com.hacknife.atlas.ui.viewmodel.impl.StyleViewModel;
import com.hacknife.atlas.ui.viewmodel.IStyleViewModel;
import com.hacknife.atlas.databinding.ActivityStyleBinding;

public class StyleActivity extends BaseActivity<IStyleViewModel, ActivityStyleBinding> implements IStyleView {

    @Override
    protected IStyleViewModel performViewModel() {
        return new StyleViewModel(this, dataBinding);
    }

    @Override
    protected ActivityStyleBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_style);
    }
}
