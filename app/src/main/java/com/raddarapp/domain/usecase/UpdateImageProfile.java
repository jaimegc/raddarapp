package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateImageProfileRepository;

import javax.inject.Inject;

public class UpdateImageProfile extends RosieUseCase {

    public static final String USE_CASE_UPDATE_IMAGE_PROFILE = "updateImageProfile";

    private final UpdateImageProfileRepository updateImageProfileRepository;

    @Inject
    public UpdateImageProfile(UpdateImageProfileRepository updateImageProfileRepository) {
        this.updateImageProfileRepository = updateImageProfileRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_IMAGE_PROFILE)
    public void updateImageProfile(String imageUri) throws Exception {
        com.raddarapp.domain.model.UpdateImageProfile updatedImageProfile = updateImageProfileRepository.updateImageProfile(imageUri);
        notifySuccess(updatedImageProfile);
    }
}
