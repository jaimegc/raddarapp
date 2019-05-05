package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyUserProfileRepository;
import com.raddarapp.domain.model.MyUserProfile;

import javax.inject.Inject;

public class GetUserProfileByUserId extends RosieUseCase {

    public static final String USE_CASE_GET_USER_PROFILE_BY_USER_ID = "getUserProfileByUserId";

    private MyUserProfileRepository userProfileRepository;

    @Inject
    public GetUserProfileByUserId(MyUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @UseCase(name = USE_CASE_GET_USER_PROFILE_BY_USER_ID)
    public void getUserProfileByUserId(String key) throws Exception {
        MyUserProfile userProfile = userProfileRepository.getByKey(key);
        notifySuccess(userProfile);
    }

}
