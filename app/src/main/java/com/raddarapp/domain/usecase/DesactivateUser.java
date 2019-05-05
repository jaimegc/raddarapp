package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UserRepository;

import javax.inject.Inject;

public class DesactivateUser extends RosieUseCase {

    public static final String USE_CASE_DESACTIVATE_USER = "desactivateUser";

    private final UserRepository userRepository;

    @Inject
    public DesactivateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @UseCase(name = USE_CASE_DESACTIVATE_USER)
    public void desactivateUser(String userKey) throws Exception {
        boolean desactivatedUser = userRepository.desactivateUser(userKey);
        notifySuccess(desactivatedUser);
    }
}
