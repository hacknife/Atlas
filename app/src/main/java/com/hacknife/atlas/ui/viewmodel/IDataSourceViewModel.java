package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.ui.base.IBaseViewModel;

import java.util.List;


public interface IDataSourceViewModel extends IBaseViewModel {

    void refresh();

    void refresh(List<DataSource> atlas);
}
