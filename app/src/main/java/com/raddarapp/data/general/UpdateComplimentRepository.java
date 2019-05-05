package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateComplimentWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateComplimentDataSourceFactory;
import com.raddarapp.domain.model.UpdateCompliment;

import javax.inject.Inject;

public class UpdateComplimentRepository extends RosieRepository<String, UpdateCompliment> {

    private final UpdateComplimentWriteableDataSourceContract updateComplimentWriteableDataSource;

    @Inject
    UpdateComplimentRepository(UpdateComplimentDataSourceFactory updateComplimentDataSourceFactory) {

        updateComplimentWriteableDataSource = updateComplimentDataSourceFactory.createWriteableDataSource();

        addWriteableDataSources(updateComplimentWriteableDataSource);
    }

    public boolean updateMyCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment;

        isUpdatedCompliment = updateComplimentWriteableDataSource.updateMyCompliments(updateCompliment);

        return isUpdatedCompliment;
    }

    public boolean updateCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment;

        isUpdatedCompliment = updateComplimentWriteableDataSource.updateCompliments(updateCompliment);

        return isUpdatedCompliment;
    }
}
