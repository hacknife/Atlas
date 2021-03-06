package com.hacknife.atlas.ui.base.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.atlas.R;
import com.hacknife.atlas.bean.Theme;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.bus.ThemeEvent;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.ui.base.IBaseView;
import com.hacknife.atlas.ui.base.IBaseViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

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
    protected CompositeDisposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(AppConfig.theme);
        dataBinding = performBinding();
        viewModel = performViewModel();
        viewModel.initial();
        disposable = new CompositeDisposable();
        RxBus.toObservable(ThemeEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ThemeEvent>(disposable) {
                    @Override
                    public void onNext(ThemeEvent event) {
                        recreate();
                    }
                });
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
        disposable.clear();
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
                intent.putParcelableArrayListExtra((String) values[i], (ArrayList<Parcelable>) values[i + 1]);
            } else {
                intent.putExtra((String) values[i], (Parcelable) values[i + 1]);
            }
            i++;
        }
        startActivity(intent);
        return true;
    }
}