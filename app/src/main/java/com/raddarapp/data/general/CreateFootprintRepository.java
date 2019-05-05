package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateFootprintWriteableDataSourceContract;
import com.raddarapp.data.general.factory.CreateFootprintDataSourceFactory;
import com.raddarapp.domain.model.CreateFootprint;

import javax.inject.Inject;

public class CreateFootprintRepository extends RosieRepository<String, CreateFootprint> {

    private final CreateFootprintWriteableDataSourceContract createFootprintWriteableDataSource;

    @Inject
    CreateFootprintRepository(CreateFootprintDataSourceFactory createFootprintDataSourceFactory) {

        createFootprintWriteableDataSource = createFootprintDataSourceFactory.createWriteableDataSource();

        addWriteableDataSources(createFootprintWriteableDataSource);
    }

    public CreateFootprint createFootprint(CreateFootprint createFootprint, String uriImage) throws Exception {
        CreateFootprint createdFootprint;

        createdFootprint = createFootprintWriteableDataSource.addOrUpdate(createFootprint, uriImage);

        return createdFootprint;
    }
}
