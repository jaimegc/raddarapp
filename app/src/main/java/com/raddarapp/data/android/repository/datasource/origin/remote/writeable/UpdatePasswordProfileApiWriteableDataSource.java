package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdatePasswordProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdatePasswordProfileToUpdatePasswordProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdatePasswordProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdatePasswordProfile;

import javax.inject.Inject;

public class UpdatePasswordProfileApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdatePasswordProfile>
        implements UpdatePasswordProfileWriteableDataSourceContract {

    private final UpdatePasswordProfileApiClient updatePasswordProfileApiClient;
    private final UpdatePasswordProfileToUpdatePasswordProfileDtoMapper mapper = new UpdatePasswordProfileToUpdatePasswordProfileDtoMapper();

    @Inject
    public UpdatePasswordProfileApiWriteableDataSource(UpdatePasswordProfileApiClient updatePasswordProfileApiClient) {
        this.updatePasswordProfileApiClient = updatePasswordProfileApiClient;
    }

    @Override
    public boolean updatePasswordProfile(UpdatePasswordProfile updatePasswordProfile) throws Exception {
        boolean updatedPasswordProfile;
        ResponseDto responseDto = null;

        responseDto = updatePasswordProfileApiClient.updatePasswordProfile(mapper.map(updatePasswordProfile));

        updatedPasswordProfile = responseDto == null || !responseDto.getResponse().isEmpty();

        return updatedPasswordProfile;
    }
}