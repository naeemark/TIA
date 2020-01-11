package com.zaeem.tia.features.home.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.zaeem.tia.R;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.view.impl.BaseActivity;
import com.zaeem.tia.features.home.injection.DaggerHomeViewComponent;
import com.zaeem.tia.features.home.injection.HomeViewModule;
import com.zaeem.tia.features.home.presenter.HomePresenter;
import com.zaeem.tia.features.home.view.HomeView;
import com.zaeem.tia.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public final class HomeActivity extends BaseActivity<HomePresenter, HomeView> implements HomeView {

    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    @BindView(R.id.input_layout_mobileNumber)
    protected TextInputLayout mobileNumberInputLayout;

    @BindView(R.id.input_mobileNumber)
    protected EditText mobileNumberEditText;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @OnFocusChange(R.id.wrapper_clickable_issueToken)
    public void onFocusObtained(boolean hasFocus) {
        if (hasFocus) {
            hideKeyboard();
        }
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager systemService = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (systemService != null) {
                systemService.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void launchNextActivity() {
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.wrapper_clickable_issueToken)
    public void onLoginClicked() {
        if (isValidContactInfo()) {
            assert mPresenter != null;

            mPresenter.onIssueTokenClicked();
        }
    }

    private boolean isValidContactInfo() {

        setInputLayoutError(mobileNumberInputLayout, null);


        if (!AppUtils.isValidName(mobileNumberEditText.getText())) {
            setInputLayoutError(mobileNumberInputLayout, "Invalid Mobile Number!");
            return false;
        }

        return true;
    }

    private void setInputLayoutError(TextInputLayout textInputLayout, CharSequence error) {

        if (error == null) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(error);
        }
    }
}
