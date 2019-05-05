package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.presentation.general.viewmodel.TerritoryMainViewModel;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.TerritoryMainViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryMainViewModelContract;

import javax.inject.Inject;

// NOTE: Cancelled this mapper to improve performance
public class TerritoryMainToTerritoryMainViewModelMapper extends Mapper<TerritoryMain, TerritoryMainViewModelContract> {

    private final TerritoryToTerritoryViewModelMapper mapperTerritoryToTerritoryViewModelMapper;
    private final TerritoryAreaToTerritoryAreaViewModelMapper mapperTerritoryAreaToTerritoryAreaViewModelMapper;

    @Inject
    public TerritoryMainToTerritoryMainViewModelMapper(TerritoryToTerritoryViewModelMapper mapperTerritoryToTerritoryViewModelMapper,
        TerritoryAreaToTerritoryAreaViewModelMapper mapperTerritoryAreaToTerritoryAreaViewModelMapper) {
        this.mapperTerritoryToTerritoryViewModelMapper = mapperTerritoryToTerritoryViewModelMapper;
        this.mapperTerritoryAreaToTerritoryAreaViewModelMapper = mapperTerritoryAreaToTerritoryAreaViewModelMapper;
    }

    @Override
    public TerritoryMainViewModelContract map(TerritoryMain territoryMain) {
        final TerritoryMainViewModel territoryMainViewModel = new TerritoryMainViewModelBuilder()
                .withTerritoryViewModel((TerritoryViewModel) mapperTerritoryToTerritoryViewModelMapper.map(territoryMain.getTerritory()))
                .withTerritoryArea(mapperTerritoryAreaToTerritoryAreaViewModelMapper.map(territoryMain.getTerritoryArea()))
                .build();

        return territoryMainViewModel;
    }

    @Override
    public TerritoryMain reverseMap(TerritoryMainViewModelContract value) {
        throw new UnsupportedOperationException();
    }


}
