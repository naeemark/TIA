package com.zaeem.tia.features.stats.interactor.impl;

import android.content.Context;

import com.zaeem.tia.R;
import com.zaeem.tia.app.interactor.impl.BaseInteractorImpl;
import com.zaeem.tia.features.stats.interactor.StatsInteractor;
import com.zaeem.tia.utils.NetworkUtils;
import com.zaeem.tia.utils.PreferencesUtils;

import javax.inject.Inject;

public final class StatsInteractorImpl extends BaseInteractorImpl implements StatsInteractor {

    private final Context mContext;
    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public StatsInteractorImpl(Context context, PreferencesUtils preferencesUtils) {

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