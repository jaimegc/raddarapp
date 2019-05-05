package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.User;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintRankingViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class FootprintsRankingToFootprintsRankingViewModelMapper extends Mapper<PaginatedCollection<FootprintRanking>, List<FootprintRankingViewModelContract>> {

    private List<String> keyPositions = new LinkedList<>();
    private final MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    @Inject
    public FootprintsRankingToFootprintsRankingViewModelMapper(MyUserProfilePreferencesDataSource userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public List<FootprintRankingViewModelContract> map(PaginatedCollection<FootprintRanking> footprintsMain) {
        List<FootprintRankingViewModelContract> footprintsViewModels = new LinkedList<>();

        footprintsViewModels.addAll(mapFootprintsMainToFootprintsViewModel(footprintsMain));

        return footprintsViewModels;
    }

    private List<FootprintRankingViewModel> mapFootprintsMainToFootprintsViewModel(PaginatedCollection<FootprintRanking> footprintsMain) {
        List<FootprintRankingViewModel> footprintsMainViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();
        keyPositions.clear();

        User userPreferences = userProfilePreferencesDataSource.userProfileToUserMapper();

        for (FootprintRanking footprintRanking : footprintsMain.getItems()) {

            // My own user is empty, so we must to add it
            if (footprintRanking.getUser() == null) {
                footprintRanking.setUser(userPreferences);
            }

            FootprintRankingViewModel footprintRankingViewModel =
                    new FootprintRankingViewModelBuilder()
                            .withKey(footprintRanking.getKey())
                            .withTitle(footprintRanking.getTitle())
                            .withMedia(footprintRanking.getMedia())
                            .withUserKey(footprintRanking.getUser().getKey())
                            .withUsername("@" + footprintRanking.getUser().getUsername())
                            .withUserImage(footprintRanking.getUser().getImage())
                            .withUserLevel(String.valueOf(footprintRanking.getUser().getLevel()))
                            .withUserRelationship(footprintRanking.getUser().getUserRelationship())
                            .withCreationMoment(footprintRanking.getRaddarLocation().getCreationMoment() != null ?
                                    String.valueOf(dateUtils.stringDateToMilliseconds(
                                            footprintRanking.getRaddarLocation().getCreationMoment())) :
                                    String.valueOf(System.currentTimeMillis()))
                            .withMediaType(footprintRanking.getMediaType())
                            .withVisibility(footprintRanking.getVisibility())
                            .withSponsored(footprintRanking.getSponsored())
                            .withComments(footprintRanking.getComments())
                            .withLatitude(footprintRanking.getRaddarLocation().getLatitude())
                            .withLongitude(footprintRanking.getRaddarLocation().getLongitude())
                            .withScope(footprintRanking.getScope())
                            .withCategory(footprintRanking.getCategory())
                            .withLevel(footprintRanking.getLevel())
                            .withAudio(footprintRanking.getUser().getAudio())
                            .withLikes(footprintRanking.getLikes())
                            .withDislikes(footprintRanking.getDislikes())
                            .withType(footprintRanking.getType())
                            .withVisible(footprintRanking.isVisible())
                            .withZoneName(footprintRanking.getZoneName())
                            .withParentZoneName(footprintRanking.getParentZoneName())
                            .withCountryEmoji(emojiUtils.emojiCountry(footprintRanking.getCountryEmoji()))
                            .withPowerSelected(false)
                            .build();

            keyPositions.add(footprintRanking.getKey());

            footprintsMainViewModels.add(footprintRankingViewModel);
        }

        return footprintsMainViewModels;
    }

    @Override
    public PaginatedCollection<FootprintRanking> reverseMap(List<FootprintRankingViewModelContract> value) {
        throw new UnsupportedOperationException();
    }

    public List<String> getKeyPositions() {
        return keyPositions;
    }
}
