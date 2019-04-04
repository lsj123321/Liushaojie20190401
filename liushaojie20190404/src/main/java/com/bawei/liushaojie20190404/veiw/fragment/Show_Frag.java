package com.bawei.liushaojie20190404.veiw.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bawei.liushaojie20190404.R;
import com.bawei.liushaojie20190404.model.bean.ShopBean;
import com.bawei.liushaojie20190404.presenter.ShopPresenter;
import com.bawei.liushaojie20190404.veiw.adapter.ShopAdapter;
import com.bawei.liushaojie20190404.veiw.interfaces.IMainView;

public class Show_Frag extends BaseFragment implements IMainView<ShopBean> {

    private ExpandableListView lv;
    private ShopPresenter shopPresenter;
    private ShopAdapter adapter;
    private CheckBox checkBox;
    private TextView price;

    @Override
    protected void initData() {
        //绑定P层 接收数据
        shopPresenter = new ShopPresenter();
        shopPresenter.setView(this);
        shopPresenter.loadDataNet();

        //适配器
        adapter = new ShopAdapter(getActivity());
        lv.setAdapter(adapter);
        adapter.setMcCheckBox(checkBox);
        adapter.setmTextView(price);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initView() {
        lv = getActivity().findViewById(R.id.list);
        checkBox = getActivity().findViewById(R.id.checkbox_show);
        price = getView().findViewById(R.id.price_show);
    }

    @Override
    protected int bindView() {
        return R.layout.show_frag;
    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        adapter.setArr(shopBean);
    }

    @Override
    public void onErr(String errMsg) {

    }
}
