package com.example.dell.myapplication11.model;

import com.example.dell.myapplication11.network.MyCallback;
import com.example.dell.myapplication11.network.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

public class Modellmpl implements Model{
    @Override
    public void setRequestData(String path, final Map<String, String> map, final Class clazz, final MyCallback myCallback) {
        RetrofitManager.getInstance().post(path,map,new RetrofitManager.HttpLiener() {
            @Override
            public void setSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                myCallback.setSuccess(o);
            }

            @Override
            public void setFall(Throwable ex) {
                myCallback.setFail(ex);
            }
        });
    }
}
