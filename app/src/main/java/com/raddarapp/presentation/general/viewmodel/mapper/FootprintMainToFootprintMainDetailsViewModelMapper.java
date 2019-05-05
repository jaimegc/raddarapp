package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.FootprintMainDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintMainDetailsViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainDetailsViewModelContract;

import javax.inject.Inject;

public class FootprintMainToFootprintMainDetailsViewModelMapper extends Mapper<FootprintMain, FootprintMainDetailsViewModelContract> {

    @Inject
    public FootprintMainToFootprintMainDetailsViewModelMapper() {}

    @Override
    public FootprintMainDetailsViewModelContract map(FootprintMain footprintMain) {
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        FootprintMainDetailsViewModel footprintMainDetailsViewModel = new FootprintMainDetailsViewModelBuilder()
                .withKey(footprintMain.getKey())
                .withMedia(footprintMain.getMedia())
                .withTitle(footprintMain.getTitle())
                .withDescription(footprintMain.getDescription())
                .withViews(numberUtils.numberToGroupedString(footprintMain.getViews()))
                .withComments(footprintMain.getComments())
                .withLikes(footprintMain.getLikes())
                .withDislikes(footprintMain.getDislikes())
                .withUserKey(footprintMain.getUser().getKey())
                .withUsername("@" + footprintMain.getUser().getUsername())
                .withUserImage(footprintMain.getUser().getImage())
                .withUserRange(footprintMain.getUser().getRange())
                .withUserFollowers(numberUtils.numberToGroupedString(footprintMain.getUser().getFollowers()))
                .withUserLevel(String.valueOf(footprintMain.getUser().getLevel()))
                .withUserTotalFootprints(numberUtils.numberToGroupedString(footprintMain.getUser().getTotalFootprints()))
                .withUserRelationship(footprintMain.getUser().getUserRelationship())
                .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                        footprintMain.getRaddarLocation().getCreationMoment())))
                .withAspectRatio(footprintMain.getAspectRatio())
                .withScope(footprintMain.getScope())
                .withLatitude(footprintMain.getRaddarLocation().getLatitude())
                .withLongitude(footprintMain.getRaddarLocation().getLongitude())
                .withAudio(footprintMain.getUser().getAudio())
                .withCategory(footprintMain.getCategory())
                .withVisible(footprintMain.isVisible())
                .withZoneName(footprintMain.getZoneName())
                .withParentZoneName(footprintMain.getParentZoneName())
                .withCountryEmoji(emojiUtils.emojiCountry(footprintMain.getCountryEmoji()))
                .build();

        return footprintMainDetailsViewModel;
    }

    @Override
    public FootprintMain reverseMap(FootprintMainDetailsViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
