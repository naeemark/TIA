package com.zaeem.tia.features.search.view;

import androidx.annotation.UiThread;

@UiThread
public interface SearchView {

    void hideKeyboard();
    void goBack();
    void launchResultActivity();
}