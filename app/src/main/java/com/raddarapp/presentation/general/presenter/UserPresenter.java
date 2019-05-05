package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.UpdateCompliment;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.UpdateComplimentBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.usecase.FollowUser;
import com.raddarapp.domain.usecase.GetFootprintMainDetails;
import com.raddarapp.domain.usecase.GetFootprintRankingDetails;
import com.raddarapp.domain.usecase.GetMyFootprintCollectionDetails;
import com.raddarapp.domain.usecase.GetMyUserRankingProfile;
import com.raddarapp.domain.usecase.UnfollowUser;
import com.raddarapp.domain.usecase.UpdateComplimentProfile;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserRankingToUserProfileViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.UserToUserProfileViewModelMapper;

import java.util.List;

import javax.inject.Inject;

public class UserPresenter extends RosiePresenter<UserPresenter.View> {

    private static final int INDEX_FOOTPRINT_MAIN = 0;
    private static final int INDEX_MY_USERS_RANKING = 1;
    private static final int INDEX_MY_FOOTPRINT_COLLECTION = 2;
    private static final int INDEX_FOOTPRINT_RANKING = 3;

    private final FollowUser followUser;
    private final UnfollowUser unfollowUser;
    private String footprintKey;
    private String userKey;
    private int indexScreen;
    private boolean isFollowing;
    private final UserToUserProfileViewModelMapper userToUserProfileDetailsViewModelMapper;
    private final MyUserRankingToUserProfileViewModelMapper myUserRankingToUserProfileViewModelMapper;
    private FootprintMainDetailsPresenter footprintMainDetailsPresenter;
    private final UpdateComplimentProfile updateComplimentProfile;

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
    public UserPresenter(UseCaseHandler useCaseHandler, FollowUser followUser, UnfollowUser unfollowUser,
            GetFootprintMainDetails getFootprintsDetails, UserToUserProfileViewModelMapper userToUserProfileDetailsViewModelMapper,
            GetMyUserRankingProfile getMyUserRanking, UpdateComplimentProfile updateComplimentProfile,
            MyUserRankingToUserProfileViewModelMapper myUserRankingToUserProfileViewModelMapper,
            GetMyFootprintCollectionDetails getMyFootprintCollectionDetails,
            GetFootprintRankingDetails getFootprintRankingDetails) {
        super(useCaseHandler);
        this.followUser = followUser;
        this.unfollowUser = unfollowUser;
        this.getFootprintsDetails = getFootprintsDetails;
        this.userToUserProfileDetailsViewModelMapper = userToUserProfileDetailsViewModelMapper;
        this.getMyUserRanking = getMyUserRanking;
        this.updateComplimentProfile = updateComplimentProfile;
        this.myUserRankingToUserProfileViewModelMapper = myUserRankingToUserProfileViewModelMapper;
        this.getMyFootprintCollectionDetails = getMyFootprintCollectionDetails;
        this.getFootprintRankingDetails = getFootprintRankingDetails;
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
            case INDEX_MY_FOOTPRINT_COLLECTION:
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
                        }
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void updateComplimentsMyUserRanking(String userProfileKey, long compliments) {
        createUseCaseCall(getMyUserRanking)
                .args(userProfileKey)
                .useCaseName(GetMyUserRankingProfile.USE_CASE_GET_MY_USER_RANKING_PROFILE_BY_USER_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyUserRankingProfileByUserId(MyUserRanking myUserRankingProfile) {
                        myUserRankingProfile.updateCompliments(compliments);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadUserFromFootprintMain(String userProfileKey, long compliments) {
        List<FootprintMain> footprintsMainInCacheByUserKey =
                getFootprintsDetails.getAllFootprintsMainInCacheByUserKey(userProfileKey);

        for (FootprintMain footprintMain : footprintsMainInCacheByUserKey) {
            footprintMain.updateCompliments(compliments);
        }
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

    public void followUser() {
        getView().showUnfollowingUser();

        createUseCaseCall(followUser)
                .args(userKey)
                .useCaseName(FollowUser.USE_CASE_FOLLOW_USER)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void followUser(int userRelationship) {
                        isFollowing = true;
                        footprintMainDetailsPresenter.updateUserRelationship(userRelationship);
                    }

                })
                .onError(error -> {
                    getView().showFollowingUser();
                    getView().showFollowingError();
                    return false;
                }).execute();

    }

    public void unfollowUser() {
        getView().showFollowingUser();

        createUseCaseCall(unfollowUser)
                .args(userKey)
                .useCaseName(UnfollowUser.USE_CASE_UNFOLLOW_USER)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void unfollowUser(int userRelationship) {
                        isFollowing = false;
                        footprintMainDetailsPresenter.updateUserRelationship(userRelationship);
                    }

                })
                .onError(error -> {
                    getView().showUnfollowingUser();
                    getView().showUnfollowingError();
                    return false;
                }).execute();

    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setFootprintKey(String footprintKey) {
        this.footprintKey = footprintKey;
    }

    public void setIndexScreen(int indexScreen) {
        this.indexScreen = indexScreen;
    }

    public void setFollowing(int userRelationshipType) {
        isFollowing = isFollowing(userRelationshipType);
    }

    public void initializeFollowingButton() {
        if (isFollowing) {
            getView().showUnfollowingUser();
        } else {
            getView().showFollowingUser();
        }
    }

    public void onFollowClicked() {
        if (isFollowing) {
            unfollowUser();
        } else {
            followUser();
        }
    }

    private boolean isFollowing(int userRelationshipType) {
        return (userRelationshipType == UserRelationshipType.UNKNOWN.getValue() ||
                userRelationshipType == UserRelationshipType.FOLLOW_ME.getValue()) ? false : true;
    }

    public void updateCompliments(long compliments) {

        if (indexScreen == INDEX_FOOTPRINT_MAIN) {
            footprintMain.getUser().updateCompliments(compliments);
            // Update user ranking if exists
            updateComplimentsMyUserRanking(footprintMain.getUser().getKey(), compliments);
        } else if (indexScreen == INDEX_MY_USERS_RANKING) {
            myUserRanking.updateCompliments(compliments);
            // Update user footprints if exists
            loadUserFromFootprintMain(userKey, compliments);
        } else if (indexScreen == INDEX_MY_FOOTPRINT_COLLECTION) {
            myFootprintCollection.updateCompliments(compliments);
            // Update user footprints if exists
            loadUserFromFootprintMain(userKey, compliments);
            // Update user ranking if exists
            updateComplimentsMyUserRanking(myFootprintCollection.getUser().getKey(), compliments);
        } else if (indexScreen == INDEX_FOOTPRINT_RANKING) {
            footprintRanking.getUser().updateCompliments(compliments);
            // Update user ranking if exists
            updateComplimentsMyUserRanking(footprintRanking.getUser().getKey(), compliments);
            // Update user footprints if exists
            loadUserFromFootprintMain(userKey, compliments);

        }

        UpdateCompliment updateCompliment = new UpdateComplimentBuilder()
                .withUserKey(userKey)
                .withCompliments(compliments)
                .build();

        createUseCaseCall(updateComplimentProfile)
                .args(updateCompliment)
                .useCaseName(UpdateComplimentProfile.USE_CASE_UPDATE_COMPLIMENTS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateCompliments(boolean isUpdatedCompliment) {}
                })
                .onError(error -> false).execute();
    }

    public void updateUserCompliments(long compliments) {
    }

    public void deleteUserFootprintInLocalCache() {
        getFootprintsDetails.deleteUserFootprintMainInLocalCache();
        getMyUserRanking.deleteUserFootprintInLocalCache();
        getMyFootprintCollectionDetails.deleteUserFootprintMainInLocalCache();
        getFootprintRankingDetails.deleteUserFootprintMainInLocalCache();
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showFollowingUser();

        void showUnfollowingUser();

        void showFollowingError();

        void showUnfollowingError();

        void showUserProfile(UserProfileViewModel userProfileDetailsViewModel);
    }
}
