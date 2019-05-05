package com.raddarapp.presentation.general.presenter;

import android.location.Location;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.usecase.GetFootprintMainDetails;
import com.raddarapp.domain.usecase.GetFootprintRankingDetails;
import com.raddarapp.domain.usecase.GetMyFootprintCollectionDetails;
import com.raddarapp.domain.usecase.GetMyUserRankingProfile;
import com.raddarapp.domain.usecase.GetUserFootprints;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.presenter.contract.UserFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserFootprintEmptyViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserRankingToUserProfileViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.UserFootprintsToUserFootprintsViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.UserToUserProfileViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserFootprintsAllPresenter extends BasePresenterRefreshWithLoading<UserFootprintsAllPresenter.View> implements
        UserFootprintsPresenterContract {

    private static final int INDEX_FOOTPRINT_MAIN = 0;
    private static final int INDEX_MY_USERS_RANKING = 1;
    private static final int INDEX_MY_FOOTPRINTS_COLLECTION = 2;
    private static final int INDEX_FOOTPRINT_RANKING = 3;
    private static int NUMBER_OF_USER_FOOTPRINTS_PER_PAGE = 20;
    private final UserFootprintsToUserFootprintsViewModelMapper mapperUserFootprints;
    private final GetUserFootprints getUserFootprints;
    private final UserToUserProfileViewModelMapper userToUserProfileDetailsViewModelMapper;
    private final MyUserRankingToUserProfileViewModelMapper myUserRankingToUserProfileViewModelMapper;
    private Integer pageNumber = 0;
    private Location lastLocation;
    private long totalUserFootprints = 0;
    //private UserProfile userProfile = null;
    private boolean isFirstTime = true;
    private int indexScreen;
    private String userKey;
    private String footprintKey;

    // From Footprint Main
    private FootprintMain footprintMain;
    private final GetFootprintMainDetails getFootprintsDetails;

    // From My User Ranking
    private MyUserRanking myUserRanking;
    private final GetMyUserRankingProfile getMyUserRanking;

    // From My Footprint Collection
    private MyFootprintCollection myFootprintCollection;
    private final GetMyFootprintCollectionDetails getMyFootprintCollectionDetails;

    // From Footprint Ranking
    private FootprintRanking footprintRanking;
    private final GetFootprintRankingDetails getFootprintRankingDetails;

    @Inject
    public UserFootprintsAllPresenter(UseCaseHandler useCaseHandler, UserFootprintsToUserFootprintsViewModelMapper mapperUserFootprints,
            GetUserFootprints getUserFootprints, GetFootprintMainDetails getFootprintsDetails, GetMyUserRankingProfile getMyUserRanking,
            UserToUserProfileViewModelMapper userToUserProfileDetailsViewModelMapper,
            MyUserRankingToUserProfileViewModelMapper myUserRankingToUserProfileViewModelMapper,
            GetMyFootprintCollectionDetails getMyFootprintCollectionDetails,
            GetFootprintRankingDetails getFootprintRankingDetails) {
        super(useCaseHandler);
        this.mapperUserFootprints = mapperUserFootprints;
        this.getUserFootprints = getUserFootprints;
        this.getFootprintsDetails = getFootprintsDetails;
        this.getMyUserRanking = getMyUserRanking;
        this.userToUserProfileDetailsViewModelMapper = userToUserProfileDetailsViewModelMapper;
        this.myUserRankingToUserProfileViewModelMapper = myUserRankingToUserProfileViewModelMapper;
        this.getMyFootprintCollectionDetails = getMyFootprintCollectionDetails;
        this.getFootprintRankingDetails = getFootprintRankingDetails;
    }

    @Override
    public void initialize() {
        super.initialize();
        pageNumber = 0;
        isFirstTime = true;
    }

    @Override
    public void update() {
        super.update();

        switch (indexScreen) {
            case INDEX_FOOTPRINT_MAIN:
                loadUserFromFootprintMain();
                break;
            case INDEX_MY_USERS_RANKING:
                loadMyUserFromRanking();
                break;
            case INDEX_MY_FOOTPRINTS_COLLECTION:
                loadUserFromMyFootprintCollection();
                break;
            case INDEX_FOOTPRINT_RANKING:
                loadUserFromFootprintRanking();
                break;
        }
    }

    private void loadUserFromFootprintMain() {
        createUseCaseCall(getFootprintsDetails)
                .args(footprintKey)
                .useCaseName(GetFootprintMainDetails.USE_CASE_GET_FOOTPRINT_MAIN_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintDetails(FootprintMain footprintMainDetails) {

                        if (footprintMainDetails != null && footprintMainDetails.getUser() != null) {
                            footprintMain = footprintMainDetails;
                            showUserProfile(footprintMain.getUser());
                            userKey = footprintMain.getUser().getKey();
                            loadData(null);
                        }

                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadMyUserFromRanking() {
        createUseCaseCall(getMyUserRanking)
                .args(userKey)
                .useCaseName(GetMyUserRankingProfile.USE_CASE_GET_MY_USER_RANKING_PROFILE_BY_USER_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyUserRankingProfileByUserId(MyUserRanking myUserRankingProfile) {

                        if (myUserRankingProfile != null) {
                            myUserRanking = myUserRankingProfile;
                            showUserProfile(myUserRankingProfile);
                            userKey = myUserRankingProfile.getKey();
                            loadData(null);
                        }

                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadUserFromMyFootprintCollection() {
        createUseCaseCall(getMyFootprintCollectionDetails)
                .args(footprintKey)
                .useCaseName(GetMyFootprintCollectionDetails.USE_CASE_GET_MY_FOOTPRINT_COLLECTION_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintCollectionDetails(MyFootprintCollection myFootprintCollectionDetails) {

                        if (myFootprintCollectionDetails != null && myFootprintCollectionDetails.getUser() != null) {
                            myFootprintCollection = myFootprintCollectionDetails;
                            showUserProfile(myFootprintCollection.getUser());
                            userKey = myFootprintCollection.getUser().getKey();
                            loadData(null);
                        }

                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadUserFromFootprintRanking() {
        createUseCaseCall(getFootprintRankingDetails)
                .args(footprintKey)
                .useCaseName(GetFootprintRankingDetails.USE_CASE_GET_FOOTPRINT_RANKING_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintRankingDetails(FootprintRanking footprintRankingDetails) {

                        if (footprintRankingDetails != null && footprintRankingDetails.getUser() != null) {
                            footprintRanking = footprintRankingDetails;
                            showUserProfile(footprintRanking.getUser());
                            userKey = footprintRanking.getUser().getKey();
                            loadData(null);
                        }
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showUserProfile(User user) {
        try {
            UserProfileViewModel userProfileViewModel;
            userProfileViewModel = userToUserProfileDetailsViewModelMapper.map(user);
            getView().showUserProfile(userProfileViewModel);
        } catch (Exception e) {}
    }

    private void showUserProfile(MyUserRanking user) {
        try {
            UserProfileViewModel userProfileViewModel;
            userProfileViewModel = myUserRankingToUserProfileViewModelMapper.map(user);
            getView().showUserProfile(userProfileViewModel);
        } catch (Exception e) {}
    }

    private void loadData(Location lastLocation) {
        this.lastLocation = lastLocation;

        if (isFirstTime) {
            try {
                try {
                    showLoading();
                    getView().clearUserFootprints();
                    getUserFootprints.deleteCache();
                } catch (Exception e) {}
            } catch (Exception e) {}

            loadUserFootprints(lastLocation);
        } else {

            PaginatedCollection<UserFootprint> allUserFootprintsInCache = getUserFootprints.getAllUserFootprintsInCache();

            if (allUserFootprintsInCache.getPage().getLimit() == 0) {
                loadUserFootprints(lastLocation);
            } else {
                try {
                    try {
                        getView().clearUserFootprints();
                    } catch (Exception e) {}

                    showUserFootprints(allUserFootprintsInCache);
                } catch (Exception e) {}
            }
        }
    }

    private void loadUserFootprints(Location location) {

        if (location == null) {

            if (pageNumber > 0) {
                try {
                    getView().showLoadingBottom();
                } catch (Exception e) {}
            }

            createUseCaseCall(getUserFootprints)
                    .args(userKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_USER_FOOTPRINTS_PER_PAGE, NUMBER_OF_USER_FOOTPRINTS_PER_PAGE))
                    .useCaseName(GetUserFootprints.USE_CASE_GET_USER_FOOTPRINTS)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void getUserFootprints(PaginatedGenericTotalCollection<UserFootprint> userFootprints) {

                            totalUserFootprints = userFootprints.getPaginatedCollection().getItems().size();

                            if (!userFootprints.getPaginatedCollection().getItems().isEmpty()) {
                                getUserFootprints.setHasMore(userFootprints.getPaginatedCollection().hasMore());

                                pageNumber++;
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
    }

    private void showError() {
        hideLoading();
        getView().hideLoadingBottom();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    private void showUserFootprints(PaginatedCollection<UserFootprint> userFootprints) {
        try {
            List<UserFootprintViewModelContract> userFootprintViewModels = mapperUserFootprints.map(userFootprints);
            getView().showUserFootprints(userFootprintViewModels);

            getView().showHasMore(userFootprints.hasMore());

            isFirstTime = false;

            hideLoading();
            getView().hideLoadingBottom();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        loadUserFootprints(lastLocation);
    }

    private void showEmptyUserFootprints() {
        try {
            getView().clearUserFootprints();
            hideLoading();
            getView().hideLoadingBottom();
            List<UserFootprintViewModelContract> userFootprintsViewModel = new ArrayList<>();
            userFootprintsViewModel.add(new UserFootprintEmptyViewModelBuilder().build());
            getView().showEmptyUserFootprints(userFootprintsViewModel);
        } catch (Exception e) {}
    }

    @Override
    public void onUserFootprintClicked(UserFootprintViewModel userFootprint, int position) {
        String footprintMainKey = userFootprint.getKey();
        long comments = userFootprint.getComments();
        getView().openUserFootprintDetails(footprintMainKey, comments, position);
    }

    public void setIndexScreen(int indexScreen) {
        this.indexScreen = indexScreen;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setFootprintKey(String footprintKey) {
        this.footprintKey = footprintKey;
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideUserFootprints();

        void showUserFootprints(List<UserFootprintViewModelContract> userFootprints);

        void showHasMore(boolean hasMore);

        void clearUserFootprints();

        void openUserFootprintDetails(String userFootprintKey, long comments, int position);

        void showEmptyUserFootprints(List<UserFootprintViewModelContract> userFootprintsEmpty);

        void showLoadingBottom();

        void hideLoadingBottom();

        void showUserProfile(UserProfileViewModel userProfileDetailsViewModel);
    }
}
