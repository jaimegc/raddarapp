package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UserRepository;

import javax.inject.Inject;

public class UnfollowUser extends RosieUseCase {

    public static final String USE_CASE_UNFOLLOW_USER = "unfollowUser";

    private final UserRepository userRepository;

    @Inject
    public UnfollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @UseCase(name = USE_CASE_UNFOLLOW_USER)
    public void unfollowUser(String userKey) throws Exception {
        int userRelationship = userRepository.unfollowUser(userKey);
        notifySuccess(userRelationship);
    }
}
