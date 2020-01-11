package com.zaeem.tia.features.stats.injection;

import com.zaeem.tia.app.injection.ActivityScope;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.features.stats.view.impl.StatsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = StatsViewModule.class)
public interface StatsViewComponent {
    void inject(StatsActivity activity);
}