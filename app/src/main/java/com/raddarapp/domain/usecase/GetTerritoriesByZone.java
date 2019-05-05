package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.TerritoryRepository;
import com.raddarapp.domain.model.Territory;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

public class GetTerritoriesByZone extends RosieUseCase {

    public static final String USE_CASE_GET_TERRITORIES_BY_ZONE = "getTerritoriesByZone";

    private final TerritoryRepository territoryRepository;
    private boolean hasMore = false;

    @Inject
    public GetTerritoriesByZone(TerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }

    public PaginatedCollection<Territory> getAllTerritoriesByZoneInCache() {
        Collection<Territory> all;

        try {
            all = territoryRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<Territory> territories = new PaginatedCollection<>(all);
        territories.setPage(page);
        territories.setHasMore(hasMore);

        return territories;
    }

    public void deleteCache() {
        try {
            territoryRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_TERRITORIES_BY_ZONE)
    public void getTerritoriesByZoneId(String zoneKey, Page page) throws Exception {
        PaginatedCollection<Territory> territories = territoryRepository.getTerritoriesByZoneId(zoneKey, page);
        notifySuccess(territories);
    }
}