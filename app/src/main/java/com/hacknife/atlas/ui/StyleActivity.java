package com.hacknife.atlas.ui;


import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.widget.SeekBar;

import androidx.databinding.DataBindingUtil;

import com.hacknife.atlas.R;
import com.hacknife.atlas.bean.MapStringInteger;
import com.hacknife.atlas.bean.MapStringIntegerLite;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.bus.StyleEvent;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IStyleView;
import com.hacknife.atlas.ui.viewmodel.impl.StyleViewModel;
import com.hacknife.atlas.ui.viewmodel.IStyleViewModel;
import com.hacknife.atlas.databinding.ActivityStyleBinding;
import com.hacknife.onlite.OnLiteFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class StyleActivity extends BaseActivity<IStyleViewModel, ActivityStyleBinding> implements IStyleView {

    @Override
    protected IStyleViewModel performViewModel() {
        return new StyleViewModel(this, dataBinding);
    }

    @Override
    protected ActivityStyleBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_style);
    }

    @Override
    protected void init() {
        TypedValue primary = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, primary, true);
        int colorPrimary = getResources().getColor(primary.resourceId);


        TypedValue primaryDark = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, primaryDark, true);
        int colorPrimaryDark = getResources().getColor(primaryDark.resourceId);


        if (AppConfig.styleCollection == 0) {
            dataBinding.ivCollection4x4.setColorFilter(colorPrimaryDark);
            dataBinding.ivCollection9x9.setColorFilter(colorPrimary);
        } else {
            dataBinding.ivCollection9x9.setColorFilter(colorPrimaryDark);
            dataBinding.ivCollection4x4.setColorFilter(colorPrimary);
        }

        if (AppConfig.styleDetail == 0) {
            dataBinding.ivDetail4x4.setColorFilter(colorPrimaryDark);
            dataBinding.ivDetail9x9.setColorFilter(colorPrimary);
        } else {
            dataBinding.ivDetail9x9.setColorFilter(colorPrimaryDark);
            dataBinding.ivDetail4x4.setColorFilter(colorPrimary);
        }
        dataBinding.ivBack.setOnClickListener(view -> onBackPressed());
        dataBinding.ivCollection4x4.setOnClickListener(view -> {
            AppConfig.styleCollection = 0;
            dataBinding.ivCollection4x4.setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
            dataBinding.ivCollection9x9.setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
            Observable.just(0)
                    .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                            .updataOrInsert(new MapStringInteger(Constant.KEY_STYLE_COLLECTION, integer), new MapStringInteger(Constant.KEY_STYLE_COLLECTION, null)))
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<>(disposable));
            RxBus.post(StyleEvent.COLLECTION);
        });
        dataBinding.ivCollection9x9.setOnClickListener(view -> {
            dataBinding.ivCollection9x9.setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
            dataBinding.ivCollection4x4.setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
            AppConfig.styleCollection = 1;
            Observable.just(1)
                    .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                            .updataOrInsert(new MapStringInteger(Constant.KEY_STYLE_COLLECTION, integer), new MapStringInteger(Constant.KEY_STYLE_COLLECTION, null)))
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<>(disposable));
            RxBus.post(StyleEvent.COLLECTION);
        });
        dataBinding.ivDetail4x4.setOnClickListener(view -> {
            AppConfig.styleDetail = 0;
            dataBinding.ivDetail4x4.setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
            dataBinding.ivDetail9x9.setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
            Observable.just(0)
                    .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                            .updataOrInsert(new MapStringInteger(Constant.KEY_STYLE_DETAIL, integer), new MapStringInteger(Constant.KEY_STYLE_DETAIL, null)))
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<>(disposable));
        });
        dataBinding.ivDetail9x9.setOnClickListener(view -> {
            AppConfig.styleDetail = 1;
            dataBinding.ivDetail9x9.setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
            dataBinding.ivDetail4x4.setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
            Observable.just(0)
                    .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                            .updataOrInsert(new MapStringInteger(Constant.KEY_STYLE_DETAIL, integer), new MapStringInteger(Constant.KEY_STYLE_DETAIL, null)))
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<>(disposable));
        });
        dataBinding.seekBar.setProgress(AppConfig.styleCollectionHeight - 30);
        dataBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                AppConfig.styleCollectionHeight = seekBar.getProgress() + 30;
                RxBus.post(StyleEvent.HEIGHT);
                Observable.just(seekBar.getProgress() + 30)
                        .doOnNext(integer -> OnLiteFactory.create(MapStringIntegerLite.class)
                                .updataOrInsert(new MapStringInteger(Constant.KEY_STYLE_COLLECTION_HEIGHT, integer), new MapStringInteger(Constant.KEY_STYLE_COLLECTION_HEIGHT, null)))
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Consumer<>(disposable));
            }
        });
    }


}
