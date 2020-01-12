package com.hacknife.atlas.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

@GlideModule
public class Glide extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        builder.setDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC));
//        builder.setDiskCache(new DiskLruCacheFactory("/sdcard/Android/data/com.hacknife.atlas/glide/", 1024 * 1024 * 100));
         super.applyOptions(context, builder);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull com.bumptech.glide.Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());

    }
}