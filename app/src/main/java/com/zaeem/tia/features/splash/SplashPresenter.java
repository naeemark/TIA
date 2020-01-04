package com.zaeem.tia.features.splash;


import com.zaeem.tia.app.presenter.BasePresenter;

public interface SplashPresenter extends BasePresenter<SplashView>{

    void doSplash();

    void checkNetwork();

    void launchNextActivity();
}