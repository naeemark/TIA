package com.zaeem.tia.features.stats.presenter;


import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.features.stats.view.StatsView;

public interface StatsPresenter extends BasePresenter<StatsView>{

    void onBackClicked();
    void onIssueTokenClicked();
    void onReprintTokenClicked();
    void onIssuedTokensClicked();
}