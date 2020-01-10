package com.hacknife.atlas.app;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class Glide extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        builder.setDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC));
//        builder.setDiskCache(new DiskLruCacheFactory("/sdcard/Android/data/com.hacknife.atlas/glide/", 1024 * 1024 * 100));
        super.applyOptions(context, builder);
    }
}