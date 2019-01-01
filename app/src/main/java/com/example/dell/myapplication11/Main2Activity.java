package com.example.dell.myapplication11;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        tab.setupWithViewPager(pager);
        pager.setAdapter(new MyFragment(getSupportFragmentManager()));
    }
    public int setName(){
        Intent intent1=getIntent();
        int pid = intent1.getIntExtra("pid", 0);
        return pid;
    }
}
