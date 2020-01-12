package com.hacknife.atlas.http;

import com.hacknife.atlas.bean.AtlasResource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class HttpClient {

    private volatile static HttpClient sHttpClient;

    private Retrofit mRetrofit;

    private HttpClient() {
        mRetrofit = buildRetrofit("https://www.baidu.com/");
    }

    private static Retrofit buildRetrofit(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);


        builder.sslSocketFactory(HttpHelper.createSSLSocketFactory());
        builder.hostnameVerifier(new HttpHelper.TrustAllHostnameVerifier());
//            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", Integer.parseInt("8888"))));


        builder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400")
                    .build();
            return chain.proceed(request);
        });
        if (false) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.addInterceptor(chain -> {
            Response resp = chain.proceed(chain.request());
            List<String> cookies = resp.headers("Set-Cookie");
            if (cookies != null && cookies.size() > 0)
                for (String cookie : cookies)
                    Cookie.putCookie(cookie);
            return resp;
        });

        return new Retrofit.Builder().client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    private static HttpClient getInstance() {
        if (sHttpClient == null) {
            synchronized (HttpClient.class) {
                if (sHttpClient == null) {
                    sHttpClient = new HttpClient();
                }
            }
        }
        return sHttpClient;
    }


    public static <T> T create(Class<T> service) {
        return getInstance().mRetrofit.create(service);
    }

    public static void refresh() {
        getInstance().mRetrofit = buildRetrofit(AtlasResource.get().atlas);
    }
}
