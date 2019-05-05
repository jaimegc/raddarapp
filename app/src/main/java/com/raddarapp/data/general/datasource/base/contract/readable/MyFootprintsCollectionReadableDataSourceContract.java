package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprintCollection;

public interface MyFootprintsCollectionReadableDataSourceContract extends PaginatedReadableDataSource<String, MyFootprintCollection> {

    PaginatedGenericTotalCollection<MyFootprintCollection> getMyFootprintsCollection(Integer pageNumber, Page page) throws Exception;
}
