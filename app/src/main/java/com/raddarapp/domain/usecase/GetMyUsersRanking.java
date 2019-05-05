package com.raddarapp.domain.usecase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.MyUsersRankingRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.domain.model.MyUserRanking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class GetMyUsersRanking extends RosieUseCase {

    public static final String USE_CASE_GET_MY_USERS_RANKING_BY_ID = "getMyUserRankingsById";

    private static int NUMBER_OF_MY_FOOTPRINTS_AFTER_DELETE_CACHE = 4;

    private final MyUsersRankingRepository myUsersRankingRepository;
    private boolean hasMore = false;

    @Inject
    public GetMyUsersRanking(MyUsersRankingRepository myUsersRankingRepository) {
        this.myUsersRankingRepository = myUsersRankingRepository;
    }

    // Always we use all cache because we have 3 different views. If not,
    // the new cache has leader and king leader views
    public PaginatedCollection<MyUserRanking> getAllMyUsersRankingInCache() {
        Collection<MyUserRanking> all;

        try {
            all = myUsersRankingRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<MyUserRanking> myUsersRanking = new PaginatedCollection<>(all);
        myUsersRanking.setPage(page);
        myUsersRanking.setHasMore(hasMore);

        return myUsersRanking;
    }

    public PaginatedCollection<MyUserRanking> getMaxMyUsersRankingInCache(int maxValue) {
        Collection<MyUserRanking> all;

        try {
            all = myUsersRankingRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        List<MyUserRanking> myFootprintsMaxValues = ImmutableList.copyOf(Iterables.limit(all, maxValue));

        PaginatedCollection<MyUserRanking> myUsersRanking = new PaginatedCollection<>(myFootprintsMaxValues);
        myUsersRanking.setPage(page);
        myUsersRanking.setHasMore(hasMore);

        return myUsersRanking;
    }

    public PaginatedCollection<MyUserRanking> getMyUsersRankingInCache() {
        try {
            return myUsersRankingRepository.getUsersRankingInLocalCache();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteLastItemsFromCache() {
        PaginatedCollection<MyUserRanking> all = getAllMyUsersRankingInCache();

        int counter = 1;

        for (MyUserRanking myFootprint : all.getItems()) {
            if (counter > NUMBER_OF_MY_FOOTPRINTS_AFTER_DELETE_CACHE) {
                deleteCacheByKey(myFootprint.getKey());
            }

            counter++;
        }

    }

    public void addMyUsersRankingInLocalCache(PaginatedCollection<MyUserRanking> myUsersRanking) {
        myUsersRankingRepository.addMyUsersRankingInLocalCache(myUsersRanking);
    }

    public PaginatedCollection<MyUserRanking> getUsersRankingInLocalCache() {
        try {
            return myUsersRankingRepository.getUsersRankingInLocalCache();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteCacheByKey(String footprintKey) {
        try {
            myUsersRankingRepository.deleteByKey(footprintKey);
        } catch (Exception e) {}
    }

    public void deleteCache() {
        try {
            myUsersRankingRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteUsersRankingLocalCache() {
        myUsersRankingRepository.deleteUsersRankingLocalCache();
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_MY_USERS_RANKING_BY_ID)
    public void getMyUsersRankingByTerritoryId(String territoryKey, Integer numberPage, Page page) throws Exception {
        PaginatedMyUserRankingTotalCollection<MyUserRanking> myUsersRanking =
                myUsersRankingRepository.getMyUsersRankingByTerritoryId(territoryKey, numberPage, page);
        notifySuccess(myUsersRanking);
    }
}