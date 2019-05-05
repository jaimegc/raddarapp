package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyFootprintsRepository;

import javax.inject.Inject;

public class DeleteMyFootprint extends RosieUseCase {

    public static final String USE_CASE_DELETE_MY_FOOTPRINT = "deleteMyFootprint";

    private final MyFootprintsRepository myFootprintsRepository;

    @Inject
    public DeleteMyFootprint(MyFootprintsRepository myFootprintsRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
    }

    @UseCase(name = USE_CASE_DELETE_MY_FOOTPRINT)
    public void deleteMyFootprint(String footprintKey) throws Exception {
        boolean myFootprintDeleted = myFootprintsRepository.deleteMyFootprint(footprintKey);
        notifySuccess(myFootprintDeleted);
    }
}