package com.hacknife.atlas.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

public class CookieManager {
    private static CookieManager manager;
    private Map<String, List<Cookie>> map;

    public CookieManager() {
        map = new HashMap<>();
    }

    private static CookieManager get() {
        if (manager == null)
            synchronized (CookieManager.class) {
                if (manager == null)
                    manager = new CookieManager();
            }
        return manager;
    }

    public static Map<String, List<Cookie>> map( ) {
        return CookieManager.get().map;
    }


    public static void clear() {
        CookieManager.get().map.clear();
    }
}
