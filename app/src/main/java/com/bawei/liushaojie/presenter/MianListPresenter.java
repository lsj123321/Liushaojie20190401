package com.bawei.liushaojie.presenter;

import com.bawei.liushaojie.app.Contant;
import com.bawei.liushaojie.model.bean.ShopBean;
import com.bawei.liushaojie.model.http.HttpUtils;
import com.bawei.liushaojie.view.interfaces.IMainView;

public class MianListPresenter extends BasePresenter<IMainView<ShopBean>> {

    private final HttpUtils httpUtils;
    public MianListPresenter(){
        httpUtils = HttpUtils.getHttpUtilsInstance();
    }
    public void loadNetData(){
        httpUtils.getData(Contant.SHOP_URL, ShopBean.class, new HttpUtils.CallBackData<ShopBean>() {

            @Override
            public void onResponce(ShopBean shopBean) {
                if (getView()!=null){
                    getView().onSuccess(shopBean);
                }
            }

            @Override
            public void onFail(String err) {
                getView().onErr(err);
            }
        });
    }
}
