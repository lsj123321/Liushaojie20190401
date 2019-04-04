package com.bawei.liushaojie20190404.veiw.interfaces;

public interface IMainView<D> extends IBaseView {
    public void onSuccess(D d);
    public void onErr(String errMsg);
}
