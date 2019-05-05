package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyFootprintsCollectionRepository;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class GetMyFootprintCollectionDetails extends RosieUseCase {

    public static final String USE_CASE_GET_MY_FOOTPRINT_COLLECTION_DETAILS = "getMyFootprintCollectionDetails";

    private final MyFootprintsCollectionRepository myFootprintsCollectionRepository;

    @Inject
    public GetMyFootprintCollectionDetails(MyFootprintsCollectionRepository myFootprintsCollectionRepository) {
        this.myFootprintsCollectionRepository = myFootprintsCollectionRepository;
    }

    @UseCase(name = USE_CASE_GET_MY_FOOTPRINT_COLLECTION_DETAILS)
    public void getMyFootprintCollectionDetails(String myFootprintCollectionKey) throws Exception {
        MyFootprintCollection myFootprintCollection = myFootprintsCollectionRepository.getByKey(myFootprintCollectionKey);
        myFootprintsCollectionRepository.addUserMyFootprintCollectionInLocalCache(myFootprintCollection.getUser());
        notifySuccess(myFootprintCollection);
    }

    public User getUserMyFootprintCollectionInLocalCache() {
        return myFootprintsCollectionRepository.getUserMyFootprintCollectionInLocalCache();
    }

    public void deleteUserFootprintMainInLocalCache() {
        myFootprintsCollectionRepository.deleteUserMyFootprintCollectionInLocalCache();
    }
}
