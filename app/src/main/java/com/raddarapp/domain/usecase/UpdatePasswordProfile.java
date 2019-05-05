package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdatePasswordProfileRepository;

import javax.inject.Inject;

public class UpdatePasswordProfile extends RosieUseCase {

    public static final String USE_CASE_UPDATE_PASSWORD_PROFILE = "updatePasswordProfile";

    private final UpdatePasswordProfileRepository updatePasswordProfileRepository;

    @Inject
    public UpdatePasswordProfile(UpdatePasswordProfileRepository updatePasswordProfileRepository) {
        this.updatePasswordProfileRepository = updatePasswordProfileRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_PASSWORD_PROFILE)
    public void updatePasswordProfile(com.raddarapp.domain.model.UpdatePasswordProfile updatePasswordProfile) throws Exception {
        boolean updatedPasswordProfile = updatePasswordProfileRepository.updatePasswordProfile(updatePasswordProfile);
        notifySuccess(updatedPasswordProfile);
    }
}
