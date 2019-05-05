package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.usecase.FollowUser;
import com.raddarapp.domain.usecase.UnfollowUser;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;

import javax.inject.Inject;

public class UserDetailsPresenter extends BasePresenterRefreshWithLoading<UserDetailsPresenter.View> {

    private final FollowUser followUser;
    private final UnfollowUser unfollowUser;
    private String userKey;
    private boolean isFollowing;
    private FootprintMainDetailsPresenter footprintMainDetailsPresenter;

    @Inject
    public UserDetailsPresenter(UseCaseHandler useCaseHandler, FollowUser followUser, UnfollowUser unfollowUser) {
        super(useCaseHandler);
        this.followUser = followUser;
        this.unfollowUser = unfollowUser;
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
                    getView().showError();
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
                    getView().showError();
                    return false;
                }).execute();

    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setFootprintMainDetailsPresenter(FootprintMainDetailsPresenter footprintMainDetailsPresenter) {
        this.footprintMainDetailsPresenter = footprintMainDetailsPresenter;
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

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showFollowingUser();

        void showUnfollowingUser();

        void showError();
    }
}
