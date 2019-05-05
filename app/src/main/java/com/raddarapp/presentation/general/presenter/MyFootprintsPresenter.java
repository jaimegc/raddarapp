package com.raddarapp.presentation.general.presenter;

import android.location.Location;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.UpdateCompliment;
import com.raddarapp.domain.model.builder.UpdateComplimentBuilder;
import com.raddarapp.domain.usecase.DeleteMyFootprint;
import com.raddarapp.domain.usecase.GetMyFootprints;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.domain.usecase.UpdateComplimentProfile;
import com.raddarapp.presentation.general.presenter.contract.MyFootprintsPresenterContract;
import com.raddarapp.presentation.general.application.RaddarApplicationWrapperContract;
import com.raddarapp.presentation.general.presenter.base.BasePresenterSwipeRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintEmptyViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintToMyFootprintViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintsToMyFootprintsViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsPresenter extends BasePresenterSwipeRefreshWithLoading<MyFootprintsPresenter.View> implements MyFootprintsPresenterContract {

    private static final int NUMBER_OF_MY_FOOTPRINTS_PER_PAGE = 11;
    private final MyFootprintsToMyFootprintsViewModelMapper mapperMyFootprints;
    private final MyFootprintToMyFootprintViewModelMapper mapperMyFootprint;
    private final GetMyFootprints getMyFootprints;
    private final GetMyUserProfile getUserProfile;
    private final DeleteMyFootprint deleteMyFootprint;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final RaddarApplicationWrapperContract raddarApplicationWrapper;
    private final UpdateComplimentProfile updateComplimentProfile;
    private final FootprintsMainPresenter footprintsMainPresenter;
    // Always 0 because is limited the footprints size
    private Integer pageNumber = 0;
    private Location lastLocation;
    // Static because when we adding a new footprint this value is reset
    private static List<String> keyPositions = new LinkedList<>();
    private static long totalMyFootprints = 0;

    private MyUserProfile userProfile = null;

    @Inject
    public MyFootprintsPresenter(UseCaseHandler useCaseHandler, MyFootprintsToMyFootprintsViewModelMapper mapperMyFootprints,
            GetMyFootprints getMyFootprints, MyUserProfileToUserProfileViewModelMapper mapperUserProfile, GetMyUserProfile getUserProfile,
            MyFootprintToMyFootprintViewModelMapper mapperMyFootprint, RaddarApplicationWrapperContract raddarApplicationWrapper,
            UpdateComplimentProfile updateComplimentProfile, DeleteMyFootprint deleteMyFootprint,
            FootprintsMainPresenter footprintsMainPresenter) {
        super(useCaseHandler);
        this.mapperMyFootprints = mapperMyFootprints;
        this.mapperMyFootprint = mapperMyFootprint;
        this.mapperUserProfile = mapperUserProfile;
        this.getMyFootprints = getMyFootprints;
        this.getUserProfile = getUserProfile;
        this.raddarApplicationWrapper = raddarApplicationWrapper;
        this.updateComplimentProfile = updateComplimentProfile;
        this.deleteMyFootprint = deleteMyFootprint;
        this.footprintsMainPresenter = footprintsMainPresenter;
    }

    @Override
    public void initialize() {
        super.initialize();
        keyPositions = new LinkedList<>();
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();
        loadData();
    }

    public void loadData() {

        getView().hideMyFootprints();

        PaginatedCollection<MyFootprint> allMyFootprintsInCache = getMyFootprints.getAllMyFootprintsInCacheOrderByDate(NUMBER_OF_MY_FOOTPRINTS_PER_PAGE);

        if (!allMyFootprintsInCache.getItems().isEmpty()) {
            getMyFootprints.addMyFootprintsInLocalCache(allMyFootprintsInCache);
        } else {
            allMyFootprintsInCache = getMyFootprints.getMyFootprintsInLocalCache();
        }

        if (allMyFootprintsInCache == null || allMyFootprintsInCache.getPage().getLimit() == 0 || allMyFootprintsInCache.getItems().isEmpty()) {
            loadMyFootprints(false);
        } else {
            clearMyFootprints();
            showMyFootprints(allMyFootprintsInCache);
        }

    }

    @Override
    public void forceRefreshing() {
        if (!isForceRefreshing) {
            getMyFootprints.deleteCache();
            totalMyFootprints = 0;
            keyPositions = new LinkedList<>();
            pageNumber = 0;
            loadMyFootprints(true);
        }
    }

    private void loadMyFootprints(boolean fromForceRefreshing) {

        isForceRefreshing = fromForceRefreshing;

        if (!fromForceRefreshing) {
            getView().showLoading();
        } else {
            getView().showRefreshing();
        }

        createUseCaseCall(getMyFootprints)
                .args(pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_MY_FOOTPRINTS_PER_PAGE, NUMBER_OF_MY_FOOTPRINTS_PER_PAGE))
                .useCaseName(GetMyFootprints.USE_CASE_GET_MY_FOOTPRINTS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprints(PaginatedGenericTotalCollection<MyFootprint> myFootprints) {

                        totalMyFootprints = myFootprints.getTotalItems();

                        getMyFootprints.addMyFootprintsInLocalCache(myFootprints.getPaginatedCollection());

                        if (!myFootprints.getPaginatedCollection().getItems().isEmpty()) {
                            getMyFootprints.setHasMore(myFootprints.getPaginatedCollection().hasMore());
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

    public void updateMyCompliments(long compliments) {

        if (userProfile != null) {
            UpdateCompliment updateCompliment = new UpdateComplimentBuilder()
                    .withUserKey(userProfile.getKey())
                    .withCompliments(compliments)
                    .build();

            createUseCaseCall(updateComplimentProfile)
                    .args(updateCompliment)
                    .useCaseName(UpdateComplimentProfile.USE_CASE_UPDATE_MY_COMPLIMENTS)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void updateMyCompliments(boolean isUpdatedCompliment) {
                        }

                    })
                    .onError(error -> false).execute();
        }
    }

    private void showError() {
        hideLoading();
        hideRefreshing();
    }

    private void loadUserProfile() {

        createUseCaseCall(getUserProfile)
                .useCaseName(getUserProfile.USE_CASE_GET_USER_PROFILE_PREFERENCES)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getUserProfilePreferences(MyUserProfile userProfilePreferences) {
                        userProfile = userProfilePreferences;
                        showUserProfilePreferences(userProfile);
                    }
                })
                .onError(error -> {
                    showError();

                    return false;
                }).execute();
    }

    private void showUserProfilePreferences(MyUserProfile userProfilePreferences) {

        try {
            MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);
            getView().showUserProfilePreferences(myUserProfileViewModel);
        } catch (Exception e) {}
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    private void showMyFootprints(PaginatedCollection<MyFootprint> myFootprints) {
        try {
            hideRefreshing();

            // Always clear because is limited the footprints size
            getView().clearMyFootprints();

            List<MyFootprintViewModelContract> myFootprintViewModels = mapperMyFootprints.map(myFootprints);
            keyPositions = mapperMyFootprints.getKeyPositions();

            getView().showMyFootprints(myFootprintViewModels);
            getView().showHasMore(myFootprints.hasMore());

            hideLoading();
        } catch (Exception e) {}
    }

    private void clearMyFootprints() {
        try {
            getView().clearMyFootprints();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        // Unused because there is not paginated
    }

    private void showEmptyMyFootprints() {
        try {
            getView().clearMyFootprints();
            hideRefreshing();
            hideLoading();
            List<MyFootprintViewModelContract> myFootprintsViewModel = new ArrayList<>();
            myFootprintsViewModel.add(new MyFootprintEmptyViewModelBuilder().build());
            getView().showEmptyMyFootprints(myFootprintsViewModel);
        } catch (Exception e) {}
    }


    public void onMyFootprintClicked(MyFootprintViewModel myFootprint, int position) {
        String footprintMainKey = myFootprint.getKey();
        long comments = myFootprint.getComments();
        getView().openMyFootprintDetails(footprintMainKey, comments, position);
    }

    @Override
    public void onMyFootprintsMoreClicked() {
        getView().openMyFootprintsAll();
    }

    @Override
    public void onMyFootprintDeleted(String footprintKey, boolean isDead, long scope, long likes, long dislikes) {
        try {
            getView().onMyFootprintDeleted(getMyFootprintKeyPosition(footprintKey), isDead, scope, likes, dislikes);
            removeMyFootprintKey(footprintKey);
            if (!showHasMoreAfterDelete()) {
                getView().showHasMore(false);

                if (totalMyFootprints == 0) {
                    showEmptyMyFootprints();
                }
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

    public void removeMyFootprintKey(String myFootprintKey) {
        keyPositions.remove(myFootprintKey);
        getMyFootprints.deleteCacheByKey(myFootprintKey);
        getMyFootprints.deleteInLocalCacheByKey(myFootprintKey);
        footprintsMainPresenter.removeFootprintMainInCache(myFootprintKey);
        totalMyFootprints--;
    }

    public int getMyFootprintKeyPosition(String myFootprintKey) {
        return keyPositions.indexOf(myFootprintKey);
    }

    public boolean showHasMoreAfterDelete() {
        return totalMyFootprints > NUMBER_OF_MY_FOOTPRINTS_PER_PAGE;
    }

    public void addMyFootprintInCache(MyFootprint myFootprint) {
        List<MyFootprintViewModelContract> myFootprints = new ArrayList<>();
        myFootprints.add(mapperMyFootprint.map(myFootprint));
        myFootprints.addAll(mapperMyFootprints.map(getMyFootprints.getAllMyFootprintsInCache()));
        ((LinkedList) keyPositions).addFirst(myFootprint.getKey());
        totalMyFootprints++;

        List<MyFootprintViewModelContract> myFootprintsViewModel = mapperMyFootprints.map(
                getMyFootprints.addMyFootprintInCache(myFootprint));

        try {
            // FIXME: This is code smell :[
            raddarApplicationWrapper.updateMyFootprints(myFootprintsViewModel);
        } catch (Exception e) {}
    }

    public interface View extends BasePresenterSwipeRefreshWithLoading.View {
        void hideMyFootprints();

        void showMyFootprints(List<MyFootprintViewModelContract> myFootprints);

        void showHasMore(boolean hasMore);

        void clearMyFootprints();

        void openMyFootprintDetails(String myFootprintKey, long comments, int position);

        void showEmptyMyFootprints(List<MyFootprintViewModelContract> myFootprintsEmpty);

        void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel);

        void openMyFootprintsAll();

        void onMyFootprintDeleted(int position, boolean isDead, long scope, long likes, long dislikes);

        void showMyFootprintDeletedError();
    }
}
