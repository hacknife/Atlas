package com.hacknife.atlas.http;

import android.util.Log;

import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response resp;
        Request req;
        if (true) {
            //有网络,检查10秒内的缓存
            req = chain.request()
                    .newBuilder()
                    .cacheControl(new CacheControl
                            .Builder()
                            .maxAge(AppConfig.HTTP_CACHE_TIME, TimeUnit.SECONDS)
                            .build())
                    .build();
        } else {
            //无网络,检查30天内的缓存,即使是过期的缓存
            req = chain.request().newBuilder()
                    .cacheControl(new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(30, TimeUnit.DAYS)
                            .build())
                    .build();
        }
        if (AppConfig.ATLAS.contains(chain.request().url().host())) {
            resp = chain.proceed(chain.request().newBuilder().build());
            Log.v("dzq", "关闭缓存");
        } else
            resp = chain.proceed(req);
        return resp.newBuilder().build();
    }
}