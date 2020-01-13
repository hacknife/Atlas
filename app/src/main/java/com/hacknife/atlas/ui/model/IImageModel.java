package com.hacknife.atlas.ui.model;


import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.ui.base.IBaseModel;

public interface IImageModel extends IBaseModel {

    void loadMore(Images nextPage);

}
