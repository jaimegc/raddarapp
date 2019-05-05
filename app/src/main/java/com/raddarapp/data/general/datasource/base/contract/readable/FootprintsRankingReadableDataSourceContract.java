package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.FootprintRanking;

public interface FootprintsRankingReadableDataSourceContract extends PaginatedReadableDataSource<String, FootprintRanking> {

    PaginatedGenericTotalCollection<FootprintRanking> getFootprintsRankingByZoneKey(String zoneKey, Integer pageNumber, Page page) throws Exception;
}
