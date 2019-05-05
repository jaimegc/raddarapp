package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.FootprintMain;

public interface FootprintsMainWriteableDataSourceContract extends WriteableDataSource<String, FootprintMain> {

    double addVote(String footprintKey, int addVoteType) throws Exception;
}
