package com.zaeem.tia.features.login.presenter.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.impl.BasePresenterImpl;
import com.zaeem.tia.features.login.interactor.LoginInteractor;
import com.zaeem.tia.features.login.presenter.LoginPresenter;
import com.zaeem.tia.features.login.view.LoginView;
import com.zaeem.tia.model.User;

import javax.inject.Inject;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final LoginInteractor mInteractor;

    @Inject
    public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
    }

    @Override
    public void onLoginClicked(User user) {

        Log.e(user.getClass().getCanonicalName(), user.toString() );
    }

    private void launchNextActivity() {
        assert mView != null;
        mView.launchNextActivity();
    }
}