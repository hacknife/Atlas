package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.DataSelector;

public class StringHelper {
    public static String link(String parentUrl, String url) {
        if (url == null || url.length() == 0) return "";
        if (url.startsWith("/")) return DataSelector.get().host + url;
        if (parentUrl != null) return parentUrl.substring(0, parentUrl.lastIndexOf("/") + 1) + url;
        return url;
    }
}
