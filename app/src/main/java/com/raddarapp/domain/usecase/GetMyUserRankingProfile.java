package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyUsersRankingRepository;
import com.raddarapp.domain.model.MyUserRanking;

import javax.inject.Inject;

public class GetMyUserRankingProfile extends RosieUseCase {

    public static final String USE_CASE_GET_MY_USER_RANKING_PROFILE_BY_USER_ID = "getMyUserRankingProfileByUserId";

    private MyUsersRankingRepository myUserRankingRepository;

    @Inject
    public GetMyUserRankingProfile(MyUsersRankingRepository myUserRankingRepository) {
        this.myUserRankingRepository = myUserRankingRepository;
    }

    @UseCase(name = USE_CASE_GET_MY_USER_RANKING_PROFILE_BY_USER_ID)
    public void getMyUserRankingProfileByUserId(String key) throws Exception {
        MyUserRanking myUserRankingProfile = myUserRankingRepository.getByKey(key);
        myUserRankingRepository.addUserFootprintInLocalCache(myUserRankingProfile);
        notifySuccess(myUserRankingProfile);
    }

    public MyUserRanking getUserFootprintInLocalCache() {
        return myUserRankingRepository.getUserFootprintInLocalCache();
    }

    public void deleteUserFootprintInLocalCache() {
        myUserRankingRepository.deleteUserFootprintInLocalCache();
    }
}
