package com.hacknife.atlas.http;

import com.hacknife.atlas.bean.Atlas;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {


    @GET("page")
    Observable<List<Atlas>> page(@Query("page") int page);

    @GET("refresh")
    Observable<List<Atlas>> refresh();
}
