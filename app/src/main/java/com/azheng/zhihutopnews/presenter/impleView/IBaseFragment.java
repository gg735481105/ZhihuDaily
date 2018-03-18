package com.azheng.zhihutopnews.presenter.impleView;

public interface IBaseFragment {
    void showProgressDialog();

    void hidProgressDialog();

    void showError(String error);
}
