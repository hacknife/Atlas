package com.hacknife.atlas.adapter;

import com.hacknife.atlas.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.atlas.adapter.viewholder.AtlasViewHolder;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Atlas;

public class AtlasAdapter extends BaseRecyclerViewAdapter<Atlas, BaseRecyclerViewHolder> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AtlasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atlas, null));
    }

}
