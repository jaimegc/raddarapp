package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintCollectionApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsCollectionWriteableDataSourceContract;
import com.raddarapp.domain.model.MyFootprintCollection;

import javax.inject.Inject;

public class MyFootprintCollectionApiWriteableDataSource extends EmptyWriteableDataSource<String, MyFootprintCollection>
        implements MyFootprintsCollectionWriteableDataSourceContract {

    private final MyFootprintCollectionApiClient myFootprintCollectionApiClient;

    @Inject
    public MyFootprintCollectionApiWriteableDataSource(MyFootprintCollectionApiClient myFootprintCollectionApiClient) {
        this.myFootprintCollectionApiClient = myFootprintCollectionApiClient;
    }

    @Override
    public boolean deleteMyFootprintCollection(String footprintKey) throws Exception {
        ResponseDto deletedFootprintDto = myFootprintCollectionApiClient.deleteMyFootprintCollection(footprintKey);

        return deletedFootprintDto == null || !deletedFootprintDto.getResponse().isEmpty();
    }
}