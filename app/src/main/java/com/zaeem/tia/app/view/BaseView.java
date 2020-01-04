package com.zaeem.tia.app.view;


public interface BaseView {

    void showLoading();

    void showLoading(String message);

    void hideLoading();

    void showErrorWithMessage(String errorText);

    void showErrorLoading();

    void showToast(String message);

    void showNetworkError();
}
