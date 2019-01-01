package com.example.dell.myapplication11.network;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Completable;
import rx.Observable;

public interface BaseApis {

    @GET
    Observable<ResponseBody> get(@Url String path);

    @POST
    Observable<ResponseBody> post(@Url String path,@QueryMap Map<String,String> map);

}
