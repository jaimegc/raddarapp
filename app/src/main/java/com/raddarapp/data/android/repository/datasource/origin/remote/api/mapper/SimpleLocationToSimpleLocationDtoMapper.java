package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.SimpleLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.SimpleLocationDtoBuilder;
import com.raddarapp.domain.model.SimpleLocation;
import com.raddarapp.domain.model.builder.SimpleLocationBuilder;

import java.util.ArrayList;
import java.util.List;

public class SimpleLocationToSimpleLocationDtoMapper extends Mapper<SimpleLocation, SimpleLocationDto> {

    @Override
    public SimpleLocationDto map(SimpleLocation simpleLocation) {

        final SimpleLocationDto simpleLocationDto = new SimpleLocationDtoBuilder()
                .withLatitude(simpleLocation.getLatitude())
                .withLongitude(simpleLocation.getLongitude())
                .build();

        return simpleLocationDto;
    }

    @Override
    public SimpleLocation reverseMap(SimpleLocationDto value) {
        throw new UnsupportedOperationException();
    }

    public List<List<SimpleLocation>> reverseMapArea(List<List<SimpleLocationDto>> simpleLocationDto) {
        List<List<SimpleLocation>> simpleLocationDtoaa = new ArrayList<>();

        for (List<SimpleLocationDto> areaPolygons : simpleLocationDto) {
            List<SimpleLocation> areaPoints = new ArrayList<>();

            for (SimpleLocationDto point : areaPolygons) {
                areaPoints.add(new SimpleLocationBuilder()
                        .withLatitude(point.getLatitude())
                        .withLongitude(point.getLongitude())
                        .build());
            }

            simpleLocationDtoaa.add(areaPoints);
        }

        return simpleLocationDtoaa;
    }
}
