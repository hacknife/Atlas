package com.hacknife.atlas.ui.base.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.atlas.ui.base.IBaseView;
import com.hacknife.atlas.ui.base.IBaseViewModel;

import java.util.ArrayList;

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

    protected void init() {
    }

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


    public boolean startActivity(Class clazz, Object... values) {
        Intent intent = new Intent(this, clazz);
        if (values.length % 2 != 0) {
            return false;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i + 1] instanceof String) {
                intent.putExtra((String) values[i], (String) values[i + 1]);
            } else if (values[i + 1] instanceof Integer) {
                intent.putExtra((String) values[i], (Integer) values[i + 1]);
            } else if (values[i + 1] instanceof ArrayList) {
                intent.putStringArrayListExtra((String) values[i], (ArrayList<String>) values[i + 1]);
            } else {
                intent.putExtra((String) values[i], (Parcelable) values[i + 1]);
            }
            i++;
        }
        startActivity(intent);
        return true;
    }
}