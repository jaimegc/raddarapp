package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateComplimentRepository;
import com.raddarapp.domain.model.UpdateCompliment;

import javax.inject.Inject;

public class UpdateComplimentProfile extends RosieUseCase {

    public static final String USE_CASE_UPDATE_MY_COMPLIMENTS = "updateMyCompliments";
    public static final String USE_CASE_UPDATE_COMPLIMENTS = "updateCompliments";

    private final UpdateComplimentRepository updateComplimentRepository;

    @Inject
    public UpdateComplimentProfile(UpdateComplimentRepository updateComplimentRepository) {
        this.updateComplimentRepository = updateComplimentRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_MY_COMPLIMENTS)
    public void updateMyCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment = updateComplimentRepository.updateMyCompliments(updateCompliment);
        notifySuccess(isUpdatedCompliment);
    }

    @UseCase(name = USE_CASE_UPDATE_COMPLIMENTS)
    public void updateCompliments(UpdateCompliment updateCompliment) throws Exception {
        boolean isUpdatedCompliment = updateComplimentRepository.updateCompliments(updateCompliment);
        notifySuccess(isUpdatedCompliment);
    }
}
