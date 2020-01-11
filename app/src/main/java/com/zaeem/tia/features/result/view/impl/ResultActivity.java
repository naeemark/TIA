package com.zaeem.tia.features.result.view.impl;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.zaeem.tia.R;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.view.impl.BaseActivity;
import com.zaeem.tia.features.result.injection.DaggerResultViewComponent;
import com.zaeem.tia.features.result.injection.ResultViewModule;
import com.zaeem.tia.features.result.presenter.ResultPresenter;
import com.zaeem.tia.features.result.view.ResultView;

import javax.inject.Inject;

import butterknife.OnClick;

public final class ResultActivity extends BaseActivity<ResultPresenter, ResultView> implements ResultView {

    @Inject
    PresenterFactory<ResultPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerResultViewComponent.builder()
                .appComponent(parentComponent)
                .resultViewModule(new ResultViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ResultPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_result;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
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
