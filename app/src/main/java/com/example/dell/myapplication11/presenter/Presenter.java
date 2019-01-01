package com.example.dell.myapplication11.presenter;

import com.example.dell.myapplication11.network.MyCallback;

import java.util.Map;

public interface Presenter {
    void setRequestData(String path, Map<String,String> map, Class clazz);
}

