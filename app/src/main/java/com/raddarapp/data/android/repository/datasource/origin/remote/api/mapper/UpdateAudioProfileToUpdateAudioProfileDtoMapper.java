package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateAudioProfileDto;
import com.raddarapp.domain.model.UpdateAudioProfile;
import com.raddarapp.domain.model.builder.UpdateAudioProfileBuilder;

public class UpdateAudioProfileToUpdateAudioProfileDtoMapper extends Mapper<UpdateAudioProfile, UpdateAudioProfileDto> {

    @Override
    public UpdateAudioProfile reverseMap(UpdateAudioProfileDto updateAudioProfileDto) {
        final UpdateAudioProfile updateAudioProfile = new UpdateAudioProfileBuilder()
                .withMediaName(updateAudioProfileDto.getMediaName())
                .build();

        return updateAudioProfile;
    }

    @Override
    public UpdateAudioProfileDto map(UpdateAudioProfile updateAudioProfile) {
        throw new UnsupportedOperationException();
    }
}
