package com.hacknife.atlas.http;




import com.hacknife.atlas.bean.DataSelector;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieInterceptor implements CookieJar {


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (!DataSelector.get().enableCookie()) {
            Logger.v( "关闭 Cookie");
            return;
        }Logger.v( "保存 Cookie");
        if (CookieManager.map().get(url.host()) == null) {
            CookieManager.map().put(url.host(), cookies);
        } else {
            List<Cookie> temp = new ArrayList<>();
            Map<String, Cookie> map = new HashMap<>();
            for (Cookie cookie : Objects.requireNonNull(CookieManager.map().get(url.host()))) {
                map.put(cookie.name(), cookie);
            }
            for (Cookie cookie : cookies) {
                map.put(cookie.name(), cookie);
            }
            for (Map.Entry<String, Cookie> entry : map.entrySet()) {
                temp.add(entry.getValue());
            }
            Objects.requireNonNull(CookieManager.map().get(url.host())).clear();
            Objects.requireNonNull(CookieManager.map().get(url.host())).addAll(temp);
        }
        Logger.i( "saveFromResponse: " + CookieManager.map().get(url.host()).toString());
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        Logger.v("CookieInterceptor  loadForRequest");
        return CookieManager.map().get(url.host()) == null ? new ArrayList<>() : CookieManager.map().get(url.host());
    }
}
