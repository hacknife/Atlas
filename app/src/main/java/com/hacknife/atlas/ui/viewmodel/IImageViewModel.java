package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.ui.base.IBaseViewModel;


public interface IImageViewModel extends IBaseViewModel {

    void refresh(String url);

    void loadMore();

    void loadMore(Images images);
}
