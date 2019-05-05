package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.User;
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

public class UserToolbarPresenter extends RosiePresenter<UserToolbarPresenter.View> {

    private static final int INDEX_FOOTPRINT_MAIN = 0;
    private static final int INDEX_MY_USERS_RANKING = 1;
    private static final int INDEX_MY_FOOTPRINTS_COLLECTION = 2;
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
    private final GetFootprintMainDetails getFootprintsDetails;

    // From My User Ranking
    private final GetMyUserRankingProfile getMyUserRanking;

    // From My Footprint Collection
    private final GetMyFootprintCollectionDetails getMyFootprintCollectionDetails;

    // From Footprint Ranking
    private final GetFootprintRankingDetails getFootprintRankingDetails;

    @Inject
    public UserToolbarPresenter(UseCaseHandler useCaseHandler, FollowUser followUser, UnfollowUser unfollowUser,
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
            case INDEX_MY_FOOTPRINTS_COLLECTION:
                loadMyUserFromMyFootprintCollection();
                break;
            case INDEX_FOOTPRINT_RANKING:
                loadMyUserFromFootprintRanking();
                break;
        }
    }

    private void loadUserFromFootprintMain() {
        showUserProfile(getFootprintsDetails.getUserFootprintMainInLocalCache());
    }

    private void loadMyUserFromRanking() {
        showUserProfile(getMyUserRanking.getUserFootprintInLocalCache());
    }

    private void loadMyUserFromMyFootprintCollection() {
        showUserProfile(getMyFootprintCollectionDetails.getUserMyFootprintCollectionInLocalCache());
    }

    private void loadMyUserFromFootprintRanking() {
        showUserProfile(getFootprintRankingDetails.getUserFootprintRankingInLocalCache());
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

    public void setFootprintKey(String footprintKey) {
        this.footprintKey = footprintKey;
    }

    public void setIndexScreen(int indexScreen) {
        this.indexScreen = indexScreen;
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showUserProfile(UserProfileViewModel userProfileDetailsViewModel);
    }
}
