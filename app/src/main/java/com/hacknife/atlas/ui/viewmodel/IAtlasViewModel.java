package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.ui.base.IBaseViewModel;

import java.util.List;


public interface IAtlasViewModel extends IBaseViewModel {

    void atlas(List<Atlas> atlases);

    void loadMore();

    void refresh();

    void refresh(List<Atlas> atlases);

}
