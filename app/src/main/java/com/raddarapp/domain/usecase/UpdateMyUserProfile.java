package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateMyUserProfileRepository;
import com.raddarapp.domain.model.MyUserProfile;

import javax.inject.Inject;

public class UpdateMyUserProfile extends RosieUseCase {

    public static final String USE_CASE_UPDATE_MY_USER_PROFILE = "updateMyUserProfile";

    private final UpdateMyUserProfileRepository updateMyUserProfileRepository;

    @Inject
    public UpdateMyUserProfile(UpdateMyUserProfileRepository updateMyUserProfileRepository) {
        this.updateMyUserProfileRepository = updateMyUserProfileRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_MY_USER_PROFILE)
    public void updateMyUserProfile(com.raddarapp.domain.model.UpdateMyUserProfile updateMyUserProfile) throws Exception {
        MyUserProfile myUserProfileUpdated = updateMyUserProfileRepository.updateMyUserProfile(updateMyUserProfile);
        notifySuccess(myUserProfileUpdated);
    }
}
