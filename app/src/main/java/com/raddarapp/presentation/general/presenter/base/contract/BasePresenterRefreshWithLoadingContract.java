package com.raddarapp.presentation.general.presenter.base.contract;

public interface BasePresenterRefreshWithLoadingContract {
    boolean isRefreshing();

    void forceRefreshing();
}
