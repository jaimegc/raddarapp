package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.usecase.DeleteMyFootprintCollection;
import com.raddarapp.domain.usecase.GetMyFootprintsCollection;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintsCollectionToMyFootprintsCollectionViewModelMapper;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsCollectionPresenter extends BasePresenterRefreshWithLoading<MyFootprintsCollectionPresenter.View> {

    private static int NUMBER_OF_MY_FOOTPRINTS_COLLECTION_PER_PAGE = 20;
    private final MyFootprintsCollectionToMyFootprintsCollectionViewModelMapper mapperMyFootprintsCollection;
    private final GetMyFootprintsCollection getMyFootprintsCollection;
    private final DeleteMyFootprintCollection deleteMyFootprintCollection;
    private Integer pageNumber = 0;
    private long totalMyFootprintsCollection = 0;
    private boolean isFirstTime = true;
    private List<String> keyPositions = new LinkedList<>();

    @Inject
    public MyFootprintsCollectionPresenter(UseCaseHandler useCaseHandler, MyFootprintsCollectionToMyFootprintsCollectionViewModelMapper mapperMyFootprintsCollection,
            GetMyFootprintsCollection getMyFootprintsCollection, DeleteMyFootprintCollection deleteMyFootprintCollection) {
        super(useCaseHandler);
        this.mapperMyFootprintsCollection = mapperMyFootprintsCollection;
        this.getMyFootprintsCollection = getMyFootprintsCollection;
        this.deleteMyFootprintCollection = deleteMyFootprintCollection;
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

        getView().hideMyFootprintsCollection();

        if (isFirstTime) {
            try {
                try {
                    showLoading();
                    getView().clearMyFootprintsCollection();
                    getMyFootprintsCollection.deleteCache();
                } catch (Exception e) {}
            } catch (Exception e) {}

            loadMyFootprintsCollection();
        } else {

            PaginatedCollection<MyFootprintCollection> allMyFootprintsInCache = getMyFootprintsCollection.getAllMyFootprintsCollectionInCache();

            if (allMyFootprintsInCache.getPage().getLimit() == 0) {
                loadMyFootprintsCollection();
            } else {
                try {
                    try {
                        getView().clearMyFootprintsCollection();
                    } catch (Exception e) {}
                    showMyFootprintsCollection(allMyFootprintsInCache);
                } catch (Exception e) {}
            }
        }
    }

    private void loadMyFootprintsCollection() {

        if (pageNumber > 0) {
            try {
                getView().showLoadingBottom();
            } catch (Exception e) {}
        }

        createUseCaseCall(getMyFootprintsCollection)
                .args(pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_FOOTPRINTS_COLLECTION_PER_PAGE, NUMBER_OF_MY_FOOTPRINTS_COLLECTION_PER_PAGE))
                .useCaseName(GetMyFootprintsCollection.USE_CASE_GET_MY_FOOTPRINTS_COLLECTION)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintsCollection(PaginatedGenericTotalCollection<MyFootprintCollection> myFootprintsCollection) {

                        totalMyFootprintsCollection = myFootprintsCollection.getPaginatedCollection().getItems().size();

                        if (!myFootprintsCollection.getPaginatedCollection().getItems().isEmpty()) {
                            getMyFootprintsCollection.setHasMore(myFootprintsCollection.getPaginatedCollection().hasMore());

                            pageNumber++;
                            showMyFootprintsCollection(myFootprintsCollection.getPaginatedCollection());
                        } else {
                            showEmptyMyFootprintsCollection();
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

    private void showMyFootprintsCollection(PaginatedCollection<MyFootprintCollection> myFootprintsCollection) {
        try {
            List<MyFootprintCollectionViewModelContract> myFootprintCollectionViewModels = mapperMyFootprintsCollection.map(myFootprintsCollection);
            keyPositions.addAll(mapperMyFootprintsCollection.getKeyPositions());
            getView().showMyFootprintsCollection(myFootprintCollectionViewModels);

            getView().showHasMore(myFootprintsCollection.hasMore());

            isFirstTime = false;

            hideLoading();
            getView().hideLoadingBottom();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        loadMyFootprintsCollection();
    }

    private void showEmptyMyFootprintsCollection() {
        try {
            getView().clearMyFootprintsCollection();
            hideLoading();
            getView().hideLoadingBottom();
            getView().showEmptyMyFootprintsCollection();
        } catch (Exception e) {}
    }

    public void onMyFootprintCollectionDelete(String footprintCollectionKey) {
        try {
            getView().onMyFootprintCollectionDeleted(getMyFootprintCollectionKeyPosition(footprintCollectionKey));
            removeMyFootprintCollectionKey(footprintCollectionKey);

            if (totalMyFootprintsCollection == 0) {
                showEmptyMyFootprintsCollection();
            }

            createUseCaseCall(deleteMyFootprintCollection)
                    .args(footprintCollectionKey)
                    .useCaseName(DeleteMyFootprintCollection.USE_CASE_DELETE_MY_FOOTPRINT_COLLECTION)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void deleteMyFootprintCollection(boolean myFootprintCollectionDeleted) {
                            if (!myFootprintCollectionDeleted) {
                                try {
                                    getView().showMyFootprintCollectionDeletedError();
                                } catch (Exception e) {}
                            }
                        }
                    })
                    .onError(error -> {
                        try {
                            getView().showMyFootprintCollectionDeletedError();
                        } catch (Exception e) {}

                        return false;
                    }).execute();

        } catch (Exception e) {}
    }

    public void removeMyFootprintCollectionKey(String myFootprintCollectionKey) {
        keyPositions.remove(myFootprintCollectionKey);
        getMyFootprintsCollection.deleteCacheByKey(myFootprintCollectionKey);
        totalMyFootprintsCollection--;
    }

    public int getMyFootprintCollectionKeyPosition(String myFootprintKey) {
        return keyPositions.indexOf(myFootprintKey);
    }

    public void onMyFootprintCollectionClicked(MyFootprintCollectionViewModel myFootprintCollection, int position) {
        String footprintMainKey = myFootprintCollection.getKey();
        long comments = myFootprintCollection.getComments();
        getView().openMyFootprintCollectionDetails(footprintMainKey, comments, position);
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideMyFootprintsCollection();

        void showMyFootprintsCollection(List<MyFootprintCollectionViewModelContract> myFootprintsCollection);

        void showHasMore(boolean hasMore);

        void clearMyFootprintsCollection();

        void openMyFootprintCollectionDetails(String myFootprintCollectionKey, long comments, int position);

        void showEmptyMyFootprintsCollection();

        void showLoadingBottom();

        void hideLoadingBottom();

        void onMyFootprintCollectionDeleted(int position);

        void showMyFootprintCollectionDeletedError();
    }
}
