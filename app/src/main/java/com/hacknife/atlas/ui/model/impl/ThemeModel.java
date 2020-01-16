package com.hacknife.atlas.ui.model.impl;

import com.hacknife.atlas.R;
import com.hacknife.atlas.ui.base.impl.BaseModel;
import com.hacknife.atlas.ui.model.IThemeModel;
import com.hacknife.atlas.ui.viewmodel.IThemeViewModel;

import java.util.Arrays;

public class ThemeModel extends BaseModel<IThemeViewModel> implements IThemeModel {
    public ThemeModel(IThemeViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh() {
        Integer[] resIds = new Integer[]{
                R.color.aqua_colorPrimary,
                R.color.burlywood_colorPrimary,
                R.color.cadetblue_colorPrimary,
                R.color.chocolate_colorPrimary,
                R.color.coral_colorPrimary,
                R.color.cornflowerblue_colorPrimary,
                R.color.cyan_colorPrimary,
                R.color.darkmagenta_colorPrimary,
                R.color.darkolivegreen_colorPrimary,
                R.color.darkseagreen_colorPrimary,
                R.color.default_colorPrimary,
                R.color.fuchsia_colorPrimary,
                R.color.green_colorPrimary,
                R.color.green_colorPrimary,
                R.color.grey_colorPrimary,
                R.color.red_colorPrimary,
                R.color.yellow_colorPrimary
        };

        viewModel.refresh(Arrays.asList(resIds));
    }
}
