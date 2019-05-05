package com.raddarapp.domain.model.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.builder.FootprintMainBuilder;

import javax.inject.Inject;

public class CreateFootprintToFootprintMainModelMapper extends Mapper<CreateFootprint, FootprintMain> {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public CreateFootprintToFootprintMainModelMapper(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public FootprintMain map(CreateFootprint createFootprint) {
        final FootprintMain footprintMain =  new FootprintMainBuilder()
                .withKey(createFootprint.getKey())
                .withMedia(createFootprint.getMediaName())
                .withTitle(createFootprint.getTitle())
                .withDescription(createFootprint.getDescription())
                .withComments(0)
                .withViews(0)
                .withLikes(0)
                .withDislikes(0)
                .withMediaType(createFootprint.getMediaType())
                .withVisibility(createFootprint.getVisibility())
                .withAspectRatio(createFootprint.getAspectRatio())
                .withSponsored(createFootprint.getSponsored())
                .withScope(0)
                .withCategory(createFootprint.getCategory())
                .withCaptures(0)
                .withType(createFootprint.getType())
                .withVisible(createFootprint.isVisible())
                .withZoneName(createFootprint.getCurrentZoneName())
                .withParentZoneName(createFootprint.getCurrentZoneParentName())
                .withCountryEmoji(createFootprint.getCountryEmoji())
                .withRaddarLocation(createFootprint.getRaddarLocation())
                .withUser(userProfilePreferencesDataSource.userProfileToUserMapper())
                .build();

        return footprintMain;
    }

    @Override
    public CreateFootprint reverseMap(FootprintMain footprintMain) {
        throw new UnsupportedOperationException();
    }
}
