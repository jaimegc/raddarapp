package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.domain.model.MyFootprint;

import javax.inject.Inject;

public class GetMyFootprintDetails extends RosieUseCase {

    public static final String USE_CASE_GET_MY_FOOTPRINT_DETAILS = "getMyFootprintDetails";

    private final MyFootprintsRepository myFootprintsRepository;

    @Inject
    public GetMyFootprintDetails(MyFootprintsRepository myFootprintsRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
    }

    @UseCase(name = USE_CASE_GET_MY_FOOTPRINT_DETAILS)
    public void getMyFootprintDetails(String myFootprintKey) throws Exception {
        MyFootprint myFootprint = myFootprintsRepository.getByKey(myFootprintKey);
        notifySuccess(myFootprint);
    }
}
