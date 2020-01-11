package com.zaeem.tia.features.result.presenter.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.features.result.interactor.ResultInteractor;
import com.zaeem.tia.features.result.presenter.ResultPresenter;
import com.zaeem.tia.features.result.view.ResultView;

import javax.inject.Inject;

public final class ResultPresenterImpl extends BasePresenterImpl<ResultView> implements ResultPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ResultInteractor mInteractor;

    @Inject
    public ResultPresenterImpl(@NonNull ResultInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
    }

    @Override
    public void onReprintTokenClicked() {
        Log.e(this.getClass().getCanonicalName(), "onReprintTokenClicked" );
    }

    @Override
    public void onBackClicked() {
        goBack();
    }

    private void goBack() {
        assert mView != null;
        mView.goBack();
    }

}