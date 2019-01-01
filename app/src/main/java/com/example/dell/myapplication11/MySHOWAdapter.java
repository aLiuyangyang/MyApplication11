package com.example.dell.myapplication11;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication11.bean.ShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class MySHOWAdapter extends RecyclerView.Adapter<MySHOWAdapter.ViewHodelr>{
    private List<ShowBean.DataBean> list=new ArrayList<>();
    private Context context;

    public MySHOWAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ShowBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MySHOWAdapter.ViewHodelr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new ViewHodelr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySHOWAdapter.ViewHodelr viewHodelr, final int i) {
        viewHodelr.item_text.setText(list.get(i).getTitle());
        String[] split = list.get(i).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        viewHodelr.item_simple.setImageURI(uri);
        viewHodelr.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.cilck(list.get(i).getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodelr extends RecyclerView.ViewHolder {
         @BindView(R.id.item_simple)
        SimpleDraweeView item_simple;
         @BindView(R.id.item_text)
        TextView item_text;
        public ViewHodelr(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick{
        void cilck(int pid);
    }
}
