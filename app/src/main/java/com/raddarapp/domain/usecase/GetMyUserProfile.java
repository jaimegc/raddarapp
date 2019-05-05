package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyUserProfileRepository;
import com.raddarapp.domain.model.MyUserProfile;

import javax.inject.Inject;

public class GetMyUserProfile extends RosieUseCase {

    public static final String USE_CASE_GET_USER_PROFILE_PREFERENCES = "getUserProfilePreferences";

    private MyUserProfileRepository userProfileRepository;

    @Inject
    public GetMyUserProfile(MyUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @UseCase(name = USE_CASE_GET_USER_PROFILE_PREFERENCES)
    public void getUserProfilePreferences() throws Exception {
        MyUserProfile userProfile = userProfileRepository.getUserProfilePreferences();
        notifySuccess(userProfile);
    }
}
