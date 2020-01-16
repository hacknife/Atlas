package com.hacknife.atlas.helper;

import com.hacknife.atlas.R;

public class ThemeHelper {
    public static int primaryColorTtheme(int resid){
        switch (resid){
            case R.color.aqua_colorPrimary:
                return R.style.AppThemeAqua;
            case R.color.burlywood_colorPrimary :
                return R.style.AppThemeBurlyWood ;
            case R.color.cadetblue_colorPrimary :
                return R.style.AppThemeCadetBlue ;
            case R.color. chocolate_colorPrimary:
                return R.style.AppThemeChocolate ;
            case R.color. coral_colorPrimary:
                return R.style.AppThemeCoral ;
            case R.color. cornflowerblue_colorPrimary:
                return R.style.AppThemeCornflowerBlue ;
            case R.color.cyan_colorPrimary :
                return R.style.AppThemeCyan ;
            case R.color. darkmagenta_colorPrimary:
                return R.style.AppThemeDarkMagenta ;
            case R.color. darkolivegreen_colorPrimary:
                return R.style.AppThemeDarkOliveGreen ;
            case R.color. darkseagreen_colorPrimary:
                return R.style.AppThemeDarkSeaGreen ;
            case R.color. default_colorPrimary:
                return R.style.AppThemeDefault ;
            case R.color. fuchsia_colorPrimary:
                return R.style.AppThemeFuchsia ;
            case R.color. green_colorPrimary:
                return R.style.AppThemeGreen ;
            case R.color. grey_colorPrimary:
                return R.style.AppThemeGrey ;
            case R.color. red_colorPrimary:
                return R.style.AppThemeRed ;
            case R.color. yellow_colorPrimary:
                return R.style.AppThemeYellow ;


        }

        return 0;
    }
}
