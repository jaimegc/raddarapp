package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.usecase.GetMyUsersRanking;
import com.raddarapp.presentation.general.presenter.base.BasePresenterSwipeRefreshWithLoading;
import com.raddarapp.presentation.general.presenter.contract.MyUsersRankingPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserRankingToMyUserRankingViewModelMapper;

import java.util.List;

import javax.inject.Inject;

public class MyUsersRankingPresenter extends BasePresenterSwipeRefreshWithLoading<MyUsersRankingPresenter.View> implements
        MyUsersRankingPresenterContract {

    private static final int NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE = 20;
    private final MyUserRankingToMyUserRankingViewModelMapper mapperMyUsersRanking;
    private final GetMyUsersRanking getMyUsersRanking;
    private Integer pageNumber = 0;
    private long totalItems = 0;
    private long totalMyUsersRanking = 0;
    private String territoryKey;

    @Inject
    public MyUsersRankingPresenter(UseCaseHandler useCaseHandler, MyUserRankingToMyUserRankingViewModelMapper mapperMyUsersRanking,
            GetMyUsersRanking getMyUsersRanking) {
        super(useCaseHandler);
        this.mapperMyUsersRanking = mapperMyUsersRanking;
        this.getMyUsersRanking = getMyUsersRanking;
    }

    public void setTerritoryKey(String territoryKey) {
        this.territoryKey = territoryKey;
    }

    public String getTerritoryKey() {
        return territoryKey;
    }

    @Override
    public void update() {
        super.update();
        // Only load data from cache. If cache is empty we don't show data
        loadMyUsersRankingDataFromCache();
    }

    @Override
    public void forceRefreshing() {
        if (!isForceRefreshing) {
            showRefreshing();
            getMyUsersRanking.deleteCache();
            totalMyUsersRanking = 0;
            pageNumber = 0;
            loadMyUsersRanking();
        }
    }

    public void onReloadMyUsersRankingClicked() {
        if (!isForceRefreshing) {
            getMyUsersRanking.deleteCache();
            totalMyUsersRanking = 0;
            pageNumber = 0;
            loadMyUsersRanking();
        }
    }

    public void onEmptyInfluencersClicked() {
        getMyUsersRanking.deleteCache();
        totalMyUsersRanking = 0;
        pageNumber = 0;
        loadMyUsersRanking();
    }

    public void loadMyUsersRankingDataFromCache() {

        getView().hideMyUsersRanking();

        PaginatedCollection<MyUserRanking> allMyUsersRankingInCache = getMyUsersRanking.getMaxMyUsersRankingInCache(NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE);

        if (!allMyUsersRankingInCache.getItems().isEmpty()) {
            getMyUsersRanking.addMyUsersRankingInLocalCache(allMyUsersRankingInCache);
        } else {
            allMyUsersRankingInCache = getMyUsersRanking.getMyUsersRankingInCache();
        }

        if (allMyUsersRankingInCache != null && allMyUsersRankingInCache.getPage().getLimit() > 0) {
            clearMyUsersRanking();
            showMyUsersRanking(allMyUsersRankingInCache);
        }
    }

    public void loadMyUsersRankingData() {

        getView().hideMyUsersRanking();

        PaginatedCollection<MyUserRanking> allMyUsersRankingInCache = getMyUsersRanking.getMaxMyUsersRankingInCache(NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE);

        if (!allMyUsersRankingInCache.getItems().isEmpty()) {
            getMyUsersRanking.addMyUsersRankingInLocalCache(allMyUsersRankingInCache);
        } else {
            allMyUsersRankingInCache = getMyUsersRanking.getMyUsersRankingInCache();
        }

        if (allMyUsersRankingInCache == null || allMyUsersRankingInCache.getPage().getLimit() == 0) {
            loadMyUsersRanking();
        } else {
            clearMyUsersRanking();
            showMyUsersRanking(allMyUsersRankingInCache);
        }
    }

    private void loadMyUsersRanking() {

        getView().showLoadingInfluencers();

        createUseCaseCall(getMyUsersRanking)
                .args(territoryKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE, NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE))
                .useCaseName(GetMyUsersRanking.USE_CASE_GET_MY_USERS_RANKING_BY_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyUsersRankingByTerritoryId(PaginatedMyUserRankingTotalCollection<MyUserRanking> myUsersRanking) {

                        if (!myUsersRanking.getPaginatedCollection().getItems().isEmpty()) {
                            myUsersRanking.getPaginatedCollection().setHasMore(myUsersRanking.getPaginatedCollection().hasMore());
                            pageNumber++;
                            totalItems = myUsersRanking.getTotalItems();
                            showMyActualPosition(myUsersRanking.getPosition(), myUsersRanking.getTotalItems());
                            showMyUsersRanking(myUsersRanking.getPaginatedCollection());
                        } else {
                            showEmptyMyUsersRanking();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    public void loadMyUsersRankingFromMap(String territoryKey) {
        this.territoryKey = territoryKey;
        this.pageNumber = 0;
        getMyUsersRanking.deleteCache();
        totalMyUsersRanking = 0;

        createUseCaseCall(getMyUsersRanking)
                .args(territoryKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE, NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE))
                .useCaseName(GetMyUsersRanking.USE_CASE_GET_MY_USERS_RANKING_BY_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyUsersRankingByTerritoryId(PaginatedMyUserRankingTotalCollection<MyUserRanking> myUsersRanking) {

                        if (!myUsersRanking.getPaginatedCollection().getItems().isEmpty()) {
                            myUsersRanking.getPaginatedCollection().setHasMore(myUsersRanking.getPaginatedCollection().hasMore());
                            pageNumber++;
                            totalItems = myUsersRanking.getTotalItems();
                            isForceRefreshing = true; // To force clearing collection
                            showMyActualPosition(myUsersRanking.getPosition(), myUsersRanking.getTotalItems());
                            showMyUsersRanking(myUsersRanking.getPaginatedCollection());
                        } else {
                            showEmptyMyUsersRanking();
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
        getView().hideLoadingInfluencers();
    }

    private void clearMyUsersRanking() {
        try {
            getView().clearMyUsersRanking();
        } catch (Exception e) {}
    }

    private void showMyUsersRanking(PaginatedCollection<MyUserRanking> myUsersRanking) {
        try {
            if (isForceRefreshing) {
                hideRefreshing();
            }

            // Always we use all cache because we have 3 different views. If not,
            // the new cache has leader and king leader views
            getView().clearMyUsersRanking();

            List<MyUserRankingViewModelContract> myUsersRankingViewModels = mapperMyUsersRanking.map(myUsersRanking);
            getView().showMyUsersRanking(myUsersRankingViewModels);
            getView().showHasMore(totalItems > NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE);

            getView().hideLoadingInfluencers();
        } catch (Exception e) {}
    }

    private void showMyActualPosition(long myPosition, long total) {
        try {
            if (myPosition != 0) {
                getView().showMyActualPosition(myPosition, total);
            } else {
                getView().showMyActualPositionUnknown(total);
            }

        } catch (Exception e) {}
    }

    public void onLoadMore() {
        //loadMyFootprints(lastLocation);
    }

    private void showEmptyMyUsersRanking() {
        try {
            if (isForceRefreshing) {
                hideRefreshing();
            }

            getView().showMyActualPositionUnknown(0);
            getView().showEmptyMyUsersRanking();
        } catch (Exception e) {}
    }

    public void deleteCache() {
        getMyUsersRanking.deleteCache();
    }

    public void deleteUsersRankingLocalCache() {
        getMyUsersRanking.deleteUsersRankingLocalCache();
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void onMyUserRankingClicked(MyUserRankingViewModel myUserRanking, int position) {
        String userKey = myUserRanking.getKey();
        getView().openUserProfile(userKey, position);
    }

    @Override
    public void onMyLeaderUserRankingClicked(MyLeaderRankingViewModel myUserRanking, int position) {
        String userKey = myUserRanking.getKey();
        getView().openUserProfile(userKey, position);
    }

    @Override
    public void onMyLeaderKingUserRankingClicked(MyLeaderKingRankingViewModel myUserRanking, int position) {
        String userKey = myUserRanking.getKey();
        getView().openUserProfile(userKey, position);
    }

    public interface View extends BasePresenterSwipeRefreshWithLoading.View {
        void hideMyUsersRanking();

        void showMyUsersRanking(List<MyUserRankingViewModelContract> myUsersRanking);

        void showHasMore(boolean hasMore);

        void clearMyUsersRanking();

        void showEmptyMyUsersRanking();

        void showMyActualPosition(long myPosition, long total);

        void showMyActualPositionUnknown(long total);

        void hideLoadingInfluencers();

        void showLoadingInfluencers();

        void openUserProfile(String userKey, int position);
    }
}
