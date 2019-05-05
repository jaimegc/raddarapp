package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.MyFootprint;

public interface MyFootprintReadableDataSourceContract extends PaginatedReadableDataSource<String, MyFootprint> {

    PaginatedGenericTotalCollection<MyFootprint> getMyFootprints(Integer pageNumber, Page page) throws Exception;

    MyFootprint addMyFootprintInCacheFromNotification(String content) throws Exception;
}
