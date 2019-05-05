package com.raddarapp.domain.usecase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.FootprintsRankingRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.FootprintRanking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class GetFootprintsRanking extends RosieUseCase {

    public static final String USE_CASE_GET_FOOTPRINTS_RANKING = "getFootprintsRanking";

    private final FootprintsRankingRepository footprintsRankingRepository;
    private boolean hasMore = false;

    @Inject
    public GetFootprintsRanking(FootprintsRankingRepository footprintsRankingRepository) {
        this.footprintsRankingRepository = footprintsRankingRepository;
    }

    public PaginatedCollection<FootprintRanking> getAllFootprintsRankingInCacheOrderByDate(int maxValue) {
        Collection<FootprintRanking> all;

        try {
            all = footprintsRankingRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<FootprintRanking> allOrdered = new ArrayList<>(all);
        Comparator<FootprintRanking> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(allOrdered, comparator);

        Page page = Page.withOffsetAndLimit(0, all.size());

        List<FootprintRanking> footprintsRankingMaxValues = ImmutableList.copyOf(Iterables.limit(allOrdered, maxValue));

        PaginatedCollection<FootprintRanking> footprintsRanking = new PaginatedCollection<>(footprintsRankingMaxValues);
        footprintsRanking.setPage(page);
        footprintsRanking.setHasMore(hasMore);

        return footprintsRanking;
    }

    public PaginatedCollection<FootprintRanking> getAllFootprintsRankingInCache() {
        Collection<FootprintRanking> all;

        try {
            all = footprintsRankingRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<FootprintRanking> footprintsRanking = new PaginatedCollection<>(all);
        footprintsRanking.setPage(page);
        footprintsRanking.setHasMore(hasMore);

        return footprintsRanking;
    }

    public void deleteCache() {
        try {
            footprintsRankingRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteCacheByKey(String footprintCollectionKey) {
        try {
            footprintsRankingRepository.deleteByKey(footprintCollectionKey);
        } catch (Exception e) {}
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_FOOTPRINTS_RANKING)
    public void getFootprintsRankingByZoneKey(String zoneKey, Integer numberPage, Page page) throws Exception {
        PaginatedGenericTotalCollection<FootprintRanking> footprintsRanking =
                footprintsRankingRepository.getFootprintsRankingByZoneKey(zoneKey, numberPage, page);
        notifySuccess(footprintsRanking);
    }
}