package com.hacknife.atlas.adapter.viewholder;


import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.adapter.base.OnRecyclerListener;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.databinding.ItemImageBinding;
import com.hacknife.atlas.glide.Glide;
import com.hacknife.atlas.glide.GlideApp;

import java.util.Map;

public class ImageViewHolder extends BaseRecyclerViewHolder<String, ItemImageBinding> {
    Map<Integer, Image> imageSize;

    public ImageViewHolder(View itemView, Map<Integer, Image> imageSize) {
        super(itemView);
        this.imageSize = imageSize;
    }

    @Override
    public void bindData(String entity) {
        binding.setEntity(entity);
//        Image image = imageSize.get(position);
//        if (image == null) {
//            image = new Image(0, 0);
//            imageSize.put(position, image);
//        }else {
//            ViewGroup.LayoutParams layoutParams = binding.ivCover.getLayoutParams();
//            layoutParams.height = image.getHeight();
//            binding.ivCover.setLayoutParams(layoutParams);
//        }
//        GlideApp.with(binding.ivCover.getContext())
//                .asBitmap()
//                .load(entity)
//                .listener(new RequestListener<Bitmap>() {
//
//
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                        int height = (int) (resource.getHeight() / resource.getWidth() * 1f * itemView.getWidth());
//                        imageSize.get(position).setHeight(height);
//                        ViewGroup.LayoutParams layoutParams = binding.ivCover.getLayoutParams();
//                        layoutParams.height = height;
//                        binding.ivCover.setLayoutParams(layoutParams);
//                        return false;
//                    }
//                })
//                .into(binding.ivCover);
    }

}