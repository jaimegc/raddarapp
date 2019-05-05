package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.FootprintsMainRepository;
import com.raddarapp.data.general.FootprintsRankingRepository;
import com.raddarapp.data.general.MyFootprintsCollectionRepository;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.data.general.MyUserProfileRepository;
import com.raddarapp.data.general.MyUsersRankingRepository;
import com.raddarapp.data.general.TerritoryMainRepository;
import com.raddarapp.data.general.UserFootprintsRepository;

import javax.inject.Inject;


public class RemoveRepositoryCacheLogout extends RosieUseCase {

    public static final String USE_CASE_REMOVE_REPOSITORY_CACHE_LOGOUT = "removeRepositoryCacheLogout";

    // These repositories because after logout and come back to the app
    // the cache is visible
    private final MyFootprintsRepository myFootprintsRepository;
    private final MyUsersRankingRepository myUsersRankingRepository;
    private final TerritoryMainRepository myTerritoryMainRepository;
    private final FootprintsMainRepository footprintsMainRepository;
    private final UserFootprintsRepository userFootprintsRepository;
    private final MyFootprintsCollectionRepository myFootprintsCollectionRepository;
    private final FootprintsRankingRepository footprintsRankingRepository;
    private final MyUserProfileRepository myUserProfileRepository;

    @Inject
    public RemoveRepositoryCacheLogout(MyFootprintsRepository myFootprintsRepository, MyUsersRankingRepository myUsersRankingRepository,
            TerritoryMainRepository myTerritoryMainRepository, FootprintsMainRepository footprintsMainRepository,
            UserFootprintsRepository userFootprintsRepository, MyFootprintsCollectionRepository myFootprintsCollectionRepository,
            FootprintsRankingRepository footprintsRankingRepository, MyUserProfileRepository myUserProfileRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
        this.myUsersRankingRepository = myUsersRankingRepository;
        this.myTerritoryMainRepository = myTerritoryMainRepository;
        this.footprintsMainRepository = footprintsMainRepository;
        this.userFootprintsRepository = userFootprintsRepository;
        this.myFootprintsCollectionRepository = myFootprintsCollectionRepository;
        this.footprintsRankingRepository = footprintsRankingRepository;
        this.myUserProfileRepository = myUserProfileRepository;
    }

    public boolean deleteAllCache() {
        boolean res = true;
        try {
            myFootprintsRepository.deleteAll();
            myFootprintsRepository.deleteMyFootprintsLocalCache();
            myUsersRankingRepository.deleteAll();
            myUsersRankingRepository.deleteUsersRankingLocalCache();
            myTerritoryMainRepository.deleteAll();
            myTerritoryMainRepository.deleteMyTerritoryMainLocalCache();
            footprintsMainRepository.deleteUserFootprintMainInLocalCache();
            userFootprintsRepository.deleteUserFootprintsLocalCache();
            myFootprintsCollectionRepository.deleteAll();
            footprintsRankingRepository.deleteAll();
            myUserProfileRepository.deleteAll();

        } catch (Exception e) {
            res = false;
        }

        return res;
    }

    @UseCase(name = USE_CASE_REMOVE_REPOSITORY_CACHE_LOGOUT)
    public void removeRepositoryCacheLogout() throws Exception {

        boolean isRepositoryCacheRemoved = deleteAllCache();

        notifySuccess(isRepositoryCacheRemoved);
    }
}
