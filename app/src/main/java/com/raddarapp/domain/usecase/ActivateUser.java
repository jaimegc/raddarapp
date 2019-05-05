package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UserRepository;

import javax.inject.Inject;

public class ActivateUser extends RosieUseCase {

    public static final String USE_CASE_ACTIVATE_USER = "activateUser";

    private final UserRepository userRepository;

    @Inject
    public ActivateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @UseCase(name = USE_CASE_ACTIVATE_USER)
    public void activateUser(String userKey) throws Exception {
        boolean activatedUser = userRepository.activateUser(userKey);
        notifySuccess(activatedUser);
    }
}
