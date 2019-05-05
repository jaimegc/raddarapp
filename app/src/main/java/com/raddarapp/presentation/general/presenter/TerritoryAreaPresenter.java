package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.usecase.GetTerritoryArea;
import com.raddarapp.domain.usecase.GetTerritoryDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.TerritoryAreaToTerritoryAreaViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.TerritoryToTerritoryDetailsViewModelMapper;

import javax.inject.Inject;

public class TerritoryAreaPresenter extends BasePresenterRefreshWithLoading<TerritoryAreaPresenter.View> {

    private final TerritoryAreaToTerritoryAreaViewModelMapper mapperTerritoryArea;
    private final TerritoryToTerritoryDetailsViewModelMapper mapperTerritoryDetails;
    private final GetTerritoryArea getTerritoryAreaByZone;
    private final GetTerritoryDetails getTerritoryAreaDetails;
    private String zoneKey;

    @Inject
    public TerritoryAreaPresenter(UseCaseHandler useCaseHandler, TerritoryAreaToTerritoryAreaViewModelMapper mapperTerritoryArea,
            TerritoryToTerritoryDetailsViewModelMapper mapperTerritoryDetails,
            GetTerritoryArea getTerritoryAreaByZone, GetTerritoryDetails getTerritoryAreaDetails) {
        super(useCaseHandler);
        this.mapperTerritoryArea = mapperTerritoryArea;
        this.mapperTerritoryDetails = mapperTerritoryDetails;
        this.getTerritoryAreaByZone = getTerritoryAreaByZone;
        this.getTerritoryAreaDetails = getTerritoryAreaDetails;
    }

    @Override public void update() {
        super.update();
        loadTerritoryAreaDetails();
    }

    private void loadTerritoryAreaDetails() {
        createUseCaseCall(getTerritoryAreaDetails)
                .args(zoneKey)
                .useCaseName(GetTerritoryDetails.USE_CASE_GET_TERRITORY_AREA_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryDetails(Territory territory) {
                        showTerritory(territory);
                    }
                })
                .onError(error -> {
                    showErrorTerritoryDetails();
                    return false;
                })
                .execute();
    }

    private void showTerritory(Territory territory) {
        TerritoryViewModel territoryViewModel = (TerritoryViewModel) mapperTerritoryDetails.map(territory);

        try {
            getView().showTerritoryDetails(territoryViewModel);
        } catch (Exception e) {}
    }

    private void showErrorTerritoryDetails() {
        try {
            getView().showErrorLoadTerritoryDetails();
        } catch (Exception e) {}
    }

    public void loadTerritoryArea() {
        getView().showLoading();
        createUseCaseCall(getTerritoryAreaByZone)
                .args(zoneKey)
                .useCaseName(GetTerritoryArea.USE_CASE_GET_TERRITORY_AREA_BY_ZONE_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryAreaByZoneId(TerritoryArea territoryArea) {
                        showTerritoryArea(territoryArea);
                    }
                })
                .onError(error -> {
                    showErrorTerritoryArea();
                    return false;
                })
                .execute();
    }

    private void showTerritoryArea(TerritoryArea territoryArea) {
        try {
            getView().showTerritoryArea(mapperTerritoryArea.map(territoryArea));
        } catch (Exception e) {}
    }

    private void showErrorTerritoryArea() {
        try {
            getView().hideLoading();
            getView().showErrorLoadTerritoryArea();
        } catch (Exception e) {}
    }

    public void setZoneKey(String zoneKey) {
        this.zoneKey = zoneKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        // This should be view model but we want to improve performance
        void showTerritoryArea(TerritoryArea territoryArea);

        void showTerritoryDetails(TerritoryViewModel territoryViewModel);

        void showErrorLoadTerritoryArea();

        void showErrorLoadTerritoryDetails();
    }
}
