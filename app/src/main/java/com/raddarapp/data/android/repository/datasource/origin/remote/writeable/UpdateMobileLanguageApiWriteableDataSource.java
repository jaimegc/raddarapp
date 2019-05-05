package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateMobileLanguageApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdateMobileLanguageToUpdateMobileLanguageDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMobileLanguageWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateMobileLanguage;

import javax.inject.Inject;

public class UpdateMobileLanguageApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateMobileLanguage>
        implements UpdateMobileLanguageWriteableDataSourceContract {

    private final UpdateMobileLanguageApiClient updateMobileLanguageApiClient;
    private final UpdateMobileLanguageToUpdateMobileLanguageDtoMapper mapper = new UpdateMobileLanguageToUpdateMobileLanguageDtoMapper();

    @Inject
    public UpdateMobileLanguageApiWriteableDataSource(UpdateMobileLanguageApiClient updateMobileLanguageApiClient) {
        this.updateMobileLanguageApiClient = updateMobileLanguageApiClient;
    }

    @Override
    public boolean updateMobileLanguage(String userKey, UpdateMobileLanguage updateMobileLanguage) throws Exception {
        boolean udpatedLanguage;
        ResponseDto responseDto = null;

        responseDto = updateMobileLanguageApiClient.updateMobileLanguage(userKey, mapper.map(updateMobileLanguage));

        udpatedLanguage = responseDto == null || !responseDto.getResponse().isEmpty();

        return udpatedLanguage;
    }
}