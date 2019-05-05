package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateImageProfileDto;
import com.raddarapp.domain.model.UpdateImageProfile;
import com.raddarapp.domain.model.builder.UpdateImageProfileBuilder;

public class UpdateImageProfileToUpdateImageProfileDtoMapper extends Mapper<UpdateImageProfile, UpdateImageProfileDto> {

    @Override
    public UpdateImageProfile reverseMap(UpdateImageProfileDto updateImageProfileDto) {
        final UpdateImageProfile updateImageProfile = new UpdateImageProfileBuilder()
                .withMediaName(updateImageProfileDto.getMediaName())
                .build();

        return updateImageProfile;
    }

    @Override
    public UpdateImageProfileDto map(UpdateImageProfile updateImageProfile) {
        throw new UnsupportedOperationException();
    }
}