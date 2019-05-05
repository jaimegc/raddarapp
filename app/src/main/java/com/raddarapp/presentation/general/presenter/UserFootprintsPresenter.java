package com.raddarapp.presentation.general.presenter;

import android.location.Location;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.usecase.GetUserFootprints;
import com.raddarapp.presentation.general.presenter.base.BasePresenterSwipeRefreshWithLoading;
import com.raddarapp.presentation.general.presenter.contract.UserFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserFootprintEmptyViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.UserFootprintsToUserFootprintsViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserFootprintsPresenter extends BasePresenterSwipeRefreshWithLoading<UserFootprintsPresenter.View> implements
        UserFootprintsPresenterContract{

    private static final int NUMBER_OF_USER_FOOTPRINTS_PER_PAGE = 11;
    private final UserFootprintsToUserFootprintsViewModelMapper mapperUserFootprints;
    private final GetUserFootprints getUserFootprints;
    private String userKey;

    // Always 0 because is limited the footprints size
    private Integer pageNumber = 0;
    private Location lastLocation;
    private long totalUserFootprints = 0;

    @Inject
    public UserFootprintsPresenter(UseCaseHandler useCaseHandler, UserFootprintsToUserFootprintsViewModelMapper mapperUserFootprints,
            GetUserFootprints getUserFootprints) {
        super(useCaseHandler);
        this.mapperUserFootprints = mapperUserFootprints;
        this.getUserFootprints = getUserFootprints;
    }

    @Override
    public void update() {
        super.update();
        loadData();
    }

    public void loadData() {
        getView().hideUserFootprints();

        PaginatedCollection<UserFootprint> allUserFootprintsInCache = getUserFootprints.getAllUserFootprintsInCacheOrderByDate(NUMBER_OF_USER_FOOTPRINTS_PER_PAGE);

        if (!allUserFootprintsInCache.getItems().isEmpty()) {
            getUserFootprints.addUserFootprintsInLocalCache(allUserFootprintsInCache);
        } else {
            allUserFootprintsInCache = getUserFootprints.getUserFootprintsInLocalCache();
        }

        if (allUserFootprintsInCache == null || allUserFootprintsInCache.getPage().getLimit() == 0) {
            loadUserFootprints(false);
        } else {
            clearUserFootprints();
            showUserFootprints(allUserFootprintsInCache);
        }

    }

    @Override
    public void forceRefreshing() {
        if (!isForceRefreshing) {
            showRefreshing();
            getUserFootprints.deleteCache();
            totalUserFootprints = 0;
            pageNumber = 0;
            loadUserFootprints(true);
        }
    }

    private void loadUserFootprints(boolean fromForceRefreshing) {

        if (!fromForceRefreshing) {
            showLoading();
        }

        createUseCaseCall(getUserFootprints)
                .args(userKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_USER_FOOTPRINTS_PER_PAGE, NUMBER_OF_USER_FOOTPRINTS_PER_PAGE))
                .useCaseName(GetUserFootprints.USE_CASE_GET_USER_FOOTPRINTS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getUserFootprints(PaginatedGenericTotalCollection<UserFootprint> userFootprints) {

                        totalUserFootprints = userFootprints.getPaginatedCollection().getItems().size();

                        getUserFootprints.addUserFootprintsInLocalCache(userFootprints.getPaginatedCollection());

                        if (!userFootprints.getPaginatedCollection().getItems().isEmpty()) {
                            getUserFootprints.setHasMore(userFootprints.getPaginatedCollection().hasMore());
                            showUserFootprints(userFootprints.getPaginatedCollection());
                        } else {
                            showEmptyUserFootprints();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showError() {
        hideLoading();
        hideRefreshing();
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    private void showUserFootprints(PaginatedCollection<UserFootprint> userFootprints) {
        try {
            // force refreshing is not implemented
            if (isForceRefreshing) {
                hideRefreshing();
            }

            // Always clear because is limited the footprints size
            getView().clearUserFootprints();

            List<UserFootprintViewModelContract> myFootprintViewModels = mapperUserFootprints.map(userFootprints);

            getView().showUserFootprints(myFootprintViewModels);
            getView().showHasMore(userFootprints.hasMore());

            hideLoading();
        } catch (Exception e) {}
    }

    private void clearUserFootprints() {
        getView().clearUserFootprints();
    }

    public void onLoadMore() {
        // Unused because there is not paginated
    }

    public void onUserFootprintClicked(UserFootprintViewModel userFootprint, int position) {
        String footprintKey = userFootprint.getKey();
        long comments = userFootprint.getComments();
        getView().openUserFootprintDetails(footprintKey, comments, position);
    }

    private void showEmptyUserFootprints() {
        try {
            getView().clearUserFootprints();
            hideRefreshing();
            hideLoading();
            List<UserFootprintViewModelContract> userFootprintsViewModel = new ArrayList<>();
            userFootprintsViewModel.add(new UserFootprintEmptyViewModelBuilder().build());
            getView().showEmptyUserFootprints(userFootprintsViewModel);
        } catch (Exception e) {}
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void deleteCache() {
        getUserFootprints.deleteCache();
        getUserFootprints.deleteMyFootprintsLocalCache();
    }

    public interface View extends BasePresenterSwipeRefreshWithLoading.View {
        void hideUserFootprints();

        void showUserFootprints(List<UserFootprintViewModelContract> userFootprints);

        void showHasMore(boolean hasMore);

        void clearUserFootprints();

        void openUserFootprintDetails(String footprintKey, long comments, int position);

        void showEmptyUserFootprints(List<UserFootprintViewModelContract> userFootprintsViewModel);
    }
}
