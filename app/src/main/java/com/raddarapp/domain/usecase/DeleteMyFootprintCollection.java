package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyFootprintsCollectionRepository;

import javax.inject.Inject;

public class DeleteMyFootprintCollection extends RosieUseCase {

    public static final String USE_CASE_DELETE_MY_FOOTPRINT_COLLECTION = "deleteMyFootprintCollection";

    private final MyFootprintsCollectionRepository myFootprintsCollectionRepository;

    @Inject
    public DeleteMyFootprintCollection(MyFootprintsCollectionRepository myFootprintsCollectionRepository) {
        this.myFootprintsCollectionRepository = myFootprintsCollectionRepository;
    }

    @UseCase(name = USE_CASE_DELETE_MY_FOOTPRINT_COLLECTION)
    public void deleteMyFootprintCollection(String footprintCollectionKey) throws Exception {
        boolean myFootprintCollectionDeleted = myFootprintsCollectionRepository.deleteMyFootprintCollection(footprintCollectionKey);
        notifySuccess(myFootprintCollectionDeleted);
    }
}