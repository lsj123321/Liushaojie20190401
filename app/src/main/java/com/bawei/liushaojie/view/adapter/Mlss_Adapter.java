package com.bawei.liushaojie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liushaojie.R;
import com.bawei.liushaojie.model.bean.ShopBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Mlss_Adapter extends RecyclerView.Adapter<Mlss_Adapter.MyViewHolder> {
    private Context context;
    private List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> arr = new ArrayList<>();

    public Mlss_Adapter(Context context) {
        this.context = context;
    }

    public void setArr(List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        if (list != null) {
            arr.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (arr.get(i) != null) {
            myViewHolder.name.setText(arr.get(i).getCommodityName());
            myViewHolder.price.setText("￥:" + arr.get(i).getPrice());
            Glide.with(context).load(arr.get(i).getMasterPic()).into(myViewHolder.img);
        }

    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private final TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img2);
            name = itemView.findViewById(R.id.name2);
            price = itemView.findViewById(R.id.price2);
        }
    }
}
