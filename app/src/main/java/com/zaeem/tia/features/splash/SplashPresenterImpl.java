package com.zaeem.tia.features.splash;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.constants.AppConstants;

import javax.inject.Inject;

public final class SplashPresenterImpl extends BasePresenterImpl<SplashView> implements SplashPresenter, Runnable {

    @NonNull
    private final SplashInteractor mInteractor;
    private Handler mHandler = new Handler();

    @Inject
    public SplashPresenterImpl(@NonNull SplashInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            doSplash();
//            if (!mInteractor.isSplashDone()) {
//                doSplash();
//            } else {
//                launchNextActivity();
//            }
        }
    }


    @Override
    public void onStop() {
        mHandler.removeCallbacks(this);
        super.onStop();
    }

    @Override
    public void doSplash() {
        mHandler.postDelayed(this, AppConstants.SPLASH_TIME_MILLI_SECONDS);
        mInteractor.setSplashDone();
    }

    @Override
    public void checkNetwork() {
        if (!mInteractor.isNetworkConnected()) {
            assert mView != null;
            mView.showNetworkError();
        }
    }

    @Override
    public void launchNextActivity() {
        assert mView != null;
        mView.launchNextActivity();
        mView.finishActivity();
    }

    @Override
    public void run() {
        launchNextActivity();
    }

}