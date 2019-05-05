package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.UserFootprint;

public interface UserFootprintReadableDataSourceContract extends PaginatedReadableDataSource<String, UserFootprint> {

    PaginatedGenericTotalCollection<UserFootprint> getUserFootprintsByUserId(String userKey, Integer pageNumber, Page page) throws Exception;
}
