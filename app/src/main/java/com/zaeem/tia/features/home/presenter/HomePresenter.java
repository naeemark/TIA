package com.zaeem.tia.features.home.presenter;


import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.features.home.view.HomeView;
import com.zaeem.tia.model.User;

public interface HomePresenter extends BasePresenter<HomeView>{

    void onIssueTokenClicked();
    void onReprintTokenClicked();
    void onIssuedTokensClicked();
}