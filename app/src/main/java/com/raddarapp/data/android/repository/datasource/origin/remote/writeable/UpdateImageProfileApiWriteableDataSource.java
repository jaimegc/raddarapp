package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateImageProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdateImageProfileToUpdateImageProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateImageProfileDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateImageProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateImageProfile;

import javax.inject.Inject;

public class UpdateImageProfileApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateImageProfile>
        implements UpdateImageProfileWriteableDataSourceContract {

    private final UpdateImageProfileApiClient updateImageProfileApiClient;
    private final UpdateImageProfileToUpdateImageProfileDtoMapper mapper = new UpdateImageProfileToUpdateImageProfileDtoMapper();

    @Inject
    public UpdateImageProfileApiWriteableDataSource(UpdateImageProfileApiClient updateImageProfileApiClient) {
        this.updateImageProfileApiClient = updateImageProfileApiClient;
    }

    @Override
    public UpdateImageProfile updateImageProfile(String uriImage) throws Exception {
        UpdateImageProfile updatedImageProfile;
        UpdateImageProfileDto updateImageProfileDto;

        updateImageProfileDto = updateImageProfileApiClient.updateImageProfile(uriImage);
        updatedImageProfile = mapper.reverseMap(updateImageProfileDto);

        return updatedImageProfile;
    }
}