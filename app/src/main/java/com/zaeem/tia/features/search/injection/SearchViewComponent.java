package com.zaeem.tia.features.search.injection;

import com.zaeem.tia.app.injection.ActivityScope;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.features.search.view.impl.SearchActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchViewModule.class)
public interface SearchViewComponent {
    void inject(SearchActivity activity);
}