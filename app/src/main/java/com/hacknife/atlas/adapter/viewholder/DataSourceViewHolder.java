package com.hacknife.atlas.adapter.viewholder;


import android.view.View;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.databinding.ItemDataSourceBinding;


public class DataSourceViewHolder extends BaseRecyclerViewHolder<AtlasLite, ItemDataSourceBinding> {

    public DataSourceViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(AtlasLite entity) {
        binding.setEntity(entity);
    }


}