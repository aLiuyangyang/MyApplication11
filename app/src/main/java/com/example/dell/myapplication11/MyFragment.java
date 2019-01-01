package com.example.dell.myapplication11;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.myapplication11.fragment.FragmentShow;
import com.example.dell.myapplication11.fragment.FragmentShow1;

class MyFragment extends FragmentPagerAdapter {
    private String[] name=new String[]{"首页","详情"};

    public MyFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentShow();
            case 1:
                return new FragmentShow1();
                default:
                    return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
}

