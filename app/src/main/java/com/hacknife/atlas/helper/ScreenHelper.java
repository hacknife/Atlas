package com.hacknife.atlas.helper;

import android.app.Activity;

import android.util.DisplayMetrics;

public class ScreenHelper {
    public static int width(Activity context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
