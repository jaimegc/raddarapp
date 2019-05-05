package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.UpdateMyUserProfileDtoBuilder;
import com.raddarapp.domain.model.UpdateMyUserProfile;
import com.raddarapp.domain.model.builder.UpdateMyUserProfileBuilder;
import com.raddarapp.presentation.android.utils.DateUtils;


public class UpdateMyUserProfileToUpdateMyUserProfileDtoMapper extends Mapper<UpdateMyUserProfile, UpdateMyUserProfileDto> {

    @Override
    public UpdateMyUserProfile reverseMap(UpdateMyUserProfileDto updateMyUserProfileDto) {
        UpdateMyUserProfile updateMyUserProfile = new UpdateMyUserProfileBuilder()
                .withName(updateMyUserProfileDto.getName())
                .withSurname(updateMyUserProfileDto.getSurname())
                .withUsername(updateMyUserProfileDto.getUsername())
                .withEmail(updateMyUserProfileDto.getEmail())
                .withGender(updateMyUserProfileDto.getGender())
                .withBirthdate(updateMyUserProfileDto.getBirthdate())
                .build();

        return updateMyUserProfile;
    }

    @Override
    public UpdateMyUserProfileDto map(UpdateMyUserProfile updateMyUserProfile) {
        DateUtils dateUtils = new DateUtils();

        final UpdateMyUserProfileDto udpateMyUserProfileDto = new UpdateMyUserProfileDtoBuilder()
                .withName(updateMyUserProfile.getName())
                .withSurname(updateMyUserProfile.getSurname())
                .withUsername(updateMyUserProfile.getUsername())
                .withEmail(updateMyUserProfile.getEmail())
                .withGender(updateMyUserProfile.getGender())
                .withBirthdate(dateUtils.mapperDateProfileToDateComplete(updateMyUserProfile.getBirthdate()))
                .build();

        return udpateMyUserProfileDto;
    }
}