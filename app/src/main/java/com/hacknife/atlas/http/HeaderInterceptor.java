package com.hacknife.atlas.http;

import com.hacknife.atlas.bean.AtlasResource;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        for (String header : AtlasResource.get().headers) {
            builder.header(header.split(":")[0], header.split(":")[1]);
        }
        if (chain == null) {
            System.out.println("chain ==null");
        } else {
            System.out.println("chain !=null");
        }
        if (builder == null) {
            System.out.println("builder ==null");
        } else {
            System.out.println("builder !=null");
        }
        if (builder.build() == null) {
            System.out.println("build ==null");
        } else {
            System.out.println("build !=null");
        }
        return chain.proceed(builder.build());
    }
}
