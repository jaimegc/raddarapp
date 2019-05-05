package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateMobileLanguageRepository;

import javax.inject.Inject;

public class UpdateMobileLanguage extends RosieUseCase {

    public static final String USE_CASE_UPDATE_MOBILE_LANGUAGE = "updateMobileLanguage";

    private final UpdateMobileLanguageRepository updateMobileLanguageRepository;

    @Inject
    public UpdateMobileLanguage(UpdateMobileLanguageRepository updateMobileLanguageRepository) {
        this.updateMobileLanguageRepository = updateMobileLanguageRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_MOBILE_LANGUAGE)
    public void updateMobileLanguage(String userKey, com.raddarapp.domain.model.UpdateMobileLanguage updateMobileLanguage) throws Exception {
        boolean updatedMobileLanguage = updateMobileLanguageRepository.updateMobileLanguage(userKey, updateMobileLanguage);
        notifySuccess(updateMobileLanguage.getMobileLanguage());
    }
}
