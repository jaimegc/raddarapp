package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryMainViewModelContract;

public class TerritoryMainViewModel implements TerritoryMainViewModelContract {

    private final TerritoryViewModel territoryViewModel;
    private final TerritoryArea territoryArea;

    public TerritoryMainViewModel(TerritoryViewModel territoryViewModel, TerritoryArea territoryArea) {
        this.territoryViewModel = territoryViewModel;
        this.territoryArea = territoryArea;
    }

    public TerritoryViewModel getTerritoryViewModel() {
        return territoryViewModel;
    }

    public TerritoryArea getTerritoryArea() {
        return territoryArea;
    }
}
