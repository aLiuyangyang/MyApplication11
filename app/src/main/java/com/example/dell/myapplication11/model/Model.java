package com.example.dell.myapplication11.model;

import com.example.dell.myapplication11.network.MyCallback;

import java.util.Map;

public interface Model{
    void setRequestData(String path, Map<String,String> map, Class clazz, MyCallback myCallback);
}
