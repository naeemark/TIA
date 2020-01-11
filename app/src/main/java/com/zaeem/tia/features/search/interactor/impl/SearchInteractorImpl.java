package com.zaeem.tia.features.search.interactor.impl;

import android.content.Context;

import com.zaeem.tia.R;
import com.zaeem.tia.app.interactor.impl.BaseInteractorImpl;
import com.zaeem.tia.features.search.interactor.SearchInteractor;
import com.zaeem.tia.utils.NetworkUtils;
import com.zaeem.tia.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SearchInteractorImpl extends BaseInteractorImpl implements SearchInteractor {

    private final Context mContext;
    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SearchInteractorImpl(Context context, PreferencesUtils preferencesUtils) {

        mContext = context;
        mPreferencesUtils = preferencesUtils;
    }

    @Override
    public String getNetworkErrorString() {
        return mContext.getString(R.string.error_no_network);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

}