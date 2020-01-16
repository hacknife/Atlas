package com.hacknife.atlas.ui.viewmodel;


import com.hacknife.atlas.ui.base.IBaseViewModel;

import java.util.List;


public interface IThemeViewModel extends IBaseViewModel {

    void refresh();

    void refresh(List<Integer> asList);
}
