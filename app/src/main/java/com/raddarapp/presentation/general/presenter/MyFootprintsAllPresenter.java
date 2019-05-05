package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.usecase.DeleteMyFootprint;
import com.raddarapp.domain.usecase.GetMyFootprints;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.presenter.contract.MyFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintsToMyFootprintsViewModelMapper;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsAllPresenter extends BasePresenterRefreshWithLoading<MyFootprintsAllPresenter.View> implements
        MyFootprintsPresenterContract {

    private static int NUMBER_OF_MY_FOOTPRINTS_PER_PAGE = 20;
    private final MyFootprintsToMyFootprintsViewModelMapper mapperMyFootprints;
    private final GetMyFootprints getMyFootprints;
    private final DeleteMyFootprint deleteMyFootprint;
    private final FootprintsMainPresenter footprintsMainPresenter;
    private Integer pageNumber = 0;
    private long totalMyFootprints = 0;
    private MyUserProfile userProfile = null;
    private boolean isFirstTime = true;
    private List<String> keyPositions = new LinkedList<>();

    @Inject
    public MyFootprintsAllPresenter(UseCaseHandler useCaseHandler, MyFootprintsToMyFootprintsViewModelMapper mapperMyFootprints,
            GetMyFootprints getMyFootprints, DeleteMyFootprint deleteMyFootprint, FootprintsMainPresenter footprintsMainPresenter) {
        super(useCaseHandler);
        this.mapperMyFootprints = mapperMyFootprints;
        this.getMyFootprints = getMyFootprints;
        this.deleteMyFootprint = deleteMyFootprint;
        this.footprintsMainPresenter = footprintsMainPresenter;
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

        getView().hideMyFootprints();

        if (isFirstTime) {
            try {
                try {
                    showLoading();
                    getView().clearMyFootprints();
                    getMyFootprints.deleteCache();
                } catch (Exception e) {}
            } catch (Exception e) {}

            loadMyFootprints();
        } else {

            PaginatedCollection<MyFootprint> allMyFootprintsInCache = getMyFootprints.getAllMyFootprintsInCache();

            if (allMyFootprintsInCache.getPage().getLimit() == 0) {
                loadMyFootprints();
            } else {
                try {
                    try {
                        getView().clearMyFootprints();
                    } catch (Exception e) {}
                    showMyFootprints(allMyFootprintsInCache);
                } catch (Exception e) {}
            }
        }
    }

    private void loadMyFootprints() {

        if (pageNumber > 0) {
            try {
                getView().showLoadingBottom();
            } catch (Exception e) {}
        }

        createUseCaseCall(getMyFootprints)
                .args(pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_FOOTPRINTS_PER_PAGE, NUMBER_OF_MY_FOOTPRINTS_PER_PAGE))
                .useCaseName(GetMyFootprints.USE_CASE_GET_MY_FOOTPRINTS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprints(PaginatedGenericTotalCollection<MyFootprint> myFootprints) {

                        totalMyFootprints = myFootprints.getPaginatedCollection().getItems().size();

                        if (!myFootprints.getPaginatedCollection().getItems().isEmpty()) {
                            getMyFootprints.setHasMore(myFootprints.getPaginatedCollection().hasMore());

                            pageNumber++;
                            showMyFootprints(myFootprints.getPaginatedCollection());
                        } else {
                            showEmptyMyFootprints();
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

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public PaginatedCollection<MyFootprint> getMyFootprintsInLocalCache() {
        return getMyFootprints.getMyFootprintsInLocalCache();
    }

    private void showMyFootprints(PaginatedCollection<MyFootprint> myFootprints) {
        try {
            List<MyFootprintViewModelContract> myFootprintViewModels = mapperMyFootprints.map(myFootprints);
            keyPositions.addAll(mapperMyFootprints.getKeyPositions());
            getView().showMyFootprints(myFootprintViewModels);

            getView().showHasMore(myFootprints.hasMore());

            isFirstTime = false;

            hideLoading();
            getView().hideLoadingBottom();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        loadMyFootprints();
    }

    private void showEmptyMyFootprints() {
        try {
            getView().clearMyFootprints();
            hideLoading();
            getView().hideLoadingBottom();
            getView().showEmptyMyFootprints();
        } catch (Exception e) {}
    }

    @Override
    public void onMyFootprintClicked(MyFootprintViewModel myFootprint, int position) {
        String footprintMainKey = myFootprint.getKey();
        long comments = myFootprint.getComments();
        getView().openMyFootprintDetails(footprintMainKey, comments, position);
    }

    @Override
    public void onMyFootprintsMoreClicked() {
        // Unused
    }

    @Override
    public void onMyFootprintDeleted(String footprintKey, boolean isDead, long scope, long likes, long dislikes) {
        try {
            getView().onMyFootprintDeleted(getMyFootprintKeyPosition(footprintKey), isDead, scope, likes, dislikes);
            removeMyFootprintCollectionKey(footprintKey);

            if (totalMyFootprints == 0) {
                showEmptyMyFootprints();
            }

            createUseCaseCall(deleteMyFootprint)
                    .args(footprintKey)
                    .useCaseName(DeleteMyFootprint.USE_CASE_DELETE_MY_FOOTPRINT)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void deleteMyFootprint(boolean myFootprintDeleted) {
                            if (!myFootprintDeleted) {
                                try {
                                    getView().showMyFootprintDeletedError();
                                } catch (Exception e) {}
                            }
                        }
                    })
                    .onError(error -> {
                        try {
                            getView().showMyFootprintDeletedError();
                        } catch (Exception e) {}

                        return false;
                    }).execute();

        } catch (Exception e) {}
    }

    public void removeMyFootprintCollectionKey(String myFootprintKey) {
        keyPositions.remove(myFootprintKey);
        getMyFootprints.deleteCacheByKey(myFootprintKey);
        getMyFootprints.deleteInLocalCacheByKey(myFootprintKey);
        footprintsMainPresenter.removeFootprintMainInCache(myFootprintKey);
        totalMyFootprints--;
    }

    public int getMyFootprintKeyPosition(String myFootprintKey) {
        return keyPositions.indexOf(myFootprintKey);
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideMyFootprints();

        void showMyFootprints(List<MyFootprintViewModelContract> myFootprints);

        void showHasMore(boolean hasMore);

        void clearMyFootprints();

        void openMyFootprintDetails(String myFootprintKey, long comments, int position);

        void showEmptyMyFootprints();

        void showLoadingBottom();

        void hideLoadingBottom();

        void onMyFootprintDeleted(int position, boolean isDead, long scope, long likes, long dislikes);

        void showMyFootprintDeletedError();
    }
}
