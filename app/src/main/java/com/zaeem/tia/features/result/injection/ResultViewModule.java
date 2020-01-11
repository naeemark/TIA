package com.zaeem.tia.features.result.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.features.result.interactor.ResultInteractor;
import com.zaeem.tia.features.result.interactor.impl.ResultInteractorImpl;
import com.zaeem.tia.features.result.presenter.ResultPresenter;
import com.zaeem.tia.features.result.presenter.impl.ResultPresenterImpl;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class ResultViewModule {
    @Provides
    public ResultInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new ResultInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<ResultPresenter> providePresenterFactory(@NonNull final ResultInteractor interactor) {
        return new PresenterFactory<ResultPresenter>() {
            @NonNull
            @Override
            public ResultPresenter create() {
                return new ResultPresenterImpl(interactor);
            }
        };
    }
}
