package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.usecase.GetTerritoryMain;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.TerritoryToTerritoryDetailsViewModelMapper;

import javax.inject.Inject;

public class MyTerritoryMainPresenter extends BasePresenterRefreshWithLoading<MyTerritoryMainPresenter.View> {

    private final TerritoryToTerritoryDetailsViewModelMapper mapperTerritoryDetails;
    private final GetTerritoryMain getTerritoryMain;
    private Double lastLatitude, lastLongitude;

    @Inject
    public MyTerritoryMainPresenter(UseCaseHandler useCaseHandler, TerritoryToTerritoryDetailsViewModelMapper mapperTerritoryDetails,
            GetTerritoryMain getTerritoryMain) {
        super(useCaseHandler);
        this.mapperTerritoryDetails = mapperTerritoryDetails;
        this.getTerritoryMain = getTerritoryMain;
    }

    @Override
    public void initialize() {
        super.initialize();
        showLoading();
    }

    @Override
    public void update() {
        super.update();
        // Only load data from cache. If cache is empty we don't show data
        loadTerritoryMainFromCache();
    }

    private void loadTerritoryMainFromCache() {
        TerritoryMain territoryMainInCache = getTerritoryMain.getTerritoryInCache();

        if (territoryMainInCache != null) {
            showMyTerritory(territoryMainInCache);
        }
    }

    public void loadTerritoryMain(Double latitude, Double longitude) {
        showLoading();

        this.lastLatitude = latitude;
        this.lastLongitude = longitude;
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
                        showMyTerritory(territoryMain);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                })
                .execute();
    }

    private void showError() {
        try {
            getView().showErrorLoadMyTerritoryDetails();
            hideLoading();
        } catch (Exception e) {}
    }

    private void showMyTerritory(TerritoryMain territoryMain) {
        TerritoryViewModel territoryViewModel = (TerritoryViewModel) mapperTerritoryDetails.map(territoryMain.getTerritory());

        try {
            getView().showMyTerritoryDetails(territoryViewModel);
            getView().saveMyTerritoryArea(territoryMain.getTerritoryArea());
        } catch (Exception e) {}
    }

    public void deleteCache() {
        getTerritoryMain.deleteCache();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {

        void showMyTerritoryDetails(TerritoryViewModel territoryViewModel);

        void saveMyTerritoryArea(TerritoryArea territoryArea);

        void showErrorLoadMyTerritoryDetails();
    }
}
