package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.FootprintsRankingRepository;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class GetFootprintRankingDetails extends RosieUseCase {

    public static final String USE_CASE_GET_FOOTPRINT_RANKING_DETAILS = "getFootprintRankingDetails";

    private final FootprintsRankingRepository footprintsRankingRepository;

    @Inject
    public GetFootprintRankingDetails(FootprintsRankingRepository footprintsRankingRepository) {
        this.footprintsRankingRepository = footprintsRankingRepository;
    }

    @UseCase(name = USE_CASE_GET_FOOTPRINT_RANKING_DETAILS)
    public void getFootprintRankingDetails(String footprintRankingKey) throws Exception {
        FootprintRanking footprintRanking = footprintsRankingRepository.getByKey(footprintRankingKey);
        footprintsRankingRepository.addUserFootprintRankingInLocalCache(footprintRanking.getUser());
        notifySuccess(footprintRanking);
    }

    public User getUserFootprintRankingInLocalCache() {
        return footprintsRankingRepository.getUserFootprintRankingInLocalCache();
    }

    public void deleteUserFootprintMainInLocalCache() {
        footprintsRankingRepository.deleteUserFootprintRankingInLocalCache();
    }
}
