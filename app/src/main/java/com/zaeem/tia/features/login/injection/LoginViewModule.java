package com.zaeem.tia.features.login.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.features.login.interactor.LoginInteractor;
import com.zaeem.tia.features.login.interactor.impl.LoginInteractorImpl;
import com.zaeem.tia.features.login.presenter.LoginPresenter;
import com.zaeem.tia.features.login.presenter.impl.LoginPresenterImpl;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class LoginViewModule {
    @Provides
    public LoginInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new LoginInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<LoginPresenter> providePresenterFactory(@NonNull final LoginInteractor interactor) {
        return new PresenterFactory<LoginPresenter>() {
            @NonNull
            @Override
            public LoginPresenter create() {
                return new LoginPresenterImpl(interactor);
            }
        };
    }
}
