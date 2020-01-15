package com.hacknife.atlas.app;

import android.app.Application;
import android.content.Intent;

import com.hacknife.atlas.R;
import com.hacknife.atlas.bean.DataSelector;
import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.bean.DataSourceLite;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.imagepicker.ImagePicker;
import com.hacknife.onlite.OnLiteFactory;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class AtlasApplication extends Application {


    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new BezierCircleHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));
        ImagePicker.getInstance().shareView(false);
        OnLiteFactory.init(AppConfig.ONLITE);
        Observable.just(OnLiteFactory.create(DataSourceLite.class))
                .map(lite -> lite.select(null))
                .flatMap((Function<List<DataSource>, Observable<DataSource>>) list -> Observable.fromArray(list.toArray(new DataSource[]{})))
                .filter(lite -> lite.checked())
                .subscribe(DataSelector::init);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePicker.getInstance().imageLoader(new PickerImageLoader());
        ImagePicker.getInstance().shareView(true);
        startService(new Intent(this, DownloadService.class));
    }


}
