package com.hacknife.atlas.app;

import android.app.Application;

import com.hacknife.atlas.R;
import com.hacknife.atlas.helper.ScreenHelper;
import com.hacknife.imagepicker.ImagePicker;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


public class AtlasApplication extends Application {

    public static int width;
    public static final int SPACE = 20;
    public static final int PAGE_SIZE = 15;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new FlyRefreshHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePicker.getInstance().imageLoader(new PickerImageLoader());
        ImagePicker.getInstance().shareView(true);

    }


}
