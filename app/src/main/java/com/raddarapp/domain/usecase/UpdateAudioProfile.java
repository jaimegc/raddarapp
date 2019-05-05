package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateAudioProfileRepository;

import javax.inject.Inject;

public class UpdateAudioProfile extends RosieUseCase {

    public static final String USE_CASE_UPDATE_AUDIO_PROFILE = "updateAudioProfile";

    private final UpdateAudioProfileRepository updateAudioProfileRepository;

    @Inject
    public UpdateAudioProfile(UpdateAudioProfileRepository updateAudioProfileRepository) {
        this.updateAudioProfileRepository = updateAudioProfileRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_AUDIO_PROFILE)
    public void updateAudioProfile(String imageUri) throws Exception {
        com.raddarapp.domain.model.UpdateAudioProfile updatedAudioProfile = updateAudioProfileRepository.updateAudioProfile(imageUri);
        notifySuccess(updatedAudioProfile);
    }
}
