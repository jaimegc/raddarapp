package com.raddarapp.presentation.general.presenter.base.contract;

public interface BasePresenterSwipeRefreshWithLoadingContract {
    void showRefreshing();

    void hideRefreshing();

    boolean isRefreshing();

    void forceRefreshing();
}
