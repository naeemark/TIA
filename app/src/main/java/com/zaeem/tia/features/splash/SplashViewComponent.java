package com.zaeem.tia.features.splash;


import com.zaeem.tia.app.injection.ActivityScope;
import com.zaeem.tia.app.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashActivity activity);
}