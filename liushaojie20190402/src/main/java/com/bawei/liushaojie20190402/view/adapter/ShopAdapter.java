package com.bawei.liushaojie20190402.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private Context context;
    private List<ShopBean.DataBean> arr = new ArrayList<>();
    MainActivity mainActivity;

    private CheckBox mCheckBox;

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

    public ShopAdapter(Context context) {
        this.context = context;

        mainActivity= (MainActivity) context;
    }

    public void setArr(List<ShopBean.DataBean> list) {
        if (list != null) {
            arr.addAll(list);
            notifyDataSetChanged();
        }
    }
    //全选
    private void CheckAll(boolean check){
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).groupCK=check;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (arr.get(i) != null) {
            myViewHolder.name.setText(arr.get(i).getSellerName());
            myViewHolder.checkBox.setChecked(arr.get(i).groupCK);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            myViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
            ShopAdapter_child shopAdapter_child=new ShopAdapter_child(context,arr.get(i).getList());
            shopAdapter_child.setCheckBox(myViewHolder.checkBox);
            myViewHolder.recyclerView.setAdapter(shopAdapter_child);
        }

    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final CheckBox checkBox;
        private final RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_group);
            checkBox = itemView.findViewById(R.id.check_group);
            recyclerView = itemView.findViewById(R.id.recycler_child);
        }
    }


}
