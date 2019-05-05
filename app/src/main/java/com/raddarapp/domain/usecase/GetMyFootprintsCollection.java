package com.raddarapp.domain.usecase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.MyFootprintsCollectionRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprintCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class GetMyFootprintsCollection extends RosieUseCase {

    public static final String USE_CASE_GET_MY_FOOTPRINTS_COLLECTION = "getMyFootprintsCollection";

    private final MyFootprintsCollectionRepository myFootprintsCollectionRepository;
    private boolean hasMore = false;

    @Inject
    public GetMyFootprintsCollection(MyFootprintsCollectionRepository myFootprintsCollectionRepository) {
        this.myFootprintsCollectionRepository = myFootprintsCollectionRepository;
    }

    public PaginatedCollection<MyFootprintCollection> getAllMyFootprintsCollectionInCacheOrderByDate(int maxValue) {
        Collection<MyFootprintCollection> all;

        try {
            all = myFootprintsCollectionRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<MyFootprintCollection> allOrdered = new ArrayList<>(all);
        Comparator<MyFootprintCollection> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(allOrdered, comparator);

        Page page = Page.withOffsetAndLimit(0, all.size());

        List<MyFootprintCollection> myFootprintsCollectionMaxValues = ImmutableList.copyOf(Iterables.limit(allOrdered, maxValue));

        PaginatedCollection<MyFootprintCollection> myFootprintsCollection = new PaginatedCollection<>(myFootprintsCollectionMaxValues);
        myFootprintsCollection.setPage(page);
        myFootprintsCollection.setHasMore(hasMore);

        return myFootprintsCollection;
    }

    public PaginatedCollection<MyFootprintCollection> getAllMyFootprintsCollectionInCache() {
        Collection<MyFootprintCollection> all;

        try {
            all = myFootprintsCollectionRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<MyFootprintCollection> myFootprintsCollection = new PaginatedCollection<>(all);
        myFootprintsCollection.setPage(page);
        myFootprintsCollection.setHasMore(hasMore);

        return myFootprintsCollection;
    }

    public void deleteCache() {
        try {
            myFootprintsCollectionRepository.deleteAll();
        } catch (Exception e) {}
    }

    public void deleteCacheByKey(String footprintCollectionKey) {
        try {
            myFootprintsCollectionRepository.deleteByKey(footprintCollectionKey);
        } catch (Exception e) {}
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_MY_FOOTPRINTS_COLLECTION)
    public void getMyFootprintsCollection(Integer numberPage, Page page) throws Exception {
        PaginatedGenericTotalCollection<MyFootprintCollection> myFootprintsCollection =
                myFootprintsCollectionRepository.getMyFootprintsCollection(numberPage, page);
        notifySuccess(myFootprintsCollection);
    }
}