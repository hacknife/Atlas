package com.hacknife.atlas.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;

import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.databinding.ItemImageBinding;
import com.hacknife.atlas.helper.AppConfig;


public class ImageViewHolder extends BaseRecyclerViewHolder<Image, ItemImageBinding> {
    ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_image);
    }

    @Override
    public void bindData(Image image) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (image.getHeight() != null && image.getHeight() != 0) {
            params.width = ((AppConfig.width - 4 * AppConfig.SPACE) / 2);
            params.height = (int) ((image.getHeight() * 1f) / image.getWidth() * params.width);
        } else {
            params.width = (AppConfig.width - 4 * AppConfig.SPACE) / 2;
            params.height = (AppConfig.width - 4 * AppConfig.SPACE) / 2;
        }
        imageView.setLayoutParams(params);
        binding.setEntity(entity);
    }

}