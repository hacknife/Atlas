package com.hacknife.atlas.adapter.viewholder;


import android.view.View;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.databinding.ItemThemeBinding;

public class ThemeViewHolder extends BaseRecyclerViewHolder<Integer, ItemThemeBinding> {

    public ThemeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Integer entity) {

        binding.setEntity(entity);
    }


}