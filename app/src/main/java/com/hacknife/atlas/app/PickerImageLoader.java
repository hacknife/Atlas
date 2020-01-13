package com.hacknife.atlas.app;

import android.widget.ImageView;


import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.imagepicker.loader.ImageLoader;

public class PickerImageLoader implements ImageLoader {
    @Override
    public void displayFileImage(ImageView imageView, String path) {
        GlideApp.with(imageView)
                .load(path)
                .into(imageView);
    }

    @Override
    public void displayUserImage(ImageView imageView, String path) {
        GlideApp.with(imageView)
                .load(path)
                .into(imageView);
    }

    @Override
    public void displayFileVideo(String path) {

    }

    @Override
    public Class<?> displayFullImageClass() {
        return null;
    }
}
