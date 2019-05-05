package com.raddarapp.presentation.general.presenter.base;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;
import com.raddarapp.presentation.general.presenter.base.contract.BasePresenterSwipeRefreshWithLoadingContract;

public abstract class BasePresenterSwipeRefreshWithLoading<T extends BasePresenterSwipeRefreshWithLoading.View>
        extends RosiePresenterWithLoading<T> implements BasePresenterSwipeRefreshWithLoadingContract {

    protected boolean isForceRefreshing = false;

    public BasePresenterSwipeRefreshWithLoading(UseCaseHandler useCaseHandler) {
        super(useCaseHandler);
    }

    @Override
    public void showRefreshing() {
        isForceRefreshing = true;
        getView().showRefreshing();
    }

    @Override
    public void hideRefreshing() {
        isForceRefreshing = false;
        getView().hideRefreshing();
    }

    @Override
    public boolean isRefreshing() {
        return isForceRefreshing;
    }

    public interface View extends RosiePresenterWithLoading.View {

        void showRefreshing();
        void hideRefreshing();
    }
}
