package com.zaeem.tia.features.search.presenter;


import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.features.search.view.SearchView;

public interface SearchPresenter extends BasePresenter<SearchView>{

    void onSearchClicked();
    void onBackClicked();
}