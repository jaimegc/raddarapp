package com.raddarapp.data.general.datasource.base.contract.base;

import com.karumi.rosie.repository.PaginatedCollection;

public class PaginatedGenericTotalCollection<T> {
    private PaginatedCollection<T> paginatedCollection;
    private long totalItems;

    public PaginatedGenericTotalCollection(PaginatedCollection<T> paginatedCollection, long totalItems) {
        this.paginatedCollection = paginatedCollection;
        this.totalItems = totalItems;
    }

    public PaginatedCollection<T> getPaginatedCollection() {
        return paginatedCollection;
    }

    public long getTotalItems() {
        return totalItems;
    }
}