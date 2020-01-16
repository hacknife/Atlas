package com.hacknife.atlas.ui;


import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.ThemeAdapter;
import com.hacknife.atlas.adapter.base.OnItemClickListener2;
import com.hacknife.atlas.bean.MapStringInteger;
import com.hacknife.atlas.bean.MapStringIntegerLite;
import com.hacknife.atlas.bean.Theme;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.bus.ThemeEvent;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.ThemeHelper;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IThemeView;
import com.hacknife.atlas.ui.viewmodel.impl.ThemeViewModel;
import com.hacknife.atlas.ui.viewmodel.IThemeViewModel;
import com.hacknife.atlas.databinding.ActivityThemeBinding;
import com.hacknife.onlite.OnLiteFactory;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        dataBinding.ivBack.setOnClickListener(view -> onBackPressed());
        ThemeAdapter adapter = new ThemeAdapter();
        dataBinding.rcTheme.setAdapter(adapter);
        dataBinding.rcTheme.setLayoutManager(new GridLayoutManager(this, 4));
        dataBinding.rcTheme.addItemDecoration(new StaggeredDividerItemDecoration(4, AppConfig.SPACE, true));
        dataBinding.refresh.setOnRefreshListener(v -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(view -> {
        });
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener2<Theme>) (theme, last, current, view) -> {
                    adapter.data().get(last).setIsChecked(0);
                    adapter.data().get(current).setIsChecked(1);
                    adapter.notifyItemChanged(last);
                    adapter.notifyItemChanged(current);
                    AppConfig.theme = ThemeHelper.primaryColorTtheme(theme.getResId());
                    RxBus.post(new ThemeEvent());
                    Observable.just(adapter.data().get(current))
                            .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                                    .updataOrInsert(new MapStringInteger(Constant.KEY_THEME, current), new MapStringInteger(Constant.KEY_THEME, null)))
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Consumer<>(disposable));
                    return true;

                }
        );
    }
}
