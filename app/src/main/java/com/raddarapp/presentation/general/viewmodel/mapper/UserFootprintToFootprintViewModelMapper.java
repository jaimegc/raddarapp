package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserFootprintViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

import javax.inject.Inject;

public class UserFootprintToFootprintViewModelMapper extends Mapper<UserFootprint, UserFootprintViewModelContract> {

    @Inject
    public UserFootprintToFootprintViewModelMapper() {}

    @Override
    public UserFootprintViewModelContract map(UserFootprint userFootprint) {
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        UserFootprintViewModel userFootprintViewModel =
                new UserFootprintViewModelBuilder()
                     .withKey(userFootprint.getKey())
                     .withTitle(userFootprint.getTitle())
                     .withMedia(userFootprint.getMedia())
                     .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                                userFootprint.getRaddarLocation().getCreationMoment())))
                     .withMediaType(userFootprint.getMediaType())
                     .withVisibility(userFootprint.getVisibility())
                     .withSponsored(userFootprint.getSponsored())
                     .withComments(userFootprint.getComments())
                     .withLatitude(userFootprint.getRaddarLocation().getLatitude())
                     .withLongitude(userFootprint.getRaddarLocation().getLongitude())
                     .withScope(userFootprint.getScope())
                     .withCategory(userFootprint.getCategory())
                     .withLevel(userFootprint.getLevel())
                     .withType(userFootprint.getCategory())
                     .withVisible(userFootprint.isVisible())
                     .withZoneName(userFootprint.getZoneName())
                     .withParentZoneName(userFootprint.getParentZoneName())
                     .withCountryEmoji(emojiUtils.emojiCountry(userFootprint.getCountryEmoji()))
                     .withStatus(userFootprint.getStatus())
                     .build();

        return userFootprintViewModel;
    }

    @Override
    public UserFootprint reverseMap(UserFootprintViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
