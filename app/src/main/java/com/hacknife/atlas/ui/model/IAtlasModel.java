package com.hacknife.atlas.ui.model;


import com.hacknife.atlas.ui.base.IBaseModel;

public interface IAtlasModel extends IBaseModel {

    void loadMore(int page);

    void refresh();

}
