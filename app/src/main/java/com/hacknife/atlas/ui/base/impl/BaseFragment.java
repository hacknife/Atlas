package com.hacknife.atlas.ui.base.impl;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.hacknife.atlas.ui.base.IBaseView;
import com.hacknife.atlas.ui.base.IBaseViewModel;

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : DataBinding
 * version: 1.0
 */
public abstract class BaseFragment<ViewModel extends IBaseViewModel, DataBinding extends ViewDataBinding> extends Fragment implements IBaseView {
    protected ViewModel viewModel;
    protected DataBinding dataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = performBinding(inflater, container);
        viewModel = performViewModel();
        viewModel.initial();
        return dataBinding.getRoot();
    }


    protected abstract ViewModel performViewModel();

    protected abstract DataBinding performBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public Application application() {
        return getActivity().getApplication();
    }

    @Override
    public Context applicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        viewModel.destroy();
        viewModel = null;
        dataBinding = null;
        super.onDestroy();
    }
}
