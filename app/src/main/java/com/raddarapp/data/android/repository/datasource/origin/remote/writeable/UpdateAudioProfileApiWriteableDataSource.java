package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateAudioProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdateAudioProfileToUpdateAudioProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateAudioProfileDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateAudioProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateAudioProfile;

import javax.inject.Inject;

public class UpdateAudioProfileApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateAudioProfile>
        implements UpdateAudioProfileWriteableDataSourceContract {

    private final UpdateAudioProfileApiClient updateAudioProfileApiClient;
    private final UpdateAudioProfileToUpdateAudioProfileDtoMapper mapper = new UpdateAudioProfileToUpdateAudioProfileDtoMapper();

    @Inject
    public UpdateAudioProfileApiWriteableDataSource(UpdateAudioProfileApiClient updateAudioProfileApiClient) {
        this.updateAudioProfileApiClient = updateAudioProfileApiClient;
    }

    @Override
    public UpdateAudioProfile updateAudioProfile(String uriAudio) throws Exception {
        UpdateAudioProfile updatedAudioProfile = null;
        UpdateAudioProfileDto updateAudioProfileDto = null;

        updateAudioProfileDto = updateAudioProfileApiClient.updateAudioProfile(uriAudio);
        updatedAudioProfile = mapper.reverseMap(updateAudioProfileDto);

        return updatedAudioProfile;
    }
}