package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.ImageCollection;
import com.hacknife.atlas.ui.base.IBaseViewModel;


public interface IImageViewModel extends IBaseViewModel {

    void refresh(Atlas url);

    void loadMore();

    void loadMore(ImageCollection images);

    void refresh(ImageCollection images);
}
