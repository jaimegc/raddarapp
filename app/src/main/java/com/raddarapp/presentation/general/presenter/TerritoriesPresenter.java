package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.usecase.GetTerritoriesByZone;
import com.raddarapp.domain.usecase.GetTerritoriesFirstLevel;
import com.raddarapp.presentation.general.presenter.base.BasePresenterSwipeRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.TerritoriesToTerritoriesViewModelMapper;

import java.util.List;

import javax.inject.Inject;

public class TerritoriesPresenter extends BasePresenterSwipeRefreshWithLoading<TerritoriesPresenter.View> {

    private static final int NUMBER_OF_TERRITORIES_PER_PAGE = 20;
    private final TerritoriesToTerritoriesViewModelMapper mapper;
    private final GetTerritoriesFirstLevel getTerritoriesFirstLevel;
    private final GetTerritoriesByZone getTerritoriesByZone;
    private Integer offset = 0, pageNumber = 0;

    @Inject
    public TerritoriesPresenter(UseCaseHandler useCaseHandler, TerritoriesToTerritoriesViewModelMapper mapper,
        GetTerritoriesFirstLevel getTerritoriesFirstLevel, GetTerritoriesByZone getTerritoriesByZone) {
        super(useCaseHandler);
        this.mapper = mapper;
        this.getTerritoriesFirstLevel = getTerritoriesFirstLevel;
        this.getTerritoriesByZone = getTerritoriesByZone;
    }

    @Override
    public void update() {
        super.update();
        loadDataFirstLevel();
    }

    public void loadDataFirstLevel() {
        getView().hideTerritories();
        showLoading();

        PaginatedCollection<Territory> allTerritoriesFirstLevel = getTerritoriesFirstLevel.getAllTerritoriesFirstLevelInCache();

        if (allTerritoriesFirstLevel.getPage().getLimit() == 0) {
            loadTerritoriesFirstLevel();
        } else {
            getView().clearTerritories();
            showTerritories(allTerritoriesFirstLevel);
            offset = allTerritoriesFirstLevel.getItems().size();
        }
    }

    public void loadDataByZone(String zoneKey) {
        getView().hideTerritories();
        showLoading();

        PaginatedCollection<Territory> allTerritoriesByZone = getTerritoriesByZone.getAllTerritoriesByZoneInCache();

        if (allTerritoriesByZone.getPage().getLimit() == 0) {
            loadTerritoriesByZone(zoneKey);
        } else {
            getView().clearTerritories();
            showTerritories(allTerritoriesByZone);
            offset = allTerritoriesByZone.getItems().size();
        }
    }

    private void loadTerritoriesFirstLevel() {
        createUseCaseCall(getTerritoriesFirstLevel)
                .args(Page.withOffsetAndLimit(44, NUMBER_OF_TERRITORIES_PER_PAGE))
                .useCaseName(GetTerritoriesFirstLevel.USE_CASE_GET_TERRITORIES_FIRST_LEVEL)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoriesFirstLevel(PaginatedCollection<Territory> territories) {
                        offset = territories.getPage().getOffset() + NUMBER_OF_TERRITORIES_PER_PAGE;
                        pageNumber++;
                        showTerritories(territories);
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showError() {
        try {
            hideLoading();
        } catch (Exception e) {}
    }

    private void loadTerritoriesByZone(String zoneKey) {
        createUseCaseCall(getTerritoriesByZone)
                .args(zoneKey, Page.withOffsetAndLimit(pageNumber, NUMBER_OF_TERRITORIES_PER_PAGE))
                .useCaseName(GetTerritoriesByZone.USE_CASE_GET_TERRITORIES_BY_ZONE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getTerritoriesByZoneId(PaginatedCollection<Territory> territories) {
                        getTerritoriesByZone.setHasMore(territories.hasMore());
                        offset = territories.getPage().getOffset() + NUMBER_OF_TERRITORIES_PER_PAGE;
                        pageNumber++;
                        showTerritories(territories);
                    }
                })
                .onError(error -> {
                    hideLoading();

                    return false;
                }).execute();
    }

    public void onTerritoryAreaClicked(TerritoryViewModel territoryViewModel) {
        String zoneKey = territoryViewModel.getKey();
        getView().openTerritoryArea(zoneKey);
    }

    @Override
    public void forceRefreshing() {
        // FIXME: Comprobar si estoy en activity first level o la siguiente para llamar a uno o a otro
        showRefreshing();
        getTerritoriesFirstLevel.deleteCache();
        offset = 0;
        pageNumber = 0;
        loadTerritoriesFirstLevel();
    }

    private void showTerritories(PaginatedCollection<Territory> territories) {
        try {
            if (isForceRefreshing) {
                getView().clearTerritories();
                hideRefreshing();
            }

            List<TerritoryViewModelContract> territoriesViewModel = mapper.map(territories);
            getView().showTerritories(territoriesViewModel);
            getView().showHasMore(territories.hasMore());
            hideLoading();
        } catch (Exception e) {}
    }

    public void onLoadMore() {
        // FIXME: Provisional method
        loadTerritoriesFirstLevel();
    }

    public interface View extends BasePresenterSwipeRefreshWithLoading.View {
        void hideTerritories();

        void showTerritories(List<TerritoryViewModelContract> territories);

        void showHasMore(boolean hasMore);

        void openTerritoryArea(String zoneKey);

        void openRanking(String zoneKey);

        void openNextLevel(String zoneKey);

        void clearTerritories();
    }
}
