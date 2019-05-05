package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsWriteableDataSourceContract;
import com.raddarapp.domain.model.MyFootprint;

import javax.inject.Inject;

public class MyFootprintApiWriteableDataSource extends EmptyWriteableDataSource<String, MyFootprint>
        implements MyFootprintsWriteableDataSourceContract {

    private final MyFootprintApiClient myFootprintApiClient;

    @Inject
    public MyFootprintApiWriteableDataSource(MyFootprintApiClient myFootprintApiClient) {
        this.myFootprintApiClient = myFootprintApiClient;
    }

    @Override
    public boolean deleteMyFootprint(String footprintKey) throws Exception {
        ResponseDto deletedFootprintDto = myFootprintApiClient.deleteMyFootprint(footprintKey);

        return deletedFootprintDto == null || !deletedFootprintDto.getResponse().isEmpty();
    }
}