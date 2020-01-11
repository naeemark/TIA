package com.zaeem.tia.features.home.view;

import androidx.annotation.UiThread;

@UiThread
public interface HomeView {

    void hideKeyboard();

    void launchNextActivity();

    void launchStatsActivity();

    void launchSearchActivity();
}