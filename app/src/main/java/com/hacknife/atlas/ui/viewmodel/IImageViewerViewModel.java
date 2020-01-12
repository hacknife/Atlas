package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.ui.base.IBaseViewModel;


public interface IImageViewerViewModel extends IBaseViewModel {

    void loadMore( );

    void loadMore(Images images);

    void refresh(String url);
}
