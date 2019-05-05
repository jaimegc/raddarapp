package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.FootprintsMainRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class GetFootprintsMain extends RosieUseCase {

    public static final String USE_CASE_GET_FOOTPRINTS_MAIN = "getFootprintsMain";

    private final FootprintsMainRepository footprintsMainRepository;
    private boolean hasMore = false;

    @Inject
    public GetFootprintsMain(FootprintsMainRepository footprintsMainRepository) {
        this.footprintsMainRepository = footprintsMainRepository;
    }

    public PaginatedCollection<FootprintMain> getAllFootprintsMainInCacheOrderByDate() {
        Collection<FootprintMain> all;

        try {
            all = footprintsMainRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<FootprintMain> allOrdered = new ArrayList<>(all);
        Comparator<FootprintMain> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(allOrdered, comparator);

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<FootprintMain> footprintsMain = new PaginatedCollection<>(allOrdered);
        footprintsMain.setPage(page);
        footprintsMain.setHasMore(hasMore);

        return footprintsMain;
    }

    public PaginatedCollection<FootprintMain> getAllFootprintsMainInCache() {
        Collection<FootprintMain> all;

        try {
            all = footprintsMainRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<FootprintMain> footprintsMain = new PaginatedCollection<>(all);
        footprintsMain.setPage(page);
        footprintsMain.setHasMore(hasMore);

        return footprintsMain;
    }


    public void deleteCache() {
        try {
            footprintsMainRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteCacheByKey(String footprintKey) {
        try {
            footprintsMainRepository.deleteByKey(footprintKey);
        } catch (Exception e) {}
    }

    public PaginatedCollection<FootprintMain> addFootprintMainInCache(FootprintMain footprintMain) {
        try {
            PaginatedCollection<FootprintMain> allMyFootprintsInCache = getAllFootprintsMainInCache();
            return footprintsMainRepository.addFootprintsMainInCache(footprintMain, allMyFootprintsInCache);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean footprintMainExists(String footprintMainKey) {
        FootprintMain footprintMain = null;
        try {
            footprintMain = footprintsMainRepository.getByKey(footprintMainKey);
        } catch (Exception e) {}

        return footprintMain != null;
    }

    public PaginatedCollection<FootprintMain> removeFootprintMainInCache(String footprintMainKey) {
        try {
            PaginatedCollection<FootprintMain> allMyFootprintsInCache = getAllFootprintsMainInCache();
            return footprintsMainRepository.removeFootprintsMainInCache(footprintMainKey, allMyFootprintsInCache);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_FOOTPRINTS_MAIN)
    public void getFootprintsMain(RaddarLocation raddarLocation, Integer pageNumber, Page page) throws Exception {
        PaginatedFootprintsMainTotalCollection<FootprintMain> footprintsMain = footprintsMainRepository.getFootprintsMain(raddarLocation, pageNumber, page);
        notifySuccess(footprintsMain);
    }
}