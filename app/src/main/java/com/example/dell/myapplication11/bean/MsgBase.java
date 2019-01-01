package com.example.dell.myapplication11.bean;

public class MsgBase {
    private Object object;
    private int flag;

    public MsgBase(Object object, int flag) {
        this.object = object;
        this.flag = flag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
