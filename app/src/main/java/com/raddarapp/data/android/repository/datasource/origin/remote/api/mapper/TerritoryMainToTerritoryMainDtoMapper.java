package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryMainDto;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.model.builder.TerritoryMainBuilder;

public class TerritoryMainToTerritoryMainDtoMapper extends Mapper<TerritoryMain, TerritoryMainDto> {

    private final TerritoryToTerritoryDtoMapper mapperTerritory = new TerritoryToTerritoryDtoMapper();
    private final TerritoryAreaToTerritoryAreaDtoMapper mapperTerritoryArea = new TerritoryAreaToTerritoryAreaDtoMapper();

    @Override
    public TerritoryMainDto map(TerritoryMain territoryMain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TerritoryMain reverseMap(TerritoryMainDto territoryMainDto) {

        final TerritoryMain territoryMain = new TerritoryMainBuilder()
                .withKey(territoryMainDto.getTerritory().getId())
                .withTerritory(mapperTerritory.reverseMap(territoryMainDto.getTerritory()))
                .withTerritoryArea(mapperTerritoryArea.reverseMap(territoryMainDto.getTerritoryArea()))
                .build();

        return territoryMain;
    }
}
