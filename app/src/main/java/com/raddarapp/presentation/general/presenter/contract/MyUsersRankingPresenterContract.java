package com.raddarapp.presentation.general.presenter.contract;

import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;

public interface MyUsersRankingPresenterContract {

    void onMyUserRankingClicked(MyUserRankingViewModel myUserRanking, int position);

    void onMyLeaderUserRankingClicked(MyLeaderRankingViewModel myUserRanking, int position);

    void onMyLeaderKingUserRankingClicked(MyLeaderKingRankingViewModel myUserRanking, int position);
}
