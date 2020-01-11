package com.zaeem.tia.features.search.presenter.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.features.search.interactor.SearchInteractor;
import com.zaeem.tia.features.search.presenter.SearchPresenter;
import com.zaeem.tia.features.search.view.SearchView;

import javax.inject.Inject;

public final class SearchPresenterImpl extends BasePresenterImpl<SearchView> implements SearchPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final SearchInteractor mInteractor;

    @Inject
    public SearchPresenterImpl(@NonNull SearchInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
    }

    @Override
    public void onSearchClicked() {
        Log.e(this.getClass().getCanonicalName(), "onSearchClicked" );
        assert mView != null;
        mView.launchResultActivity();
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