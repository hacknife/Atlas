package com.hacknife.atlas.databinding;

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
import com.hacknife.atlas.bean.ImageSize;
import com.hacknife.atlas.glide.GlideApp;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.ImageSizeContainer;

public class ImageBinding {
    @BindingAdapter("app:imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        GlideApp.with(imageView)
                .load(url)
                .into(imageView);

    }

    @BindingAdapter("app:imgUrlWithCache")
    public static void setImageUrlWithCache(ImageView imageView, String url) {
        GlideApp.with(imageView)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (!ImageSizeContainer.size().containsKey(url)) {
                            int width = AppConfig.width / 2;
                            int height = (int) (resource.getHeight() / (1f * resource.getWidth()) * width);
                            ImageSizeContainer.size().put(url, new ImageSize(width, height));
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            params.width = width;
                            params.height = height;
                            imageView.setLayoutParams(params);
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
}
