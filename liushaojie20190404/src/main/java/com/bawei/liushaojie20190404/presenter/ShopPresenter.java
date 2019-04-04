package com.bawei.liushaojie20190404.presenter;

import android.util.Log;

import com.bawei.liushaojie20190404.app.Contant;
import com.bawei.liushaojie20190404.model.bean.ShopBean;
import com.bawei.liushaojie20190404.model.http.HttpUtils;
import com.bawei.liushaojie20190404.veiw.interfaces.IMainView;

public class ShopPresenter extends BasePresenter<IMainView<ShopBean>> {

    private final HttpUtils httpUtils;

    public ShopPresenter(){
        httpUtils = HttpUtils.getHttpUtilsInstance();
    }
    public void loadDataNet(){
        httpUtils.getData(Contant.URL_SHOP, ShopBean.class, new HttpUtils.CallBackData<ShopBean>() {

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
