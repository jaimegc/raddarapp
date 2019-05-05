package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.MyFootprintCollection;

public interface MyFootprintsCollectionWriteableDataSourceContract extends WriteableDataSource<String, MyFootprintCollection> {

    boolean deleteMyFootprintCollection(String footPrintCollectionKey) throws Exception;
}
