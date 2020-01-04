package com.zaeem.tia.features.login.view;

import androidx.annotation.UiThread;

@UiThread
public interface LoginView {

    void hideKeyboard();

    void launchNextActivity();
}