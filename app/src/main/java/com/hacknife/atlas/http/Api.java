package com.hacknife.atlas.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {


    @GET
    Observable<String> url(@Url String url);

}
