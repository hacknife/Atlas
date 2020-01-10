package com.hacknife.atlas.ui.base.impl;

import android.app.Application;
import android.content.Context;

import com.hacknife.atlas.ui.base.IBaseModel;
import com.hacknife.atlas.ui.base.IBaseView;
import com.hacknife.atlas.ui.base.IBaseViewModel;

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : DataBinding
 * version: 1.0
 */
public abstract class BaseViewModel<V extends IBaseView, M extends IBaseModel, D> implements IBaseViewModel {
    protected V view;
    protected D binding;
    protected M model;

    public BaseViewModel(V view, D binding) {
        this.view = view;
        this.binding = binding;
        binding(binding);
        model = createModel();
    }

    protected abstract void binding(D binding);

    protected abstract M createModel();

    @Override
    public void initial() {
        model.initial();
    }

    @Override
    public Application application() {
        return view.application();
    }

    @Override
    public Context applicationContext() {
        return view.applicationContext();
    }

    @Override
    public Context context() {
        return view.context();
    }

    @Override
    public void destroy() {
        model.destroy();
        model = null;
        view = null;
    }
}
