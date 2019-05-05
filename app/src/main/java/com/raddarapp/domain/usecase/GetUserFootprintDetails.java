package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UserFootprintsRepository;
import com.raddarapp.domain.model.UserFootprint;

import javax.inject.Inject;

public class GetUserFootprintDetails extends RosieUseCase {

    public static final String USE_CASE_GET_USER_FOOTPRINT_DETAILS = "getFootprintDetails";

    private final UserFootprintsRepository myFootprintsRepository;

    @Inject
    public GetUserFootprintDetails(UserFootprintsRepository myFootprintsRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
    }

    @UseCase(name = USE_CASE_GET_USER_FOOTPRINT_DETAILS)
    public void getUserFootprintDetails(String userFootprintKey) throws Exception {
        UserFootprint userFootprint = myFootprintsRepository.getByKey(userFootprintKey);
        notifySuccess(userFootprint);
    }
}
