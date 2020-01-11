package com.zaeem.tia.features.home.presenter.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.features.home.interactor.HomeInteractor;
import com.zaeem.tia.features.home.presenter.HomePresenter;
import com.zaeem.tia.features.home.view.HomeView;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomeInteractor mInteractor;

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
    }



    @Override
    public void onIssueTokenClicked() {
        Log.e(this.getClass().getCanonicalName(), "onIssueTokenClicked" );
    }

    @Override
    public void onSearchTokenClicked() {
        Log.e(this.getClass().getCanonicalName(), "onSearchTokenClicked" );
        assert mView != null;
        mView.launchSearchActivity();
    }

    @Override
    public void onIssuedTokensClicked() {
        Log.e(this.getClass().getCanonicalName(), "onIssuedTokensClicked" );
        assert mView != null;
        mView.launchStatsActivity();
    }

    @Override
    public void onLogoutClicked() {
        Log.e(this.getClass().getCanonicalName(), "onIssuedTokensClicked" );
        assert mView != null;
        mView.logout();
    }

    private void launchNextActivity() {
        assert mView != null;
        mView.launchNextActivity();
    }
}