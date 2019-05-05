package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.usecase.GetFootprintsRanking;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.FootprintsRankingToFootprintsRankingViewModelMapper;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class FootprintsRankingPresenter extends BasePresenterRefreshWithLoading<FootprintsRankingPresenter.View> {

    private static int NUMBER_OF_FOOTPRINTS_RANKING_PER_PAGE = 25;
    private static final String WORLD_ZONE_KEY = "0";
    private final FootprintsRankingToFootprintsRankingViewModelMapper mapperFootprintsRanking;
    private final GetFootprintsRanking getFootprintsRanking;
    private Integer pageNumber = 0;
    private boolean isFirstTime = true;
    private List<String> keyPositions = new LinkedList<>();

    @Inject
    public FootprintsRankingPresenter(UseCaseHandler useCaseHandler, FootprintsRankingToFootprintsRankingViewModelMapper mapperFootprintsRanking,
            GetFootprintsRanking getFootprintsRanking) {
        super(useCaseHandler);
        this.mapperFootprintsRanking = mapperFootprintsRanking;
        this.getFootprintsRanking = getFootprintsRanking;
    }

    @Override
    public void initialize() {
        super.initialize();
        pageNumber = 0;
        isFirstTime = true;
        keyPositions = new LinkedList<>();
    }

    @Override
    public void update() {
        super.update();
        loadData();
    }

    private void loadData() {

        getView().hideFootprintsRanking();

        if (isFirstTime) {
            try {
                try {
                    showLoading();
                    getView().clearFootprintsRanking();
                    getFootprintsRanking.deleteCache();
                } catch (Exception e) {}
            } catch (Exception e) {}

            loadFootprintsRanking();
        } else {

            PaginatedCollection<FootprintRanking> allMyFootprintsInCache = getFootprintsRanking.getAllFootprintsRankingInCache();

            if (allMyFootprintsInCache.getPage().getLimit() == 0) {
                loadFootprintsRanking();
            } else {
                try {
                    try {
                        getView().clearFootprintsRanking();
                    } catch (Exception e) {}
                    showFootprintsRanking(allMyFootprintsInCache, allMyFootprintsInCache.hasMore());
                } catch (Exception e) {}
            }
        }
    }

    private void loadFootprintsRanking() {

        if (pageNumber > 0) {
            try {
                getView().showLoadingBottom();
            } catch (Exception e) {}
        }

        createUseCaseCall(getFootprintsRanking)
                .args(WORLD_ZONE_KEY, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_FOOTPRINTS_RANKING_PER_PAGE, NUMBER_OF_FOOTPRINTS_RANKING_PER_PAGE))
                .useCaseName(GetFootprintsRanking.USE_CASE_GET_FOOTPRINTS_RANKING)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintsRankingByZoneKey(PaginatedGenericTotalCollection<FootprintRanking> footprintsRanking) {

                        if (!footprintsRanking.getPaginatedCollection().getItems().isEmpty()) {
                            boolean hasMore = footprintsRanking.getPaginatedCollection().hasMore();
                            getFootprintsRanking.setHasMore(hasMore);
                            pageNumber++;
                            showFootprintsRanking(footprintsRanking.getPaginatedCollection(), hasMore);
                        } else {
                            showEmptyFootprintsRanking();
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
        getView().hideLoadingBottom();
    }

    public void onUserClicked(FootprintRankingViewModel footprintRanking, int position) {
        String footprintRankingKey = footprintRanking.getKey();
        String userKey = footprintRanking.getUserKey();
        getView().openUserProfile(footprintRankingKey, userKey, position);
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    private void showFootprintsRanking(PaginatedCollection<FootprintRanking> footprintsRanking, boolean hasMore) {
        try {
            List<FootprintRankingViewModelContract> myFootprintCollectionViewModels = mapperFootprintsRanking.map(footprintsRanking);
            keyPositions.addAll(mapperFootprintsRanking.getKeyPositions());
            getView().showFootprintsRanking(myFootprintCollectionViewModels);

            getView().showHasMore(hasMore);

            isFirstTime = false;

            hideLoading();
            getView().hideLoadingBottom();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        loadFootprintsRanking();
    }

    private void showEmptyFootprintsRanking() {
        try {
            getView().clearFootprintsRanking();
            hideLoading();
            getView().hideLoadingBottom();
            getView().showEmptyFootprintsRanking();
        } catch (Exception e) {}
    }

    public void onFootprintRankingClicked(FootprintRankingViewModel myFootprintCollection, int position) {
        String footprintMainKey = myFootprintCollection.getKey();
        long comments = myFootprintCollection.getComments();
        getView().openFootprintRankingDetails(footprintMainKey, comments, position);
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideFootprintsRanking();

        void showFootprintsRanking(List<FootprintRankingViewModelContract> footprintsRanking);

        void showHasMore(boolean hasMore);

        void clearFootprintsRanking();

        void openFootprintRankingDetails(String myFootprintCollectionKey, long comments, int position);

        void showEmptyFootprintsRanking();

        void openUserProfile(String footprintRankingKey, String userKey, int position);

        void showLoadingBottom();

        void hideLoadingBottom();
    }
}
