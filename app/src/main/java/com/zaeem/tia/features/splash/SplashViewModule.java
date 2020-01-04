package com.zaeem.tia.features.splash;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class SplashViewModule {

    @Provides
    public SplashInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new SplashInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<SplashPresenter> providePresenterFactory(@NonNull final SplashInteractor interactor) {
        return new PresenterFactory<SplashPresenter>() {
            @NonNull
            @Override
            public SplashPresenter create() {
                return new SplashPresenterImpl(interactor);
            }
        };
    }
}
