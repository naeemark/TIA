package com.zaeem.tia.features.login.injection;

import com.zaeem.tia.app.injection.ActivityScope;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.features.login.view.impl.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
    void inject(LoginActivity activity);
}