package com.example.dell.myapplication11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dell.myapplication11.bean.ShowBean;
import com.example.dell.myapplication11.network.Apis;
import com.example.dell.myapplication11.presenter.Presenterlmpl;
import com.example.dell.myapplication11.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {
    private Presenterlmpl presenterlmpl;
    private MySHOWAdapter adapter;
    private Button but;
    private RecyclerView recy;
    private RecyclerView recytwo;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenterlmpl = new Presenterlmpl(this);
        adapter = new MySHOWAdapter(this);
        linear();
        grid();
    }

    private void grid() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recytwo.setLayoutManager(linearLayoutManager);
        recytwo.setAdapter(adapter);
        load();
        adapter.setItemClick(new MySHOWAdapter.ItemClick() {
            @Override
            public void cilck(int pid) {
                Intent intent1=new Intent(MainActivity.this,Main2Activity.class);
                intent1.putExtra("pid",pid);
                startActivity(intent1);
            }
        });
    }

    private void linear() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);
        load();
        adapter.setItemClick(new MySHOWAdapter.ItemClick() {
            @Override
            public void cilck(int pid) {
                Intent intent1=new Intent();
                intent1.putExtra("pid",pid);
                startActivity(intent1);
            }
        });
    }

    private void load() {
        Map<String, String> map = new HashMap<>();
        map.put("keywords", "笔记本");
        map.put("page", 1 + "");
        presenterlmpl.setRequestData(Apis.MY_SHOW, map, ShowBean.class);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.but:
             if (i%2==0){
                 recy.setVisibility(View.VISIBLE);
                 recytwo.setVisibility(View.GONE);
             }else {
                 recy.setVisibility(View.GONE);
                 recytwo.setVisibility(View.VISIBLE);
             }
             i++;
             break;
     }
    }

    @Override
    public void setSuccess(Object data) {
        if (data instanceof ShowBean) {
            ShowBean showBean = (ShowBean) data;
            adapter.setList(showBean.getData());
        }
    }

    @Override
    public void setFail(Throwable ex) {

    }

    private void initView() {
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(this);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setOnClickListener(this);
        recytwo=findViewById(R.id.recy2);
        recytwo.setOnClickListener(this);
    }
}
