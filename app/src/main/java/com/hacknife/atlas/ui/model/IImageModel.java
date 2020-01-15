package com.hacknife.atlas.ui.model;


import com.hacknife.atlas.bean.ImageCollection;
import com.hacknife.atlas.ui.base.IBaseModel;

public interface IImageModel extends IBaseModel {

    void loadMore(ImageCollection nextPage);

    void refresh(ImageCollection images);
}
