package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.User;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserProfileViewModelBuilder;

import javax.inject.Inject;

public class UserToUserProfileViewModelMapper extends Mapper<User, UserProfileViewModel> {

    @Inject
    public UserToUserProfileViewModelMapper() {
    }

    @Override
    public UserProfileViewModel map(User user) {
        NumberUtils numberUtils = new NumberUtils();

        UserProfileViewModel userProfileViewModel = new UserProfileViewModelBuilder()
                .withKey(user.getKey())
                .withUsername("@" + user.getUsername())
                .withImage(user.getImage())
                .withAudio(user.getAudio())
                .withRange(user.getRange())
                .withFollowers(numberUtils.numberToGroupedString(user.getFollowers()))
                .withFollowing(numberUtils.numberToGroupedString(user.getFollowing()))
                .withTotalFootprints(numberUtils.numberToGroupedString(user.getTotalFootprints()))
                .withLevel(String.valueOf(user.getLevel()))
                .withUserCompliments(user.getUserCompliments())
                .withTotalLikes(user.getTotalLikes())
                .withTotalDislikes(user.getTotalDislikes())
                .withTotalFootprintsDead(numberUtils.numberToGroupedString(user.getTotalFootprintsDead()))
                .build();

        return userProfileViewModel;
    }

    @Override
    public User reverseMap(UserProfileViewModel value) {
        throw new UnsupportedOperationException();
    }
}