package com.hacknife.atlas.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageBinding {
    @BindingAdapter("app:imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        Glide.with(imageView)
                .asBitmap()
                .load(url)
                .into(imageView);
    }

}
