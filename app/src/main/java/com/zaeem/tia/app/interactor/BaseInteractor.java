package com.zaeem.tia.app.interactor;

public interface BaseInteractor {

    void cancelOnGoingDataRequest();

    String getNetworkErrorString();

    boolean isNetworkConnected();
}
