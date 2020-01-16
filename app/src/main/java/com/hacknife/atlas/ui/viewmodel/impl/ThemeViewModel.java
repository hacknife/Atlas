package com.hacknife.atlas.ui.viewmodel.impl;

import com.hacknife.atlas.adapter.ThemeAdapter;
import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IThemeModel;
import com.hacknife.atlas.ui.model.impl.ThemeModel;
import com.hacknife.atlas.ui.view.IThemeView;
import com.hacknife.atlas.ui.viewmodel.IThemeViewModel;
import com.hacknife.atlas.databinding.ActivityThemeBinding;

import java.util.List;

public class ThemeViewModel extends BaseViewModel<IThemeView, IThemeModel, ActivityThemeBinding> implements IThemeViewModel {


    public ThemeViewModel(IThemeView view, ActivityThemeBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityThemeBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IThemeModel createModel() {
        return new ThemeModel(this);
    }


    @Override
    public void refresh() {
        model.refresh();
    }

    @Override
    public void refresh(List<Integer> asList) {
        ThemeAdapter adapter = ((ThemeAdapter) binding.rcTheme.getAdapter());
        if (adapter.data().size() == 0)
            adapter.bindData(asList);
        binding.refresh.setNoMoreData(true);
        binding.refresh.finishRefresh(1000);
    }
}
