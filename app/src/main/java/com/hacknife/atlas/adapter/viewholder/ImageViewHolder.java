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


public class ImageViewHolder extends BaseRecyclerViewHolder<String, ItemImageBinding> {
    ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_image);
    }

    @Override
    public void bindData(String url) {
        Image image = AtlasResource.get().imageSize.get(url);
        if (image != null) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = image.getWidth();
            params.height = image.getHeight();
            imageView.setLayoutParams(params);
            Log.i("dzq", "设置大小: " + params.width + "<>" + params.height);
        }
        binding.setEntity(entity);
    }

}