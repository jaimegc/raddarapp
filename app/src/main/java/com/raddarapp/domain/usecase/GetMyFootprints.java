package com.raddarapp.domain.usecase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class GetMyFootprints extends RosieUseCase {

    public static final String USE_CASE_GET_MY_FOOTPRINTS = "getMyFootprints";

    private static final int NUMBER_OF_MY_FOOTPRINTS_AFTER_DELETE_CACHE = 11;

    private final MyFootprintsRepository myFootprintsRepository;
    private boolean hasMore = false;

    @Inject
    public GetMyFootprints(MyFootprintsRepository myFootprintsRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
    }

    public PaginatedCollection<MyFootprint> getAllMyFootprintsInCacheOrderByDate(int maxValue) {
        Collection<MyFootprint> all;

        try {
            all = myFootprintsRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<MyFootprint> allOrdered = new ArrayList<>(all);
        Comparator<MyFootprint> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(allOrdered, comparator);

        Page page = Page.withOffsetAndLimit(0, all.size());

        List<MyFootprint> myFootprintsMaxValues = ImmutableList.copyOf(Iterables.limit(allOrdered, maxValue));

        PaginatedCollection<MyFootprint> myFootprints = new PaginatedCollection<>(myFootprintsMaxValues);
        myFootprints.setPage(page);
        myFootprints.setHasMore(hasMore);

        return myFootprints;
    }

    public PaginatedCollection<MyFootprint> getAllMyFootprintsInCache() {
        Collection<MyFootprint> all;

        try {
            all = myFootprintsRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<MyFootprint> myFootprints = new PaginatedCollection<>(all);
        myFootprints.setPage(page);
        myFootprints.setHasMore(hasMore);

        return myFootprints;
    }

    public void deleteCache() {
        try {
            myFootprintsRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteCacheByKey(String footprintKey) {
        try {
            myFootprintsRepository.deleteByKey(footprintKey);
        } catch (Exception e) {}
    }

    public void deleteInLocalCacheByKey(String footprintKey) {
        try {
            myFootprintsRepository.deleteInLocalCacheByKey(footprintKey);
        } catch (Exception e) {}
    }

    public PaginatedCollection<MyFootprint> addMyFootprintInCache(MyFootprint myFootprint) {
        try {
            PaginatedCollection<MyFootprint> allMyFootprintsInCache = getAllMyFootprintsInCache();
            return myFootprintsRepository.addMyFootprintInCache(myFootprint, allMyFootprintsInCache);
        } catch (Exception e) {
            return null;
        }
    }

    public void addMyFootprintsInLocalCache(PaginatedCollection<MyFootprint> myFootprints) {
        myFootprintsRepository.addMyFootprintsInLocalCache(myFootprints);
    }

    public PaginatedCollection<MyFootprint> getMyFootprintsInLocalCache() {
        try {
            return myFootprintsRepository.getMyFootprintsInLocalCache();
        } catch (Exception e) {
            return null;
        }
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_MY_FOOTPRINTS)
    public void getMyFootprints(Integer numberPage, Page page) throws Exception {
        PaginatedGenericTotalCollection<MyFootprint> myFootprints = myFootprintsRepository.getMyFootprints(numberPage, page);
        notifySuccess(myFootprints);
    }
}