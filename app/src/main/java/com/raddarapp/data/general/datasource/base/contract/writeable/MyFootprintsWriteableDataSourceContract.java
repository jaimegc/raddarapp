package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.MyFootprint;

public interface MyFootprintsWriteableDataSourceContract extends WriteableDataSource<String, MyFootprint> {

    boolean deleteMyFootprint(String footPrintKey) throws Exception;
}
