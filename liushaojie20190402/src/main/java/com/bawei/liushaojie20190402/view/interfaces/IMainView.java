package com.bawei.liushaojie20190402.view.interfaces;

public interface IMainView<T> extends IBaseView{
    public void onSuccess(T t);
    public void onErr(String errMessage);
}
