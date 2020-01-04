package com.zaeem.tia.features.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.zaeem.tia.R;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.view.impl.BaseActivity;

import javax.inject.Inject;

public final class SplashActivity extends BaseActivity<SplashPresenter, SplashView> implements SplashView {

    @Inject
    PresenterFactory<SplashPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerSplashViewComponent.builder()
                .appComponent(parentComponent)
                .splashViewModule(new SplashViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @NonNull
    @Override
    protected PresenterFactory<SplashPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void launchNextActivity() {
//        Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
    }

    @Override
    public void showNetworkError() {
        super.showToast(getString(R.string.error_no_network));
    }
}
