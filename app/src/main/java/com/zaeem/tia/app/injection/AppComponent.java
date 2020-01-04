package com.zaeem.tia.app.injection;

import android.content.Context;

import com.zaeem.tia.app.TIAApp;
import com.zaeem.tia.utils.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {

    Context getAppContext();

    TIAApp getApp();

    PreferencesUtils exposePreferencesUtils();

    Retrofit exposeRetrofit();

//    DataObjectMapper exposeProductMapper();
}