package com.zaeem.tia.app.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zaeem.tia.app.TIAApp;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final TIAApp mApp;

    public AppModule(@NonNull TIAApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public TIAApp provideApp() {
        return mApp;
    }
}
