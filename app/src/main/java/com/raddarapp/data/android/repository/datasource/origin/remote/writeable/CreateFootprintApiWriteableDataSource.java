package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CreateFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.CreateFootprintToCreateFootprintDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateFootprintWriteableDataSourceContract;
import com.raddarapp.domain.model.CreateFootprint;

import javax.inject.Inject;

public class CreateFootprintApiWriteableDataSource extends EmptyWriteableDataSource<String, CreateFootprint>
        implements CreateFootprintWriteableDataSourceContract {

    private final CreateFootprintApiClient createFootprintApiClient;
    private final CreateFootprintToCreateFootprintDtoMapper mapper = new CreateFootprintToCreateFootprintDtoMapper();

    @Inject
    public CreateFootprintApiWriteableDataSource(CreateFootprintApiClient createFootprintApiClient) {
        this.createFootprintApiClient = createFootprintApiClient;
    }

    @Override
    public CreateFootprint addOrUpdate(CreateFootprint createFootprint, String uriImage) throws Exception {
        CreateFootprint createdFootprint = null;
        CreateFootprintDto createdFootprintDto = null;

        createdFootprintDto = createFootprintApiClient.createFootprint(mapper.map(createFootprint), uriImage);
        createdFootprint = mapper.reverseMap(createdFootprintDto);

        return createdFootprint;
    }
}