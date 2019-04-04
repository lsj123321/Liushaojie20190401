package com.bawei.liushaojie20190402.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liushaojie20190402.R;
import com.bawei.liushaojie20190402.model.bean.ShopBean;
import com.bawei.liushaojie20190402.view.activity.MainActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter_child extends RecyclerView.Adapter<ShopAdapter_child.MyViewHolder> {
    private Context mcontext;
    private List<ShopBean.DataBean.ListBean> arr ;
    MainActivity mainActivity;
    private CheckBox mCheckBox;


    public ShopAdapter_child(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.arr = list;
        this.mcontext = context;
        mainActivity = (MainActivity) context;
    }
    public void setCheckBox(CheckBox checkBox) {
        this.mCheckBox = checkBox;
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox ck= (CheckBox) v;
                CheckAll(ck.isChecked());
            }
        });
    }
    //全选
    private void CheckAll(boolean check){
        List<ShopBean.DataBean> list=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
                check=list.get(i).getList().get(i).childCK;
                arr.get(i).childCK=check;
        }
        notifyDataSetChanged();
    }
/*
    public void setArr(List<ShopBean.DataBean.ListBean> list) {
        if (list != null) {
            arr.addAll(list);
            notifyDataSetChanged();
        }
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_child, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (arr.get(i) != null) {
            myViewHolder.name.setText(arr.get(i).getTitle());
            myViewHolder.price.setText(arr.get(i).getPrice() + "");
            myViewHolder.checkBox.setChecked(arr.get(i).childCK);
            Glide.with(mcontext).load(arr.get(i).getImages()).into(myViewHolder.img);


        }

    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, price;
        private final CheckBox checkBox;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            checkBox = itemView.findViewById(R.id.check);
            img = itemView.findViewById(R.id.img);
        }
    }
}
