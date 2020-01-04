package com.zaeem.tia.features.login.presenter;


import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.features.login.view.LoginView;
import com.zaeem.tia.model.User;

public interface LoginPresenter extends BasePresenter<LoginView>{

    void onLoginClicked(User user);
}