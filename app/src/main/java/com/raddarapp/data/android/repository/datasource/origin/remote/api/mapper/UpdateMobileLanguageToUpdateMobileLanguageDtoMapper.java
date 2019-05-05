package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMobileLanguageDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.UpdateMobileLanguageDtoBuilder;
import com.raddarapp.domain.model.UpdateMobileLanguage;

public class UpdateMobileLanguageToUpdateMobileLanguageDtoMapper extends Mapper<UpdateMobileLanguage, UpdateMobileLanguageDto> {

    @Override
    public UpdateMobileLanguageDto map(UpdateMobileLanguage updateMobileLanguage) {
        final UpdateMobileLanguageDto updateMobileLanguageDto = new UpdateMobileLanguageDtoBuilder()
                .withMobileLanguage(updateMobileLanguage.getMobileLanguage())
                .build();

        return updateMobileLanguageDto;
    }

    @Override
    public UpdateMobileLanguage reverseMap(UpdateMobileLanguageDto updateMobileLanguageDto) {
        throw new UnsupportedOperationException();
    }
}
