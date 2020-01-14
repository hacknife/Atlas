package com.hacknife.atlas.app;

import android.app.Application;

import com.hacknife.atlas.R;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.ScreenHelper;
import com.hacknife.imagepicker.ImagePicker;
import com.hacknife.onlite.OnLiteFactory;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


public class AtlasApplication extends Application {


    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new BezierCircleHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));
        ImagePicker.getInstance().shareView(false);
        OnLiteFactory.init(AppConfig.ONLITE);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePicker.getInstance().imageLoader(new PickerImageLoader());
        ImagePicker.getInstance().shareView(true);
    }


}
