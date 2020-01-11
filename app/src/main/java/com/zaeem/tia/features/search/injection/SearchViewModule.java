package com.zaeem.tia.features.search.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.features.search.interactor.SearchInteractor;
import com.zaeem.tia.features.search.interactor.impl.SearchInteractorImpl;
import com.zaeem.tia.features.search.presenter.SearchPresenter;
import com.zaeem.tia.features.search.presenter.impl.SearchPresenterImpl;
import com.zaeem.tia.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class SearchViewModule {
    @Provides
    public SearchInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new SearchInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<SearchPresenter> providePresenterFactory(@NonNull final SearchInteractor interactor) {
        return new PresenterFactory<SearchPresenter>() {
            @NonNull
            @Override
            public SearchPresenter create() {
                return new SearchPresenterImpl(interactor);
            }
        };
    }
}
