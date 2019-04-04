package com.bawei.liushaojie.view.interfaces;

public interface IMainView<T> extends IBaseView {
    public void onSuccess(T t);
    public void onErr(String errMsg);
}
