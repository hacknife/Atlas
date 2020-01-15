package com.hacknife.atlas.adapter.viewholder;


import android.view.View;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Atlas;

import com.hacknife.atlas.databinding.ItemDownBinding;

public class DownViewHolder extends BaseRecyclerViewHolder<Atlas, ItemDownBinding> {

    public DownViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Atlas entity) {
        binding.setEntity(entity);
    }


}