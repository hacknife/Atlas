package com.hacknife.atlas.adapter;

import com.hacknife.atlas.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.viewholder.ThemeViewHolder;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Theme;

public class ThemeAdapter extends BaseRecyclerViewAdapter<Theme, BaseRecyclerViewHolder> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, null));
    }

}
