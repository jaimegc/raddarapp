package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;

public interface FootprintsMainReadableDataSourceContract extends PaginatedReadableDataSource<String, FootprintMain> {

    PaginatedFootprintsMainTotalCollection<FootprintMain> getFootprintsMain(RaddarLocation raddarLocation, Integer pageNumber, Page page) throws Exception;

    PaginatedCollection<FootprintMain> getFootprintsMainByUserId(String userKey, Page page) throws Exception;
}
