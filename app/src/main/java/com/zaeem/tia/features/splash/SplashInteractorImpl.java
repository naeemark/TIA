package com.zaeem.tia.features.splash;

import android.content.Context;

import com.zaeem.tia.R;
import com.zaeem.tia.app.interactor.impl.BaseInteractorImpl;
import com.zaeem.tia.utils.NetworkUtils;
import com.zaeem.tia.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private final Context mContext;

    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SplashInteractorImpl(Context context, PreferencesUtils preferencesUtils) {
        this.mContext = context;
        this.mPreferencesUtils = preferencesUtils;
    }

    @Override
    public String getNetworkErrorString() {
        return mContext.getString(R.string.error_no_network);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public boolean isSplashDone() {
        return mPreferencesUtils.getBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name());
    }

    @Override
    public void setSplashDone() {
        mPreferencesUtils.putBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name(), true);
    }
}