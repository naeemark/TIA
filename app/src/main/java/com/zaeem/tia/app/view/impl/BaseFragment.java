package com.zaeem.tia.app.view.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.zaeem.tia.app.TIAApp;
import com.zaeem.tia.app.injection.AppComponent;
import com.zaeem.tia.app.presenter.BasePresenter;
import com.zaeem.tia.app.presenter.loader.PresenterFactory;
import com.zaeem.tia.app.presenter.loader.PresenterLoader;
import com.zaeem.tia.app.view.BaseFragmentView;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter<V>, V> extends Fragment implements LoaderManager.LoaderCallbacks<P>, BaseFragmentView {
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
     * Is this the first start of the fragment (after onCreate)
     */
    private boolean mFirstStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        injectDependencies();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(0, null, this).startLoading();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onViewReady(view, savedInstanceState);
    }

    @CallSuper
    protected void onViewReady(View view, Bundle savedInstanceState) {

    }

    protected abstract int getLayoutId();

    @Override
    public void onStart() {
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
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getActivity(), getPresenterFactory());
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

    @Override
    public void showLoading() {
        ((BaseActivity)getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((BaseActivity)getActivity()).hideLoading();
    }

    public void showToast(String message){
        ((BaseActivity)getActivity()).showToast(message);
    }

    private void injectDependencies() {
        setupComponent(((TIAApp) getActivity().getApplication()).getAppComponent());
    }
}
