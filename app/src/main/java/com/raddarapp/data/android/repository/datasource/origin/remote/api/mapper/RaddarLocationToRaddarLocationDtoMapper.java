package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.RaddarLocationDtoBuilder;
import com.raddarapp.domain.model.RaddarLocation;

public class RaddarLocationToRaddarLocationDtoMapper extends Mapper<RaddarLocation, RaddarLocationDto> {

    @Override
    public RaddarLocationDto map(RaddarLocation raddarLocation) {
        final RaddarLocationDto raddarLocationDto = new RaddarLocationDtoBuilder()
                .withLatitude(raddarLocation.getLatitude())
                .withLongitude(raddarLocation.getLongitude())
                .withCreationMoment(raddarLocation.getCreationMoment())
                .build();

        return raddarLocationDto;
    }

    @Override
    public RaddarLocation reverseMap(RaddarLocationDto raddarLocation) {
        throw new UnsupportedOperationException();
    }
}
