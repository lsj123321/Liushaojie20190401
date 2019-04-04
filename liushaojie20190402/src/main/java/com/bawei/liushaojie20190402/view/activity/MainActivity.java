package com.bawei.liushaojie20190402.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.liushaojie20190402.R;
import com.bawei.liushaojie20190402.model.bean.ShopBean;
import com.bawei.liushaojie20190402.presenter.MianListPresenter;
import com.bawei.liushaojie20190402.view.adapter.ShopAdapter;
import com.bawei.liushaojie20190402.view.interfaces.IMainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView<ShopBean> {

    private ShopAdapter shopAdapter;
    private RecyclerView recyclerView;
    private TextView price;
    private CheckBox check_all;
    private List<ShopBean.DataBean> list=new ArrayList<>();
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //查找资源id
        recyclerView = findViewById(R.id.recycler);
        price = findViewById(R.id.price_main);
        check_all = findViewById(R.id.check_All);


        //绑定数据
        MianListPresenter mianListPresenter = new MianListPresenter();
        mianListPresenter.setView(this);
        mianListPresenter.loadNetData();
        //绑定适配器样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //适配器
        shopAdapter = new ShopAdapter(this);
        recyclerView.setAdapter(shopAdapter);
        shopAdapter.setCheckBox(check_all);
        shopAdapter.notifyDataSetChanged();

        //initData();
    }

    private void initData() {
        //全选
        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check=check_all.isChecked();
                Log.e("myMessage",check+"");
                for (int i = 0; i < list.size(); i++) {
                    Log.e("myMessage",list.get(i).groupCK+"1");
                    list.get(i).groupCK=check;//选中组
                    for (int j = 0; j < list.get(i).getList().size(); j++) {
                        list.get(i).getList().get(j).childCK=check;//选中自条目
                    }
                }
                shopAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        shopAdapter.setArr(shopBean.getData());
        Log.e("myMessage",shopBean+"");
    }

    @Override
    public void onErr(String errMessage) {

    }
}
