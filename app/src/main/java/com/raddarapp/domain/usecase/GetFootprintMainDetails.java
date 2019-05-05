package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.FootprintsMainRepository;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class GetFootprintMainDetails extends RosieUseCase {

    public static final String USE_CASE_GET_FOOTPRINT_MAIN_DETAILS = "getFootprintMainDetails";

    private final FootprintsMainRepository footprintsMainRepository;

    @Inject
    public GetFootprintMainDetails(FootprintsMainRepository footprintsMainRepository) {
        this.footprintsMainRepository = footprintsMainRepository;
    }

    @UseCase(name = USE_CASE_GET_FOOTPRINT_MAIN_DETAILS)
    public void getFootprintDetails(String footprintMainKey) throws Exception {
        FootprintMain footprintMain = footprintsMainRepository.getByKey(footprintMainKey);
        footprintsMainRepository.addUserFootprintMainInLocalCache(footprintMain.getUser());
        notifySuccess(footprintMain);
    }

    public List<FootprintMain> getAllFootprintsMainInCacheByUserKey(String userKey) {
        Collection<FootprintMain> all;

        try {
            all = footprintsMainRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        List<FootprintMain> alltoList = new ArrayList<>(all);
        List<FootprintMain> allFiltered = new ArrayList<>();

        for (FootprintMain footprintMain : alltoList) {
            if (footprintMain.getUser().getKey().equals(userKey)) {
                allFiltered.add(footprintMain);
            }
        }

        return allFiltered;
    }

    public User getUserFootprintMainInLocalCache() {
        return footprintsMainRepository.getUserFootprintMainInLocalCache();
    }

    public void deleteUserFootprintMainInLocalCache() {
        footprintsMainRepository.deleteUserFootprintMainInLocalCache();
    }
}
