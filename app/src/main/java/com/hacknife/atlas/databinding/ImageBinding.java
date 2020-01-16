package com.hacknife.atlas.databinding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;


import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hacknife.atlas.R;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.service.DownloadService;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ImageBinding {
    @BindingAdapter("app:imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        GlideApp.with(imageView)
                .load(url)
                .into(imageView);

    }


    @BindingAdapter("app:image")
    public static void setImage(ImageView imageView, Image image) {
//        Logger.json(image.toString());
        GlideApp.with(imageView)
                .asBitmap()
                .load(image.getUrl())
                .placeholder(R.drawable.placeholder)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (image.getHeight() == null || image.getHeight() == 0) {
                            int width = (AppConfig.width - 4 * AppConfig.SPACE) / 2;
                            int height = (int) (resource.getHeight() / (1f * resource.getWidth()) * width);
                            image.setWidth(width);
                            image.setHeight(height);
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            params.width = width;
                            params.height = height;
                            imageView.setLayoutParams(params);
                            imageView.getContext().startService(new Intent(imageView.getContext(), DownloadService.class));
                            Observable.just(image)
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe(image1 -> RxBus.post(image1));
                        }
                        return false;
                    }
                })
                .into(imageView);

    }

    @BindingAdapter("app:stateChecked")
    public static void setStateChecked(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    @BindingAdapter("app:show")
    public static void setState(View view, Integer integer) {
        view.setVisibility(integer == null ? View.GONE : (integer == 1 ? View.VISIBLE : View.GONE));
    }

    @BindingAdapter("app:bgColor")
    public static void setBgColor(View view, Integer integer) {
        view.setBackgroundColor(view.getResources().getColor(integer));
    }
}
