package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.domain.model.MyUserRanking;

public interface MyUserRankingReadableDataSourceContract extends PaginatedReadableDataSource<String, MyUserRanking> {

    PaginatedMyUserRankingTotalCollection<MyUserRanking> getMyUsersRankingByTerritoryId(String territoryId, Integer pageNumber, Page page) throws Exception;
}
