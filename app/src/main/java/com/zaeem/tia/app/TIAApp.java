package com.zaeem.tia.app;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaeem.tia.BuildConfig;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.injection.AppModule;
import com.zaeem.tia.app.injection.DaggerAppComponent;
import com.zaeem.tia.app.injection.NetworkApiModule;

import timber.log.Timber;


public final class TIAApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Shows logs only in Debug Build type
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkApiModule(new NetworkApiModule(BuildConfig.BASE_URL))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}