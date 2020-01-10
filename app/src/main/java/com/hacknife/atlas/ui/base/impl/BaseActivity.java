package com.hacknife.atlas.ui.base.impl;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.atlas.ui.base.IBaseView;
import com.hacknife.atlas.ui.base.IBaseViewModel;

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : DataBinding
 * version: 1.0
 */
public abstract class BaseActivity<ViewModel extends IBaseViewModel, DataBinding> extends AppCompatActivity implements IBaseView {
    protected ViewModel viewModel;
    protected DataBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = performBinding();
        viewModel = performViewModel();
        viewModel.initial();
        init();
    }

    protected void init(){}
    protected abstract ViewModel performViewModel();

    protected abstract DataBinding performBinding();

    @Override
    public Application application() {
        return getApplication();
    }

    @Override
    public Context applicationContext() {
        return getApplicationContext();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        viewModel = null;
        dataBinding = null;
        super.onDestroy();
    }
}