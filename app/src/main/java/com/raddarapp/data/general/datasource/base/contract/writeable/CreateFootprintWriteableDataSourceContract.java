package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.CreateFootprint;

public interface CreateFootprintWriteableDataSourceContract extends WriteableDataSource<String, CreateFootprint> {

    CreateFootprint addOrUpdate(CreateFootprint createFootprint, String uriImage) throws Exception;
}
