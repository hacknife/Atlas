package com.hacknife.atlas.http;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.hacknife.atlas.bean.AtlasResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieInterceptor implements CookieJar {


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (!AtlasResource.get().enableCookie()) {
            Log.v("dzq", "关闭 Cookie");
            return;
        }
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
        Log.i("dzq", "saveFromResponse: " + CookieManager.map().get(url.host()).toString());
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return CookieManager.map().get(url.host()) == null ? new ArrayList<>() : CookieManager.map().get(url.host());
    }
}
