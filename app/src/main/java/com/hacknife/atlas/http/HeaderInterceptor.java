package com.hacknife.atlas.http;

import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.AppConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        if (AppConfig.ATLAS.contains(builder.build().url().host())) {
            builder.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Accept-Encoding", "deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Cache-Control","max-age=0")
                    .header("Upgrade-Insecure-Requests","1");
        } else if (AtlasResource.get().headers != null)
            for (String header : AtlasResource.get().headers) {
                builder.header(header.split(":")[0], header.split(":")[1]);
            }
        return chain.proceed(builder.build());
    }
}
