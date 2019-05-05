package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.UserFootprintDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserFootprintDetailsViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintDetailsViewModelContract;

import javax.inject.Inject;

public class UserFootprintToUserFootprintDetailsViewModelMapper extends Mapper<UserFootprint, UserFootprintDetailsViewModelContract> {

    @Inject
    public UserFootprintToUserFootprintDetailsViewModelMapper() {}

    @Override
    public UserFootprintDetailsViewModelContract map(UserFootprint userFootprint) {
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        UserFootprintDetailsViewModel userFootprintDetailsViewModel = new UserFootprintDetailsViewModelBuilder()
                .withKey(userFootprint.getKey())
                .withMedia(userFootprint.getMedia())
                .withTitle(userFootprint.getTitle())
                .withDescription(userFootprint.getDescription())
                .withViews(numberUtils.numberToGroupedString(userFootprint.getViews()))
                .withComments(userFootprint.getComments())
                .withLikes(userFootprint.getLikes())
                .withDislikes(userFootprint.getDislikes())
                .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                        userFootprint.getRaddarLocation().getCreationMoment())))
                .withAspectRatio(userFootprint.getAspectRatio())
                .withScope(userFootprint.getScope())
                .withLatitude(userFootprint.getRaddarLocation().getLatitude())
                .withLongitude(userFootprint.getRaddarLocation().getLongitude())
                .withCategory(userFootprint.getCategory())
                .withVisible(userFootprint.isVisible())
                .withZoneName(userFootprint.getZoneName())
                .withParentZoneName(userFootprint.getParentZoneName())
                .withCountryEmoji(emojiUtils.emojiCountry(userFootprint.getCountryEmoji()))
                .build();

        return userFootprintDetailsViewModel;
    }

    @Override
    public UserFootprint reverseMap(UserFootprintDetailsViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
