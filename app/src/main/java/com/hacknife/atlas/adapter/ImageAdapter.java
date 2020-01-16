package com.hacknife.atlas.adapter;

import com.hacknife.atlas.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.viewholder.ImageViewHolder;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.atlas.bean.Image;

public class ImageAdapter extends BaseRecyclerViewAdapter<Image, ImageViewHolder> {

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null));
    }

}
