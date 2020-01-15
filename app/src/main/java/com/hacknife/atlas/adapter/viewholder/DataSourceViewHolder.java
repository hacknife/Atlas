package com.hacknife.atlas.adapter.viewholder;


import android.view.View;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.databinding.ItemDataSourceBinding;


public class DataSourceViewHolder extends BaseRecyclerViewHolder<DataSource, ItemDataSourceBinding> {

    public DataSourceViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(DataSource entity) {
        binding.setEntity(entity);
    }

    @Override
    protected int callback(DataSource atlasLite) {
        if (atlasLite.checked()) return position;
        return super.callback(atlasLite);
    }
}