package com.hacknife.atlas.adapter.viewholder;


import android.view.View;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.databinding.ItemAtlasBinding;

public class AtlasViewHolder extends BaseRecyclerViewHolder<Atlas, ItemAtlasBinding> {

    public AtlasViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Atlas entity) {
        binding.setEntity(entity);
    }


}