package com.zaeem.tia.features.result.presenter;


import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.features.result.view.ResultView;

public interface ResultPresenter extends BasePresenter<ResultView>{

    void onReprintTokenClicked();
    void onBackClicked();
}