package com.raddarapp.domain.model.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.builder.MyFootprintBuilder;
import com.raddarapp.domain.model.enums.FootprintStatus;

import javax.inject.Inject;

public class CreateFootprintToMyFootprintModelMapper extends Mapper<CreateFootprint, MyFootprint> {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public CreateFootprintToMyFootprintModelMapper(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public MyFootprint map(CreateFootprint createFootprint) {
        final MyFootprint myFootprint =  new MyFootprintBuilder()
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
                .withZoneName(createFootprint.getCurrentZoneName())
                .withParentZoneName(createFootprint.getCurrentZoneParentName())
                .withCountryEmoji(createFootprint.getCountryEmoji())
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withVisible(createFootprint.isVisible())
                .withRaddarLocation(createFootprint.getRaddarLocation())
                .withUser(userProfilePreferencesDataSource.userProfileToUserMapper())
                .build();

        return myFootprint;
    }

    @Override
    public CreateFootprint reverseMap(MyFootprint myFootprint) {
        throw new UnsupportedOperationException();
    }
}
