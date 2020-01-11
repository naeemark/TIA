package com.zaeem.tia.features.stats.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.features.stats.interactor.StatsInteractor;
import com.zaeem.tia.features.stats.interactor.impl.StatsInteractorImpl;
import com.zaeem.tia.features.stats.presenter.StatsPresenter;
import com.zaeem.tia.features.stats.presenter.impl.StatsPresenterImpl;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class StatsViewModule {
    @Provides
    public StatsInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new StatsInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<StatsPresenter> providePresenterFactory(@NonNull final StatsInteractor interactor) {
        return new PresenterFactory<StatsPresenter>() {
            @NonNull
            @Override
            public StatsPresenter create() {
                return new StatsPresenterImpl(interactor);
            }
        };
    }
}
