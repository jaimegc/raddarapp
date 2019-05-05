package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdatePasswordProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.UpdatePasswordProfileDtoBuilder;
import com.raddarapp.domain.model.UpdatePasswordProfile;
import com.raddarapp.domain.model.builder.UpdatePasswordProfileBuilder;

public class UpdatePasswordProfileToUpdatePasswordProfileDtoMapper extends Mapper<UpdatePasswordProfile, UpdatePasswordProfileDto> {

    @Override
    public UpdatePasswordProfileDto map(UpdatePasswordProfile updatePasswordProfile) {
        final UpdatePasswordProfileDto updatePasswordProfileDto = new UpdatePasswordProfileDtoBuilder()
                .withPassword(updatePasswordProfile.getPassword())
                .withOldPassword(updatePasswordProfile.getOldPassword())
                .build();

        return updatePasswordProfileDto;
    }

    @Override
    public UpdatePasswordProfile reverseMap(UpdatePasswordProfileDto updatePasswordProfileDto) {
        final UpdatePasswordProfile updatePasswordProfile = new UpdatePasswordProfileBuilder()
                .withPassword(updatePasswordProfileDto.getPassword())
                .withOldPassword(updatePasswordProfileDto.getOldPassword())
                .build();

        return updatePasswordProfile;
    }
}
