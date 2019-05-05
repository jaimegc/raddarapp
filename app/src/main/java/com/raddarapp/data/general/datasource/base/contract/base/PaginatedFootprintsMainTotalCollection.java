package com.raddarapp.data.general.datasource.base.contract.base;

import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.MyUserProfile;

public class PaginatedFootprintsMainTotalCollection<T> {
    private PaginatedCollection<T> paginatedCollection;
    private MyUserProfile myUserProfile;
    private long totalItems;

    public PaginatedFootprintsMainTotalCollection(PaginatedCollection<T> paginatedCollection, MyUserProfile myUserProfile, long totalItems) {
        this.paginatedCollection = paginatedCollection;
        this.myUserProfile = myUserProfile;
        this.totalItems = totalItems;
    }

    public PaginatedCollection<T> getPaginatedCollection() {
        return paginatedCollection;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public MyUserProfile getMyUserProfile() {
        return myUserProfile;
    }
}