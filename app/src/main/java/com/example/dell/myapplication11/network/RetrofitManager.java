package com.example.dell.myapplication11.network;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager<T> {
    private static String MY_URL="http://www.zhaoapi.cn/product/";
    private static RetrofitManager retrofitManager;
    private final BaseApis baseApis;

    public static synchronized RetrofitManager getInstance(){
        if (retrofitManager==null){
            retrofitManager=new RetrofitManager();
        }
        return retrofitManager;
    }
    public RetrofitManager(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(MY_URL)
                .client(okHttpClient)
                .build();
        baseApis = retrofit.create(BaseApis.class);
    }
    public RetrofitManager get(String path,HttpLiener httpLiener){
             baseApis.get(path)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(getobserver(httpLiener));
             return retrofitManager;
    }
    public RetrofitManager post(String path, Map<String, String> map, HttpLiener httpLiener){
        if(map==null){
            map=new HashMap<>();
        }
        baseApis.post(path,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(httpLiener));
        return retrofitManager;
    }
     private Observer getobserver(final HttpLiener httpLiener){
          Observer observer=new Observer<ResponseBody>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {
                   if (httpLiener!=null){
                       httpLiener.setFall(e);
                   }
              }

              @Override
              public void onNext(ResponseBody responseBody) {
                  try {
                      String string = responseBody.string();
                      if (httpLiener!=null){
                          httpLiener.setSuccess(string);
                      }
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          };
          return observer;
     }

    private HttpLiener httpLiener;

    public void setHttpLiener(HttpLiener httpLiener) {
        this.httpLiener = httpLiener;
    }

    public interface HttpLiener{
        void setSuccess(String data);
        void setFall(Throwable ex);
    }
}
