package com.hacknife.atlas.adapter;

import com.hacknife.atlas.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.viewholder.DataSourceViewHolder;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.DataSource;

public class DataSourceAdapter extends BaseRecyclerViewAdapter<DataSource, BaseRecyclerViewHolder> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataSourceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_source, null));
    }

}
