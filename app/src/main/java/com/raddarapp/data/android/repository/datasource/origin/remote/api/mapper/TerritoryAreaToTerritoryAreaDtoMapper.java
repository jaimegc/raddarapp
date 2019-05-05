package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryAreaDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.TerritoryAreaDtoBuilder;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.builder.TerritoryAreaBuilder;

import java.util.UUID;

public class TerritoryAreaToTerritoryAreaDtoMapper extends Mapper<TerritoryArea, TerritoryAreaDto> {

    private final SimpleLocationToSimpleLocationDtoMapper mapper = new SimpleLocationToSimpleLocationDtoMapper();

    @Override
    public TerritoryAreaDto map(TerritoryArea territoryArea) {
        final TerritoryAreaDto territoryAreaDto = new TerritoryAreaDtoBuilder()
                .build();

        return territoryAreaDto;
    }

    @Override
    public TerritoryArea reverseMap(TerritoryAreaDto territoryAreaDto) {

        final TerritoryArea territoryArea = new TerritoryAreaBuilder()
                .withKey(territoryAreaDto.getId() == null ? UUID.randomUUID().toString().replace("-", "") : territoryAreaDto.getId())
                .withArea(mapper.reverseMapArea(territoryAreaDto.getArea()))
                .build();

        return territoryArea;
    }
}
