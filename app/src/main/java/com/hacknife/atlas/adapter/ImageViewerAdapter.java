package com.hacknife.atlas.adapter;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.hacknife.atlas.bean.Image;
import com.hacknife.imagepicker.ImagePicker;
import com.hacknife.imagepicker.adapter.ImagePageAdapter;

import com.hacknife.imagepicker.photo.OnPhotoTapListener;
import com.hacknife.imagepicker.photo.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class ImageViewerAdapter extends PagerAdapter {

    public ImagePageAdapter.PhotoViewClickListener listener;
    private int mPosition;
    private ImagePicker imagePicker;
    private List<Image> images = new ArrayList<>();
    private AppCompatActivity mActivity;
    private boolean mIsFromViewr = false;
    private View currentView;

    public ImageViewerAdapter(AppCompatActivity activity, int position) {
        this.mActivity = activity;
        imagePicker = ImagePicker.getInstance();
        mPosition = position;
        mIsFromViewr = true;
    }

    public ImageViewerAdapter(AppCompatActivity activity) {
        this.mActivity = activity;
        imagePicker = ImagePicker.getInstance();
    }

    public void addData(List<Image> images) {
        this.images.addAll(images);
        notifyDataSetChanged();
    }

    public void setPhotoViewClickListener(ImagePageAdapter.PhotoViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(mActivity);

        Image image = images.get(position);

        if (mIsFromViewr)
            imagePicker.getImageLoader().displayUserImage(photoView, image.getUrl());
        else
            imagePicker.getImageLoader().displayFileImage(photoView, image.getUrl());
        photoView.setOnPhotoTapListener((view, x, y) -> {
            if (listener != null) listener.OnPhotoTapListener(view, x, y);
        });

        photoView.setOnOutsidePhotoTapListener(imageView -> mActivity.onBackPressed());
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    public interface PhotoViewClickListener {
        void OnPhotoTapListener(View view, float v, float v1);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentView = (View) object;
    }

}
