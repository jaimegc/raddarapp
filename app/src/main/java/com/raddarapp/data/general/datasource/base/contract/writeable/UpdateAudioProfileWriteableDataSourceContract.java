package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdateAudioProfile;

public interface UpdateAudioProfileWriteableDataSourceContract extends WriteableDataSource<String, UpdateAudioProfile> {

    UpdateAudioProfile updateAudioProfile(String uriAudio) throws Exception;
}
