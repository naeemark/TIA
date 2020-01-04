package com.zaeem.tia.features.splash;


import com.zaeem.tia.app.interactor.BaseInteractor;

public interface SplashInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    boolean isSplashDone();

    void setSplashDone();

}