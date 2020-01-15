package com.hacknife.atlas.http;

import com.hacknife.atlas.bean.DataSelector;
import com.hacknife.atlas.helper.AppConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(TrustAllFactory.createSSLSocketFactory())
                .hostnameVerifier(new TrustAllFactory.TrustAllHostnameVerifier())
                .addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheNetworkInterceptor())
                .cookieJar(new CookieInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .cache(new Cache(new File(AppConfig.OKHTTP_CACHE_FILE), AppConfig.OKHTTP_CACHE_SIZE));

        if (true) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }


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
        getInstance().mRetrofit = buildRetrofit(DataSelector.get().atlas);
    }
}
