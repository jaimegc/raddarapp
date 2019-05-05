package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.usecase.GetMyUsersRanking;
import com.raddarapp.domain.usecase.GetTerritoryMain;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.presenter.contract.MyUsersRankingPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserRankingToMyUserRankingViewModelMapper;

import java.util.List;

import javax.inject.Inject;

public class MyUsersRankingAllPresenter extends BasePresenterRefreshWithLoading<MyUsersRankingAllPresenter.View> implements
        MyUsersRankingPresenterContract {

    private static int NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE = 30;
    private final MyUserRankingToMyUserRankingViewModelMapper mapperMyUsersRanking;
    private final GetMyUsersRanking getMyUsersRanking;
    private final GetTerritoryMain getTerritoryMain;
    private Integer pageNumber = 0;
    private long totalMyUsersRanking = 0;
    private String territoryKey;
    private boolean isFirstTime = true;

    @Inject
    public MyUsersRankingAllPresenter(UseCaseHandler useCaseHandler, MyUserRankingToMyUserRankingViewModelMapper mapperMyUsersRanking,
            GetMyUsersRanking getMyUsersRanking, GetTerritoryMain getTerritoryMain) {
        super(useCaseHandler);
        this.mapperMyUsersRanking = mapperMyUsersRanking;
        this.getMyUsersRanking = getMyUsersRanking;
        this.getTerritoryMain = getTerritoryMain;
    }

    @Override
    public void initialize() {
        super.initialize();
        pageNumber = 0;
        isFirstTime = true;
        TerritoryMain territoryMain = getTerritoryMain.getTerritoryInCache();

        territoryKey = (territoryMain != null) ? territoryMain.getKey() : "";
    }

    @Override
    public void update() {
        super.update();
        loadMyUsersRankingData();
    }

    public void loadMyUsersRankingData() {

        if (isFirstTime) {
            try {
                try {
                    showLoading();
                    getView().clearMyUsersRanking();
                    getMyUsersRanking.deleteCache();
                } catch (Exception e) {}
            } catch (Exception e) {}

            loadMyUsersRanking();
        } else {

            PaginatedCollection<MyUserRanking> allMyUsersRankingInCache = getMyUsersRanking.getAllMyUsersRankingInCache();

            if (allMyUsersRankingInCache.getPage().getLimit() == 0) {
                loadMyUsersRanking();
            } else {
                try {
                    getView().clearMyUsersRanking();

                    // Because we clear all cache every time
                    if (allMyUsersRankingInCache.getItems().size() < totalMyUsersRanking) {
                        allMyUsersRankingInCache.setHasMore(true);
                    }

                    showMyUsersRanking(allMyUsersRankingInCache);
                } catch (Exception e) {}
            }
        }
    }

    private void loadMyUsersRanking() {
        createUseCaseCall(getMyUsersRanking)
                .args(territoryKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE, NUMBER_OF_MY_USERS_RANKING_MAIN_PER_PAGE))
                .useCaseName(GetMyUsersRanking.USE_CASE_GET_MY_USERS_RANKING_BY_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyUsersRankingByTerritoryId(PaginatedMyUserRankingTotalCollection<MyUserRanking> myUsersRanking) {

                        totalMyUsersRanking = myUsersRanking.getTotalItems();

                        if (!myUsersRanking.getPaginatedCollection().getItems().isEmpty()) {
                            myUsersRanking.getPaginatedCollection().setHasMore(myUsersRanking.getPaginatedCollection().hasMore());

                            pageNumber++;

                            // Always we use all cache because we have 3 different views. If not,
                            // the new cache has leader and king leader views
                            PaginatedCollection<MyUserRanking> allMyFootprintsInCache = getMyUsersRanking.getAllMyUsersRankingInCache();
                            allMyFootprintsInCache.setHasMore(myUsersRanking.getPaginatedCollection().hasMore());
                            showMyUsersRanking(allMyFootprintsInCache);

                        } else {
                            showError();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showError() {
        try {
            hideLoading();
        } catch (Exception e) {}
    }

    private void showMyUsersRanking(PaginatedCollection<MyUserRanking> myUsersRanking) {
        try {
            getView().showHasMore(myUsersRanking.hasMore());

            // Always clear cache to avoid the problem with the different views
            getView().clearMyUsersRanking();

            isFirstTime = false;

            List<MyUserRankingViewModelContract> myUsersRankingViewModels = mapperMyUsersRanking.map(myUsersRanking);
            getView().showMyUsersRanking(myUsersRankingViewModels);
            hideLoading();

        } catch (Exception e) {}
    }

    public void onLoadMore() {
        loadMyUsersRanking();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public void deleteLastItemsFromCache() {
        getMyUsersRanking.deleteLastItemsFromCache();
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

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideMyUsersRanking();

        void showMyUsersRanking(List<MyUserRankingViewModelContract> myUsersRanking);

        void showHasMore(boolean hasMore);

        void clearMyUsersRanking();

        void openUserProfile(String userKey, int position);
    }
}
