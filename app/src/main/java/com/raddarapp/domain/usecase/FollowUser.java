package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UserRepository;

import javax.inject.Inject;

public class FollowUser extends RosieUseCase {

    public static final String USE_CASE_FOLLOW_USER = "followUser";

    private final UserRepository userRepository;

    @Inject
    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @UseCase(name = USE_CASE_FOLLOW_USER)
    public void followUser(String userKey) throws Exception {
        int userRelationship = userRepository.followUser(userKey);
        notifySuccess(userRelationship);
    }
}
