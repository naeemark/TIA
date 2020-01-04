package com.zaeem.tia.features.splash;


import androidx.annotation.UiThread;

import com.zaeem.tia.app.view.BaseView;

@UiThread
public interface SplashView extends BaseView {

    void finishActivity();

    void launchNextActivity();

    void showNetworkError();

}