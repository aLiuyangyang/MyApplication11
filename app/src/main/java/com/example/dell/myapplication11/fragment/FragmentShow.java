package com.example.dell.myapplication11.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication11.Main2Activity;
import com.example.dell.myapplication11.R;
import com.example.dell.myapplication11.bean.MsgBase;
import com.example.dell.myapplication11.bean.XingBase;
import com.example.dell.myapplication11.network.Apis;
import com.example.dell.myapplication11.presenter.Presenterlmpl;
import com.example.dell.myapplication11.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentShow extends Fragment implements IView,View.OnClickListener{
    private Presenterlmpl presenterlmpl;
    private int pid;
    private SimpleDraweeView show_simple;
    private TextView show_text;
    private  Button but_name;
    private XingBase.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        pid = ((Main2Activity) getActivity()).setName();
        presenterlmpl = new Presenterlmpl(this);
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid+"");
        presenterlmpl.setRequestData(Apis.MY_XIANG,map,XingBase.class);
    }

    @Override
    public void setSuccess(Object data) {
        if (data instanceof XingBase) {
            XingBase xingBase = (XingBase) data;
            data1 = xingBase.getData();
            show_text.setText(data1.getTitle());
            String[] split = data1.getImages().split("\\|");
            Uri uri = Uri.parse(split[0]);
            show_simple.setImageURI(uri);
        }
    }

    @Override
    public void setFail(Throwable ex) {

    }

    private void initView(View view) {
        show_simple = (SimpleDraweeView) view.findViewById(R.id.show_simple);
        show_text = (TextView) view.findViewById(R.id.show_text);
        but_name=view.findViewById(R.id.but_name);
        but_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_name:
                EventBus.getDefault().postSticky(new MsgBase(data1.getTitle(),1));
                Toast.makeText(getContext(),data1.getTitle(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
