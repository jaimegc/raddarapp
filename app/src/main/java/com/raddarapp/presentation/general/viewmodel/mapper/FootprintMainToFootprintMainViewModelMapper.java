package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintMainViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;

import javax.inject.Inject;

public class FootprintMainToFootprintMainViewModelMapper extends Mapper<FootprintMain, FootprintMainViewModelContract> {

    @Inject
    public FootprintMainToFootprintMainViewModelMapper() {}

    @Override
    public FootprintMainViewModelContract map(FootprintMain footprintMain) {
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        FootprintMainViewModel footprintMainViewModel =
                new FootprintMainViewModelBuilder()
                        .withKey(footprintMain.getKey())
                        .withTitle(footprintMain.getTitle())
                        .withMedia(footprintMain.getMedia())
                        .withUserKey(footprintMain.getUser().getKey())
                        .withUsername("@" + footprintMain.getUser().getUsername())
                        .withUserImage(footprintMain.getUser().getImage())
                        .withUserLevel(String.valueOf(footprintMain.getUser().getLevel()))
                        .withUserRelationship(footprintMain.getUser().getUserRelationship())
                        .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                                footprintMain.getRaddarLocation().getCreationMoment())))
                        .withMediaType(footprintMain.getMediaType())
                        .withVisibility(footprintMain.getVisibility())
                        .withSponsored(footprintMain.getSponsored())
                        .withComments(footprintMain.getComments())
                        .withLatitude(footprintMain.getRaddarLocation().getLatitude())
                        .withLongitude(footprintMain.getRaddarLocation().getLongitude())
                        .withScope(footprintMain.getScope())
                        .withCategory(footprintMain.getCategory())
                        .withLevel(footprintMain.getLevel())
                        .withAudio(footprintMain.getUser().getAudio())
                        .withLikes(footprintMain.getLikes())
                        .withDislikes(footprintMain.getDislikes())
                        .withType(footprintMain.getType())
                        .withVisible(footprintMain.isVisible())
                        .withPowerSelected(false)
                        .withZoneName(footprintMain.getZoneName())
                        .withParentZoneName(footprintMain.getParentZoneName())
                        .withCountryEmoji(emojiUtils.emojiCountry(footprintMain.getCountryEmoji()))
                        .build();

        return footprintMainViewModel;
    }

    @Override
    public FootprintMain reverseMap(FootprintMainViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
