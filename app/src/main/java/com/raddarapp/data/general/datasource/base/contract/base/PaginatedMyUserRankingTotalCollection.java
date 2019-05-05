package com.raddarapp.data.general.datasource.base.contract.base;

import com.karumi.rosie.repository.PaginatedCollection;

public class PaginatedMyUserRankingTotalCollection<T> {
    private PaginatedCollection<T> paginatedCollection;
    private long position;
    private long totalFootprintsZone;
    private long totalItems;

    public PaginatedMyUserRankingTotalCollection(PaginatedCollection<T> paginatedCollection, long position, long totalFootprintsZone, long totalItems) {
        this.paginatedCollection = paginatedCollection;
        this.position = position;
        this.totalFootprintsZone = totalFootprintsZone;
        this.totalItems = totalItems;
    }

    public PaginatedMyUserRankingTotalCollection(PaginatedCollection<T> paginatedCollection, long position, long totalItems) {
        this.paginatedCollection = paginatedCollection;
        this.position = position;
        this.totalFootprintsZone = -1;
        this.totalItems = totalItems;
    }

    public PaginatedCollection<T> getPaginatedCollection() {
        return paginatedCollection;
    }

    public long getPosition() {
        return position;
    }

    public long getTotalFootprintsZone() {
        return totalFootprintsZone;
    }

    public long getTotalItems() {
        return totalItems;
    }
}