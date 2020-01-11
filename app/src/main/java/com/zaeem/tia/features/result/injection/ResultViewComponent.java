package com.zaeem.tia.features.result.injection;

import com.zaeem.tia.app.injection.ActivityScope;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.features.result.view.impl.ResultActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ResultViewModule.class)
public interface ResultViewComponent {
    void inject(ResultActivity activity);
}