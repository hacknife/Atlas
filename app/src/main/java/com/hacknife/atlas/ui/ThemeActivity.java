package com.hacknife.atlas.ui;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.ThemeAdapter;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IThemeView;
import com.hacknife.atlas.ui.viewmodel.impl.ThemeViewModel;
import com.hacknife.atlas.ui.viewmodel.IThemeViewModel;
import com.hacknife.atlas.databinding.ActivityThemeBinding;

public class ThemeActivity extends BaseActivity<IThemeViewModel, ActivityThemeBinding> implements IThemeView {

    @Override
    protected IThemeViewModel performViewModel() {
        return new ThemeViewModel(this, dataBinding);
    }

    @Override
    protected ActivityThemeBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_theme);
    }

    @Override
    protected void init() {
        ThemeAdapter adapter = new ThemeAdapter();
        dataBinding.rcTheme.setAdapter(adapter);
        dataBinding.rcTheme.setLayoutManager(new GridLayoutManager(this, 4));
        dataBinding.rcTheme.addItemDecoration(new StaggeredDividerItemDecoration(4, 20, true));
        dataBinding.refresh.setNoMoreData(true);
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(view->{});
        dataBinding.refresh.autoRefresh();
    }
}
