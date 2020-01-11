package com.zaeem.tia.features.search.view.impl;

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
import com.zaeem.tia.features.search.injection.DaggerSearchViewComponent;
import com.zaeem.tia.features.search.injection.SearchViewModule;
import com.zaeem.tia.features.search.presenter.SearchPresenter;
import com.zaeem.tia.features.search.view.SearchView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public final class SearchActivity extends BaseActivity<SearchPresenter, SearchView> implements SearchView {

    @Inject
    PresenterFactory<SearchPresenter> mPresenterFactory;

    @BindView(R.id.input_layout_tokenOrMobileNumber)
    protected TextInputLayout mobileNumberInputLayout;

    @BindView(R.id.input_tokenOrMobileNumber)
    protected EditText mobileNumberEditText;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerSearchViewComponent.builder()
                .appComponent(parentComponent)
                .searchViewModule(new SearchViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<SearchPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
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

    @OnFocusChange(R.id.wrapper_clickable_search)
    public void onFocusObtained(boolean hasFocus) {
        if (hasFocus) {
            hideKeyboard();
        }
    }

    @OnClick(R.id.wrapper_clickable_back)
    public void onBackClicked() {
        assert mPresenter != null;
        mPresenter.onBackClicked();
    }


    @Override
    public void goBack() {
        finish();
    }
}
