package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.MyUserProfileBuilder;

public class MyUserProfileToMyUserProfileDtoMapper extends Mapper<MyUserProfile, MyUserProfileDto> {

    @Override
    public MyUserProfile reverseMap(MyUserProfileDto userProfileDto) {
        final MyUserProfile userProfile = new MyUserProfileBuilder()
                .withKey(userProfileDto.getId())
                .withName(userProfileDto.getName())
                .withSurname(userProfileDto.getSurname())
                .withUsername(userProfileDto.getUsername())
                .withImage(userProfileDto.getImage())
                .withAudio(userProfileDto.getAudio())
                .withEmail(userProfileDto.getEmail())
                .withRange(userProfileDto.getRange())
                .withFollowers(userProfileDto.getFollowers())
                .withFollowing(userProfileDto.getFollowing())
                .withTotalFootprints(userProfileDto.getTotalFootprints())
                .withUserCompliments(userProfileDto.getUserCompliments())
                .withLevel(userProfileDto.getLevel())
                .withPercentage(userProfileDto.getPercentage())
                .withAccessToken(userProfileDto.getAccessToken())
                .withRefreshToken(userProfileDto.getRefreshToken())
                .withRangeMined(userProfileDto.getRangeMined())
                .withRangeNotMined(userProfileDto.getRangeNotMined())
                .withMiningDate(userProfileDto.getMiningDate())
                .withGender(userProfileDto.getGender())
                .withBirthdate(userProfileDto.getBirthdate())
                .withRole(userProfileDto.getRole())
                .withStatus(userProfileDto.getStatus())
                .withNotificationTopics(userProfileDto.getNotificationTopics())
                .withNotificationToken(userProfileDto.getNotificationToken())
                .withTotalLikes(userProfileDto.getTotalLikes())
                .withTotalDislikes(userProfileDto.getTotalDislikes())
                .withTotalFootprintsDead(userProfileDto.getTotalFootprintsDead())
                .withMobileLanguage(userProfileDto.getMobileLanguage())
                .build();

        return userProfile;
    }

    @Override
    public MyUserProfileDto map(MyUserProfile userProfile) {
        throw new UnsupportedOperationException();
    }

}
