package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.TerritoryArea;

import javax.inject.Inject;

// NOTE: Cancelled this mapper to improve performance
public class TerritoryAreaToTerritoryAreaViewModelMapper extends Mapper<TerritoryArea, TerritoryArea> {

    @Inject
    public TerritoryAreaToTerritoryAreaViewModelMapper() {}

    @Override
    public TerritoryArea map(TerritoryArea territoryArea) {
        return territoryArea;
    }

    @Override
    public TerritoryArea reverseMap(TerritoryArea value) {
        throw new UnsupportedOperationException();
    }

}
