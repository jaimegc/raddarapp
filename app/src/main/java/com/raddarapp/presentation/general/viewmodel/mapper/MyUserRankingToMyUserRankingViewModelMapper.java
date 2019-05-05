package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyLeaderKingRankingViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.builder.MyLeaderRankingViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.builder.MyUserRankingViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyUserRankingToMyUserRankingViewModelMapper extends Mapper<PaginatedCollection<MyUserRanking>, List<MyUserRankingViewModelContract>> {

    private NumberUtils numberUtils = new NumberUtils();

    @Inject
    public MyUserRankingToMyUserRankingViewModelMapper() {}

    @Override
    public List<MyUserRankingViewModelContract> map(PaginatedCollection<MyUserRanking> myUserRankings) {
        List<MyUserRankingViewModelContract> footprintsViewModels = new ArrayList<>();
        List<MyUserRanking> listMyUserRankings = new ArrayList<>(myUserRankings.getItems());

        if (!listMyUserRankings.isEmpty()) {
            for (int i = 0; i < listMyUserRankings.size(); i++) {
                if (i > 2) {
                    footprintsViewModels.add(mapMyUserRankingToMyUserRankingViewModel(listMyUserRankings.get(i)));
                } else if (i == 0) {
                    footprintsViewModels.add(mapMyUserRankingKingToMyLeaderRankingViewModel(listMyUserRankings.get(i)));
                } else {
                    footprintsViewModels.add(mapMyUserRankingToMyLeaderRankingViewModel(listMyUserRankings.get(i)));
                }
            }
        }

        return footprintsViewModels;
    }

    private MyUserRankingViewModel mapMyUserRankingToMyUserRankingViewModel(MyUserRanking myUserRanking) {
        MyUserRankingViewModel myUserRankingViewModel =
                new MyUserRankingViewModelBuilder()
                        .withKey(myUserRanking.getKey())
                        .withUsername("@" + myUserRanking.getUsername())
                        .withImage(myUserRanking.getImage())
                        .withAudio(myUserRanking.getAudio())
                        .withRange(numberUtils.numberToGroupedString(myUserRanking.getRange()))
                        .withLevel(String.valueOf(myUserRanking.getLevel()))
                        .withTotalScopeZone(numberUtils.rangeOrScopeToString(myUserRanking.getTotalScopeZone()))
                        .withTotalFootprintsZone(numberUtils.numberToGroupedString(myUserRanking.getTotalFootprintsZone()))
                        .withRelationship(myUserRanking.getUserRelationship())
                        .withTotalLikesZone(myUserRanking.getTotalLikesZone())
                        .withTotalDislikesZone(myUserRanking.getTotalDislikesZone())
                        .withTotalLikes(myUserRanking.getTotalLikesZone())
                        .build();

        return myUserRankingViewModel;
    }

    private MyLeaderRankingViewModel mapMyUserRankingToMyLeaderRankingViewModel(MyUserRanking myUserRanking) {
        MyLeaderRankingViewModel myLeaderRankingViewModel =
                new MyLeaderRankingViewModelBuilder()
                        .withKey(myUserRanking.getKey())
                        .withUsername("@" + myUserRanking.getUsername())
                        .withImage(myUserRanking.getImage())
                        .withAudio(myUserRanking.getAudio())
                        .withRange(numberUtils.numberToGroupedString(myUserRanking.getRange()))
                        .withLevel(String.valueOf(myUserRanking.getLevel()))
                        .withTotalScopeZone(numberUtils.rangeOrScopeToString(myUserRanking.getTotalScopeZone()))
                        .withTotalFootprintsZone(numberUtils.numberToGroupedString(myUserRanking.getTotalFootprintsZone()))
                        .withRelationship(myUserRanking.getUserRelationship())
                        .withTotalLikesZone(myUserRanking.getTotalLikesZone())
                        .withTotalDislikesZone(myUserRanking.getTotalDislikesZone())
                        .build();

        return myLeaderRankingViewModel;
    }

    private MyLeaderKingRankingViewModel mapMyUserRankingKingToMyLeaderRankingViewModel(MyUserRanking myUserRanking) {
        MyLeaderKingRankingViewModel myLeaderRankingKingViewModel =
                new MyLeaderKingRankingViewModelBuilder()
                        .withKey(myUserRanking.getKey())
                        .withUsername("@" + myUserRanking.getUsername())
                        .withImage(myUserRanking.getImage())
                        .withAudio(myUserRanking.getAudio())
                        .withRange(numberUtils.numberToGroupedString(myUserRanking.getRange()))
                        .withLevel(String.valueOf(myUserRanking.getLevel()))
                        .withTotalScopeZone(numberUtils.rangeOrScopeToString(myUserRanking.getTotalScopeZone()))
                        .withTotalFootprintsZone(numberUtils.numberToGroupedString(myUserRanking.getTotalFootprintsZone()))
                        .withRelationship(myUserRanking.getUserRelationship())
                        .withTotalLikesZone(myUserRanking.getTotalLikesZone())
                        .withTotalDislikesZone(myUserRanking.getTotalDislikesZone())
                        .build();

        return myLeaderRankingKingViewModel;
    }

    @Override
    public PaginatedCollection<MyUserRanking> reverseMap(List<MyUserRankingViewModelContract> value) {
        throw new UnsupportedOperationException();
    }
}
