package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.DataSelector;

public class StringHelper {
    public static String link(String parentUrl, String url, boolean isWeb) {
        if (url == null || url.length() == 0) return "";
        if (url.startsWith("/")) {
            if (isWeb)
                return DataSelector.get().webHost + url;
            else
                return DataSelector.get().imageHost + url;
        }
        if (parentUrl != null) return parentUrl.substring(0, parentUrl.lastIndexOf("/") + 1) + url;
        return url;
    }
}
