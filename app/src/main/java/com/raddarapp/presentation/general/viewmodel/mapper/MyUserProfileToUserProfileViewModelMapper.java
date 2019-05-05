package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyUserProfileViewModelBuilder;

import javax.inject.Inject;

public class MyUserProfileToUserProfileViewModelMapper extends Mapper<MyUserProfile, MyUserProfileViewModel> {

    @Inject
    public MyUserProfileToUserProfileViewModelMapper() {
    }

    @Override
    public MyUserProfileViewModel map(MyUserProfile userProfile) {
        NumberUtils numberUtils = new NumberUtils();

        MyUserProfileViewModel userProfileViewModel = new MyUserProfileViewModelBuilder()
                .withKey(userProfile.getKey())
                .withName(userProfile.getName())
                .withSurname(userProfile.getSurname())
                .withUsername("@" + userProfile.getUsername())
                .withImage(userProfile.getImage())
                .withImageCache(userProfile.getImageCache())
                .withAudio(userProfile.getAudio())
                .withAudioCache(userProfile.getAudioCache())
                .withEmail(userProfile.getEmail())
                .withRange(userProfile.getRange())
                .withFollowers(numberUtils.numberToGroupedString(userProfile.getFollowers()))
                .withFollowing(numberUtils.numberToGroupedString(userProfile.getFollowing()))
                .withTotalFootprints(numberUtils.numberToGroupedString(userProfile.getTotalFootprints()))
                .withUserCompliments(userProfile.getUserCompliments())
                .withLevel(String.valueOf(userProfile.getLevel()))
                .withPercentage(userProfile.getPercentage())
                .withRangeMined(userProfile.getRangeMined())
                .withRangeNotMined(userProfile.getRangeNotMined())
                .withMiningDate(userProfile.getMiningDate())
                .withRangeMinedLocalAccumulated(userProfile.getRangeMinedLocalAccumulated())
                .withGender(userProfile.getGender())
                .withBirthdate(userProfile.getBirthdate())
                .withRole(userProfile.getRole())
                .withStatus(userProfile.getStatus())
                .withNotificationTopics(userProfile.getNotificationTopics())
                .withNotificationToken(userProfile.getNotificationToken())
                .withTotalLikes(userProfile.getTotalLikes())
                .withTotalDislikes(userProfile.getTotalDislikes())
                .withTotalFootprintsDead(numberUtils.numberToGroupedString(userProfile.getTotalFootprintsDead()))
                .withMobileLanguage(userProfile.getMobileLanguage())
                .build();

        return userProfileViewModel;
    }

    @Override
    public MyUserProfile reverseMap(MyUserProfileViewModel value) {
        throw new UnsupportedOperationException();
    }
}