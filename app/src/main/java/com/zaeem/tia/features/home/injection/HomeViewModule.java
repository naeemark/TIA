package com.zaeem.tia.features.home.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.features.home.interactor.HomeInteractor;
import com.zaeem.tia.features.home.interactor.impl.HomeInteractorImpl;
import com.zaeem.tia.features.home.presenter.HomePresenter;
import com.zaeem.tia.features.home.presenter.impl.HomePresenterImpl;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new HomeInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return new PresenterFactory<HomePresenter>() {
            @NonNull
            @Override
            public HomePresenter create() {
                return new HomePresenterImpl(interactor);
            }
        };
    }
}
