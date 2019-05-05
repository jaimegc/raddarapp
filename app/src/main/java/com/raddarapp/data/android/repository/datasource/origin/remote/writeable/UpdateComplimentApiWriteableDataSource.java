package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateComplimentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.BooleanToResponseDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdateComplimentToUpdateComplimentDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateComplimentWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateCompliment;

import javax.inject.Inject;

public class UpdateComplimentApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateCompliment>
        implements UpdateComplimentWriteableDataSourceContract {

    private final UpdateComplimentApiClient updateComplimentApiClient;
    private final UpdateComplimentToUpdateComplimentDtoMapper mapperUpdateCompliment = new UpdateComplimentToUpdateComplimentDtoMapper();
    private final BooleanToResponseDtoMapper mapperBoolean = new BooleanToResponseDtoMapper();

    @Inject
    public UpdateComplimentApiWriteableDataSource(UpdateComplimentApiClient updateComplimentApiClient) {
        this.updateComplimentApiClient = updateComplimentApiClient;
    }

    @Override
    public boolean updateMyCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment;
        ResponseDto responseDto;

        responseDto = updateComplimentApiClient.updateMyCompliments(mapperUpdateCompliment.map(updateCompliment));
        isUpdatedCompliment = mapperBoolean.reverseMap(responseDto);

        return isUpdatedCompliment;
    }

    @Override
    public boolean updateCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment;
        ResponseDto responseDto;

        responseDto = updateComplimentApiClient.updateCompliments(mapperUpdateCompliment.map(updateCompliment));
        isUpdatedCompliment = mapperBoolean.reverseMap(responseDto);

        return isUpdatedCompliment;
    }
}