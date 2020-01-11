package com.zaeem.tia.features.stats.presenter.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.features.stats.interactor.StatsInteractor;
import com.zaeem.tia.features.stats.presenter.StatsPresenter;
import com.zaeem.tia.features.stats.view.StatsView;

import javax.inject.Inject;

public final class StatsPresenterImpl extends BasePresenterImpl<StatsView> implements StatsPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final StatsInteractor mInteractor;

    @Inject
    public StatsPresenterImpl(@NonNull StatsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
    }


    @Override
    public void onBackClicked() {
        goBack();
    }

    @Override
    public void onIssueTokenClicked() {
        Log.e(this.getClass().getCanonicalName(), "onIssueTokenClicked" );
    }

    @Override
    public void onReprintTokenClicked() {
        Log.e(this.getClass().getCanonicalName(), "onReprintTokenClicked" );
    }

    @Override
    public void onIssuedTokensClicked() {
        Log.e(this.getClass().getCanonicalName(), "onIssuedTokensClicked" );
    }

    private void goBack() {
        assert mView != null;
        mView.goBack();
    }
}