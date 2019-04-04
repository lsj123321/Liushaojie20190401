package com.bawei.liushaojie.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.liushaojie.R;
import com.bawei.liushaojie.model.bean.ShopBean;
import com.bawei.liushaojie.presenter.MianListPresenter;
import com.bawei.liushaojie.view.adapter.Mlss_Adapter;
import com.bawei.liushaojie.view.adapter.Pzsh_Adapter;
import com.bawei.liushaojie.view.adapter.Rxxp_Adapter;
import com.bawei.liushaojie.view.interfaces.IMainView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class Frag_show extends Fragment implements IMainView<ShopBean> {

    private MianListPresenter mianListPresenter;
    private Banner banner;
    private RecyclerView rxxp;
    private RecyclerView mlss;
    private RecyclerView pzsh;
    private Rxxp_Adapter rxxp_adapter;
    private Mlss_Adapter mlss_adapter;
    private ArrayList<String> list;
    private Pzsh_Adapter pzsh_adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_show,container,false);
        initView();
        initDdta();

        return view;
    }

    private void initDdta() {

        list = new ArrayList<String>();
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                DisplayImageOptions build = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisc(true)
                        .build();
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path,imageView,build);

            }
        });
        banner.setImages(list);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.start();
        mianListPresenter = new MianListPresenter();
        mianListPresenter.setView(this);
        mianListPresenter.loadNetData();
        //绑定布局 设定样式
        LinearLayoutManager rxxp_linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rxxp.setLayoutManager(rxxp_linearLayoutManager);
        LinearLayoutManager mlss_linearLayoutManager = new LinearLayoutManager(getActivity());
        mlss.setLayoutManager(mlss_linearLayoutManager);
        GridLayoutManager pzsh_gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        pzsh.setLayoutManager(pzsh_gridLayoutManager);

        rxxp_adapter = new Rxxp_Adapter(getActivity());
        rxxp.setAdapter(rxxp_adapter);
        rxxp_adapter.notifyDataSetChanged();
        mlss_adapter = new Mlss_Adapter(getActivity());
        mlss.setAdapter(mlss_adapter);
        mlss_adapter.notifyDataSetChanged();
        pzsh_adapter = new Pzsh_Adapter(getActivity());
        pzsh.setAdapter(pzsh_adapter);
        pzsh_adapter.notifyDataSetChanged();
    }

    private void initView() {
        banner = view.findViewById(R.id.banner);
        rxxp = view.findViewById(R.id.rxxp);
        mlss = view.findViewById(R.id.mlss);
        pzsh = view.findViewById(R.id.pzsh);

    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        Log.e("myMessage",shopBean+"");
        rxxp_adapter.setArr(shopBean.getResult().getRxxp().getCommodityList());
        mlss_adapter.setArr(shopBean.getResult().getMlss().getCommodityList());
        pzsh_adapter.setArr(shopBean.getResult().getPzsh().getCommodityList());
    }

    @Override
    public void onErr(String errMsg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mianListPresenter.detachView();
    }
}
