package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.presentation.general.viewmodel.TerritoryMainViewModel;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;

public class TerritoryMainViewModelBuilder {

    private TerritoryViewModel territoryViewModel;
    private TerritoryArea territoryArea;

    public TerritoryMainViewModelBuilder() {}

    public TerritoryMainViewModel build() {
        return new TerritoryMainViewModel(territoryViewModel, territoryArea);
    }

    public TerritoryMainViewModelBuilder withTerritoryViewModel(TerritoryViewModel territoryViewModel) {
        this.territoryViewModel = territoryViewModel;
        return this;
    }

    public TerritoryMainViewModelBuilder withTerritoryArea(TerritoryArea territoryArea) {
        this.territoryArea = territoryArea;
        return this;
    }
}
