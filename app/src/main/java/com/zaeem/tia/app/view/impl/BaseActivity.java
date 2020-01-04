package com.zaeem.tia.app.view.impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.zaeem.tia.R;
import com.zaeem.tia.app.TIAApp;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.presenter.loader.PresenterLoader;
import com.zaeem.tia.app.view.BaseView;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends AppCompatActivity implements BaseView, LoaderManager.LoaderCallbacks<P> {
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);
    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean mFirstStart;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        injectDependencies();

        getSupportLoaderManager().initLoader(0, null, this).startLoading();

        setContentView(getContentView());

        ButterKnife.bind(this);

        setActionBar();

        onViewReady(savedInstanceState, getIntent());
    }

    private void injectDependencies() {
        AppComponent appComponent = ((TIAApp) getApplication()).getAppComponent();
        setupComponent(appComponent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPresenter == null) {
            mNeedToCallStart.set(true);
        } else {
            doStart();
        }
    }

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart() {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }
        super.onStop();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        mPresenter = presenter;

        if (mNeedToCallStart.compareAndSet(true, false)) {
            doStart();
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    protected abstract void setupComponent(@NonNull AppComponent appComponent);

    protected abstract int getContentView();

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        this.showLoading(getString(R.string.loading));
    }

    @Override
    public void showLoading(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showErrorWithMessage(String errorText) {
        showToast(errorText);
    }

    @Override
    public void showErrorLoading() {
        showToast(getString(R.string.error_loading_data));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError() {
        showToast(getString(R.string.error_no_network));
    }

    protected void replaceFragment(@IdRes int containerId, @NonNull Fragment targetFragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, targetFragment, targetFragment.getTag());
        ft.commit();
    }

    protected void showAbout() {
        // Todo: show about dialog
    }

}