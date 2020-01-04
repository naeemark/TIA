package com.zaeem.tia.features.login.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.zaeem.tia.R;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.view.impl.BaseActivity;
import com.zaeem.tia.features.login.injection.DaggerLoginViewComponent;
import com.zaeem.tia.features.login.injection.LoginViewModule;
import com.zaeem.tia.features.login.presenter.LoginPresenter;
import com.zaeem.tia.features.login.view.LoginView;
import com.zaeem.tia.model.User;
import com.zaeem.tia.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    @BindView(R.id.input_layout_username)
    protected TextInputLayout nameInputLayout;
    @BindView(R.id.input_layout_password)
    protected TextInputLayout passwordInputLayout;

    @BindView(R.id.input_username)
    protected EditText usernameEditText;

    @BindView(R.id.input_password)
    protected EditText passwordEditText;

    @BindView(R.id.radioGroup_gender)
    protected RadioGroup radioGroupGender;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @OnFocusChange(R.id.wrapper_clickable_login)
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

    @OnClick(R.id.wrapper_clickable_login)
    public void onLoginClicked(){
        if(isValidContactInfo()) {
            assert mPresenter != null;
            User user = getUser();
            mPresenter.onLoginClicked(user);
        }
    }

    private boolean isValidContactInfo() {

        setInputLayoutError(nameInputLayout, null);
        setInputLayoutError(passwordInputLayout, null);

        if(!AppUtils.isValidName(usernameEditText.getText())){
            setInputLayoutError(nameInputLayout, "Invalid Name!");
            return false;
        }

        return true;
    }

    private void setInputLayoutError(TextInputLayout textInputLayout, CharSequence error) {

        if (error == null){
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }else{
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(error);
        }
    }

    private User getUser() {

        return new User(
                usernameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                (String) findViewById(radioGroupGender.getCheckedRadioButtonId()).getTag()
        );
    }
}
