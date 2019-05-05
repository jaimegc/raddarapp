package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateComplimentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.UpdateComplimentDtoBuilder;
import com.raddarapp.domain.model.UpdateCompliment;
import com.raddarapp.domain.model.builder.UpdateComplimentBuilder;


public class UpdateComplimentToUpdateComplimentDtoMapper extends Mapper<UpdateCompliment, UpdateComplimentDto> {

    @Override
    public UpdateCompliment reverseMap(UpdateComplimentDto updateComplimentDto) {
        UpdateCompliment updateCompliment = new UpdateComplimentBuilder()
                .withUserKey(updateComplimentDto.getUserId())
                .withCompliments(updateComplimentDto.getCompliments())
                .build();

        return updateCompliment;
    }

    @Override
    public UpdateComplimentDto map(UpdateCompliment updateCompliment) {
        final UpdateComplimentDto updateComplimentDto = new UpdateComplimentDtoBuilder()
                .withUserId(updateCompliment.getKey())
                .withCompliments(updateCompliment.getCompliments())
                .build();

        return updateComplimentDto;
    }
}
