package com.hacknife.atlas.ui.model.impl;

import com.hacknife.atlas.R;
import com.hacknife.atlas.bean.MapStringInteger;
import com.hacknife.atlas.bean.MapStringIntegerLite;
import com.hacknife.atlas.bean.Theme;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IThemeModel;
import com.hacknife.atlas.ui.viewmodel.IThemeViewModel;
import com.hacknife.onlite.OnLiteFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.hacknife.atlas.helper.Constant.primaryColor;

public class ThemeModel extends BaseModel<IThemeViewModel> implements IThemeModel {
    public ThemeModel(IThemeViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh() {
        Observable.just(primaryColor)
                .map(integers -> {
                    List<MapStringInteger> map = OnLiteFactory.create(MapStringIntegerLite.class).select(new MapStringInteger(Constant.KEY_THEME, null));
                    MapStringInteger stringInteger = map.size() > 0 ? map.get(0) : null;
                    List<Theme> themes = new ArrayList<>(integers.length);
                    for (Integer integer : integers) {
                        themes.add(new Theme(integer, 0));
                    }
                    for (int i = 0; i < integers.length; i++) {
                        if (stringInteger != null && stringInteger.getValue() == i) {
                            themes.get(i).setIsChecked(1);
                            return themes;
                        }
                    }
                    themes.get(0).setIsChecked(1);
                    return themes;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Theme>>(disposable) {
                    @Override
                    public void onNext(List<Theme> themes) {
                        viewModel.refresh(themes);
                    }
                });

    }


}
