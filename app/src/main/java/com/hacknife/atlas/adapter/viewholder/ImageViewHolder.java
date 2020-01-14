package com.hacknife.atlas.adapter.viewholder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;

import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.databinding.ItemImageBinding;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.ImageSize;


public class ImageViewHolder extends BaseRecyclerViewHolder<String, ItemImageBinding> {
    ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_image);
    }

    @Override
    public void bindData(String url) {
        Image image = ImageSize.size().get(url);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (image != null) {
            params.width = image.getWidth();
            params.height = image.getHeight();
        } else {
            params.width = AppConfig.width / 2;
            params.height = AppConfig.width / 2;
        }
        imageView.setLayoutParams(params);
        binding.setEntity(entity);
    }

}