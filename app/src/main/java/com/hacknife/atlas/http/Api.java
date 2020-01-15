package com.hacknife.atlas.http;


import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.helper.AppConfig;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {


    @GET
    Observable<String> url(@Url String url);

    @GET(AppConfig.ATLAS)
    Observable<List<DataSource>> atlas();

}
