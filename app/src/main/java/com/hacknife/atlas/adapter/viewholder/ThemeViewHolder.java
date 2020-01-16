package com.hacknife.atlas.adapter.viewholder;


import android.view.View;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Theme;
import com.hacknife.atlas.databinding.ItemThemeBinding;
import com.hacknife.atlas.helper.AppConfig;

public class ThemeViewHolder extends BaseRecyclerViewHolder<Theme, ItemThemeBinding> {

    public ThemeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Theme entity) {
        ViewGroup.LayoutParams params = binding.ivTheme.getLayoutParams();
        params.width = (AppConfig.width - 4 * AppConfig.SPACE) / 4;
        params.height = (AppConfig.width - 4 * AppConfig.SPACE) / 4;
        binding.ivTheme.setLayoutParams(params);
        binding.setEntity(entity);
    }

    @Override
    protected int callback(Theme theme) {
        return theme.getIsChecked() == 1 ? position : super.callback(theme);
    }
}