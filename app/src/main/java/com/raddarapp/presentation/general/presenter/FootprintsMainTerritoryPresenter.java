package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.usecase.GetTerritoryArea;
import com.raddarapp.domain.usecase.GetTerritoryMain;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.TerritoryMainViewModel;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.TerritoryMainToTerritoryMainViewModelMapper;

import javax.inject.Inject;

public class FootprintsMainTerritoryPresenter extends BasePresenterRefreshWithLoading<FootprintsMainTerritoryPresenter.View> {

    private final TerritoryMainToTerritoryMainViewModelMapper mapperTerritoryMain;
    private final GetTerritoryMain getTerritoryMain;
    private final GetTerritoryArea getTerritoryArea;
    private String lastTerritoryKey = null;

    @Inject
    public FootprintsMainTerritoryPresenter(UseCaseHandler useCaseHandler, GetTerritoryMain getTerritoryMain,
            GetTerritoryArea getTerritoryArea, TerritoryMainToTerritoryMainViewModelMapper mapperTerritoryMain) {
        super(useCaseHandler);
        this.getTerritoryMain = getTerritoryMain;
        this.mapperTerritoryMain = mapperTerritoryMain;
        this.getTerritoryArea = getTerritoryArea;
    }

    @Override
    public void update() {
        super.update();
        loadTerritoryFromCache();
    }

    private void loadTerritoryFromCache() {
        TerritoryMain territoryMain = null;

        try {
            getView().showLoadingTerritoryMainDetails();
        } catch (Exception e) {}

        if (lastTerritoryKey != null) {
            territoryMain = getTerritoryMain.getTerritoryInCacheByZoneKey(lastTerritoryKey);
        } else {
            territoryMain = getTerritoryMain.getTerritoryInCache();
        }

        if (territoryMain != null) {
            onShowTerritoryMain(territoryMain);
        }
    }

    public void onShowTerritoryAreaByTerritory(String territoryKey) {

        lastTerritoryKey = territoryKey;

        try {
            getView().showLoadingTerritoryMainArea();
        } catch (Exception e) {}

        createUseCaseCall(getTerritoryArea)
                .args(territoryKey)
                .useCaseName(GetTerritoryArea.USE_CASE_GET_TERRITORY_AREA_BY_TERRITORY_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryAreaByTerritoryId(TerritoryArea territoryArea) {
                        showTerritoryMainArea(territoryArea);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    public void showTerritoryMainArea(TerritoryArea territoryArea) {
        try {
            if (territoryArea.getArea() != null) {
                getView().showTerritoryMainArea(territoryArea);
            }

            getView().hideLoadingTerritoryMainArea();
        } catch (Exception e) {}
    }

    public void onShowTerritoryMain(String latitude, String longitude) {

        try {
            getView().showLoadingTerritoryMainDetails();
        } catch (Exception e) {}

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(Double.valueOf(latitude))
                .withLongitude(Double.valueOf(longitude))
                .build();

        createUseCaseCall(getTerritoryMain)
                .args(raddarLocation)
                .useCaseName(GetTerritoryMain.USE_CASE_GET_TERRITORY_MAIN_BY_COORDINATES)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryMainByCoordinates(TerritoryMain territoryMain) {

                        lastTerritoryKey = territoryMain.getTerritory().getKey();
                        onShowTerritoryMain(territoryMain);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    public void updateTerritory(String latitude, String longitude) {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(Double.valueOf(latitude))
                .withLongitude(Double.valueOf(longitude))
                .build();

        createUseCaseCall(getTerritoryMain)
                .args(raddarLocation)
                .useCaseName(GetTerritoryMain.USE_CASE_GET_TERRITORY_MAIN_BY_COORDINATES)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryMainByCoordinates(TerritoryMain territoryMain) {

                        lastTerritoryKey = territoryMain.getTerritory().getKey();
                        updateTerritory(territoryMain);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    public void updateTerritoryByZone(String zoneKey) {

        lastTerritoryKey = zoneKey;

        try {
            getView().showLoadingTerritoryMainDetails();
        } catch (Exception e) {}

        createUseCaseCall(getTerritoryMain)
                .args(zoneKey)
                .useCaseName(GetTerritoryMain.USE_CASE_GET_TERRITORY_MAIN_BY_ZONE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryMainByZone(TerritoryMain territoryMain) {
                        updateTerritory(territoryMain);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    public void onShowTerritoryMainByZone(String zoneKey) {

        lastTerritoryKey = zoneKey;

        try {
            getView().showLoadingTerritoryMainDetails();
        } catch (Exception e) {}

        createUseCaseCall(getTerritoryMain)
                .args(zoneKey)
                .useCaseName(GetTerritoryMain.USE_CASE_GET_TERRITORY_MAIN_BY_ZONE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoryMainByZone(TerritoryMain territoryMain) {

                        lastTerritoryKey = territoryMain.getTerritory().getKey();

                        onShowTerritoryMain(territoryMain);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    private void onShowTerritoryMain(TerritoryMain territoryMain) {
        try {
            getView().showLoadingTerritoryMainDetails();
            TerritoryMainViewModel territoryMainViewModel = (TerritoryMainViewModel) mapperTerritoryMain.map(territoryMain);
            getView().showTerritoryMainDetails(territoryMainViewModel.getTerritoryViewModel());

            if (territoryMain.getTerritoryArea() != null && territoryMain.getTerritoryArea().getArea() != null &&
                    !territoryMain.getTerritoryArea().getArea().isEmpty()) {
                getView().showLoadingTerritoryMainArea();
                getView().showTerritoryMainArea(territoryMainViewModel.getTerritoryArea());
            } else {
                getView().showWorldTerritoryMainArea();
            }

            getView().hideLoadingTerritoryMainArea();
        } catch (Exception e) {}
    }

    private void updateTerritory(TerritoryMain territoryMain) {
        try {
            TerritoryMainViewModel territoryMainViewModel = (TerritoryMainViewModel) mapperTerritoryMain.map(territoryMain);
            getView().showTerritoryMainDetails(territoryMainViewModel.getTerritoryViewModel());
            getView().showLoadingTerritoryMainArea();

            if (territoryMain.getTerritoryArea() != null && territoryMain.getTerritoryArea().getArea() != null) {
                getView().showTerritoryMainArea(territoryMainViewModel.getTerritoryArea());
            } else {
                getView().showWorldTerritoryMainArea();
            }

            getView().hideLoadingTerritoryMainArea();
        } catch (Exception e) {}
    }

    private void showError() {
        try {
            getView().showErrorLoadTerritoryMain();
            getView().hideLoadingTerritoryMainArea();
        } catch (Exception e) {}
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        // This should be view model but we want to improve performance
        void showTerritoryMainArea(TerritoryArea territoryArea);

        void showTerritoryMainDetails(TerritoryViewModel TerritoryViewModel);

        void showErrorLoadTerritoryMain();

        void hideLoadingTerritoryMainArea();

        void showLoadingTerritoryMainDetails();

        void showLoadingTerritoryMainArea();

        void showWorldTerritoryMainArea();
    }
}
