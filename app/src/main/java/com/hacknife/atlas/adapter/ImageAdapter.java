package com.hacknife.atlas.adapter;

import com.hacknife.atlas.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.viewholder.ImageViewHolder;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewAdapter;

public class ImageAdapter extends BaseRecyclerViewAdapter<String, ImageViewHolder> {

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null));
    }

}
