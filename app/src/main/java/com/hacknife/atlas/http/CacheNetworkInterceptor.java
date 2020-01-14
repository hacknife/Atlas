package com.hacknife.atlas.http;

import android.util.Log;

import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheNetworkInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        //无缓存,进行缓存
//        if (AppConfig.ATLAS.contains(chain.request().url().host())) {
//            Log.v("dzq", "关闭缓存");
//            return chain.proceed(chain.request().newBuilder().build()).newBuilder().build();
//
//        } else
            return chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    //对请求进行最大60秒的缓存
                    .addHeader("Cache-Control", "max-age=" + AppConfig.HTTP_CACHE_TIME)
                    .build();
    }
}

