package com.example.dell.myapplication11.presenter;

import com.example.dell.myapplication11.model.Modellmpl;
import com.example.dell.myapplication11.network.MyCallback;
import com.example.dell.myapplication11.view.IView;

import java.util.Map;

public class Presenterlmpl implements Presenter{
    private Modellmpl modellmpl;
    private IView iView;

    public Presenterlmpl(IView iView) {
        this.iView = iView;
        modellmpl=new Modellmpl();
    }

    @Override
    public void setRequestData(String path, Map<String, String> map, Class clazz) {
        modellmpl.setRequestData(path, map, clazz, new MyCallback() {
            @Override
            public void setSuccess(Object object) {
                iView.setSuccess(object);
            }

            @Override
            public void setFail(Throwable ex) {
               iView.setFail(ex);
            }
        });
    }
    public void setDel(){
        if (modellmpl!=null){
            modellmpl=null;
        }
        if (iView!=null){
            iView=null;
        }
    }
}
