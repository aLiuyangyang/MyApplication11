package com.example.dell.myapplication11.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication11.R;
import com.example.dell.myapplication11.bean.MsgBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentShow1 extends Fragment {
    @BindView(R.id.text)
    TextView text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show1,container,false);
        Log.i("aa","onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Log.i("aa","onViewCreated");
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setSou(MsgBase msgBase){
        Log.i("aa","setSou");
       if (msgBase.getFlag()==1){
           Toast.makeText(getContext(),msgBase.getObject().toString(),Toast.LENGTH_SHORT).show();
           text.setText(msgBase.getObject().toString());
           Log.i("aa",msgBase.getObject().toString());
       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("aa","onDestroy");
        EventBus.getDefault().unregister(this);

    }
}
