package com.zaeem.tia.features.stats.view.impl;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.zaeem.tia.R;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.view.impl.BaseActivity;
import com.zaeem.tia.features.stats.injection.DaggerStatsViewComponent;
import com.zaeem.tia.features.stats.injection.StatsViewModule;
import com.zaeem.tia.features.stats.presenter.StatsPresenter;
import com.zaeem.tia.features.stats.view.StatsView;

import javax.inject.Inject;

import butterknife.OnClick;

public final class StatsActivity extends BaseActivity<StatsPresenter, StatsView> implements StatsView {

    @Inject
    PresenterFactory<StatsPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerStatsViewComponent.builder()
                .appComponent(parentComponent)
                .statsViewModule(new StatsViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<StatsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_stats;
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
