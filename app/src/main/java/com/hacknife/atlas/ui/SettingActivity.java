package com.hacknife.atlas.ui;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.hacknife.atlas.R;
import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.ISettingView;
import com.hacknife.atlas.ui.viewmodel.impl.SettingViewModel;
import com.hacknife.atlas.ui.viewmodel.ISettingViewModel;
import com.hacknife.atlas.databinding.ActivitySettingBinding;
import com.hacknife.atlas.widget.ChooseBottomSheetDialog;

public class SettingActivity extends BaseActivity<ISettingViewModel, ActivitySettingBinding> implements ISettingView, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected ISettingViewModel performViewModel() {
        return new SettingViewModel(this, dataBinding);
    }

    @Override
    protected ActivitySettingBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_setting);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        dataBinding.navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        new ChooseBottomSheetDialog(this)
                .title(menuItem.getTitle())
                .content(menuItem.getTitleCondensed())
                .setOnChooseBottomSheetDialogConfirmListener(() -> {
                })
                .show();
        return true;
    }
}
