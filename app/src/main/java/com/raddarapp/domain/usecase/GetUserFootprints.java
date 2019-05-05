package com.raddarapp.domain.usecase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.UserFootprintsRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.UserFootprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class GetUserFootprints extends RosieUseCase {

    public static final String USE_CASE_GET_USER_FOOTPRINTS = "getUserFootprintsByUserId";

    private final UserFootprintsRepository userFootprintsRepository;
    private boolean hasMore = false;

    @Inject
    public GetUserFootprints(UserFootprintsRepository userFootprintsRepository) {
        this.userFootprintsRepository = userFootprintsRepository;
    }

    public PaginatedCollection<UserFootprint> getAllUserFootprintsInCache() {
        Collection<UserFootprint> all;

        try {
            all = userFootprintsRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<UserFootprint> userFootprints = new PaginatedCollection<>(all);
        userFootprints.setPage(page);
        userFootprints.setHasMore(hasMore);

        return userFootprints;
    }

    public PaginatedCollection<UserFootprint> getAllUserFootprintsInCacheOrderByDate(int maxValue) {
        Collection<UserFootprint> all;

        try {
            all = userFootprintsRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<UserFootprint> allOrdered = new ArrayList<>(all);
        Comparator<UserFootprint> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(allOrdered, comparator);

        Page page = Page.withOffsetAndLimit(0, all.size());

        List<UserFootprint> userFootprintsMaxValues = ImmutableList.copyOf(Iterables.limit(allOrdered, maxValue));

        PaginatedCollection<UserFootprint> userFootprints = new PaginatedCollection<>(userFootprintsMaxValues);
        userFootprints.setPage(page);
        userFootprints.setHasMore(hasMore);

        return userFootprints;
    }

    public PaginatedCollection<UserFootprint> getUserFootprintsInLocalCache() {
        try {
            return userFootprintsRepository.getUserFootprintsInLocalCache();
        } catch (Exception e) {
            return null;
        }
    }

    public void addUserFootprintsInLocalCache(PaginatedCollection<UserFootprint> userFootprints) {
        userFootprintsRepository.addUserFootprintsInLocalCache(userFootprints);
    }

    public void deleteCache() {
        try {
            userFootprintsRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteMyFootprintsLocalCache() {
        userFootprintsRepository.deleteUserFootprintsLocalCache();
    }

    public void deleteCacheByKey(String footprintKey) {
        try {
            userFootprintsRepository.deleteByKey(footprintKey);
        } catch (Exception e) {}
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_USER_FOOTPRINTS)
    public void getUserFootprints(String userKey, Integer numberPage, Page page) throws Exception {
        PaginatedGenericTotalCollection<UserFootprint> userFootprints = userFootprintsRepository.getUserFootprints(userKey, numberPage, page);
        notifySuccess(userFootprints);
    }
}