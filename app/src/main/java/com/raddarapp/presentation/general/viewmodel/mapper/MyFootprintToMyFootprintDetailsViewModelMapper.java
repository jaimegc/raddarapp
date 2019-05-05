package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.User;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.MyFootprintDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintDetailsViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintDetailsViewModelContract;

import javax.inject.Inject;

public class MyFootprintToMyFootprintDetailsViewModelMapper extends Mapper<MyFootprint, MyFootprintDetailsViewModelContract> {

    private final MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    @Inject
    public MyFootprintToMyFootprintDetailsViewModelMapper(MyUserProfilePreferencesDataSource userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public MyFootprintDetailsViewModelContract map(MyFootprint myFootprint) {
        DateUtils dateUtils = new DateUtils();
        NumberUtils numberUtils = new NumberUtils();
        EmojiUtils emojiUtils = new EmojiUtils();
        User userPreferences = userProfilePreferencesDataSource.userProfileToUserMapper();

        MyFootprintDetailsViewModel myFootprintViewModel =
                new MyFootprintDetailsViewModelBuilder()
                        .withKey(myFootprint.getKey())
                        .withDescription(myFootprint.getDescription())
                        .withTitle(myFootprint.getTitle())
                        .withMedia(myFootprint.getMedia())
                        .withUserKey(userPreferences.getKey())
                        .withUsername("@" + userPreferences.getUsername())
                        .withUserImage(userPreferences.getImage())
                        .withUserRange(userPreferences.getRange())
                        .withUserFollowers(numberUtils.numberToGroupedString(userPreferences.getFollowers()))
                        .withUserFollowing(numberUtils.numberToGroupedString(userPreferences.getFollowing()))
                        .withUserLevel(String.valueOf(userPreferences.getLevel()))
                        .withUserTotalFootprints(numberUtils.numberToGroupedString(userPreferences.getTotalFootprints()))
                        .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                                myFootprint.getRaddarLocation().getCreationMoment())))
                        .withMediaType(myFootprint.getMediaType())
                        .withVisibility(myFootprint.getVisibility())
                        .withAspectRatio(myFootprint.getAspectRatio())
                        .withSponsored(myFootprint.getSponsored())
                        .withViews(numberUtils.numberToGroupedString(myFootprint.getViews()))
                        .withComments(myFootprint.getComments())
                        .withLikes(myFootprint.getLikes())
                        .withDislikes(myFootprint.getDislikes())
                        .withLatitude(myFootprint.getRaddarLocation().getLatitude())
                        .withLongitude(myFootprint.getRaddarLocation().getLongitude())
                        .withScope(myFootprint.getScope())
                        .withCategory(myFootprint.getCategory())
                        .withCaptures(numberUtils.numberToGroupedString(myFootprint.getCaptures()))
                        .withType(myFootprint.getType())
                        .withVisible(myFootprint.isVisible())
                        .withZoneName(myFootprint.getZoneName())
                        .withParentZoneName(myFootprint.getParentZoneName())
                        .withCountryEmoji(emojiUtils.emojiCountry(myFootprint.getCountryEmoji()))
                        .build();

        return myFootprintViewModel;
    }

    @Override
    public MyFootprint reverseMap(MyFootprintDetailsViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
