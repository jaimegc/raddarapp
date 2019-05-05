package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.CreateFootprintRepository;

import javax.inject.Inject;

public class CreateFootprint extends RosieUseCase {

    public static final String USE_CASE_CREATE_FOOTPRINT = "createFootprint";

    private final CreateFootprintRepository createFootprintMainRepository;

    @Inject
    public CreateFootprint(CreateFootprintRepository createFootprintMainRepository) {
        this.createFootprintMainRepository = createFootprintMainRepository;
    }

    @UseCase(name = USE_CASE_CREATE_FOOTPRINT)
    public void createFootprint(com.raddarapp.domain.model.CreateFootprint createFootprint, String uriImage) throws Exception {
        com.raddarapp.domain.model.CreateFootprint createdFootprint = createFootprintMainRepository.createFootprint(createFootprint, uriImage);
        notifySuccess(createdFootprint);
    }
}
