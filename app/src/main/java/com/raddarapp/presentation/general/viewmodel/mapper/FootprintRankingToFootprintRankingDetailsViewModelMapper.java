package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintRankingDetailsViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingDetailsViewModelContract;

import javax.inject.Inject;

public class FootprintRankingToFootprintRankingDetailsViewModelMapper extends Mapper<FootprintRanking, FootprintRankingDetailsViewModelContract> {

    @Inject
    public FootprintRankingToFootprintRankingDetailsViewModelMapper() {}

    @Override
    public FootprintRankingDetailsViewModelContract map(FootprintRanking footprintRanking) {
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        FootprintRankingDetailsViewModel FootprintRankingDetailsViewModel = new FootprintRankingDetailsViewModelBuilder()
                .withKey(footprintRanking.getKey())
                .withMedia(footprintRanking.getMedia())
                .withTitle(footprintRanking.getTitle())
                .withDescription(footprintRanking.getDescription())
                .withViews(numberUtils.numberToGroupedString(footprintRanking.getViews()))
                .withComments(footprintRanking.getComments())
                .withLikes(footprintRanking.getLikes())
                .withDislikes(footprintRanking.getDislikes())
                .withUserKey(footprintRanking.getUser().getKey())
                .withUsername("@" + footprintRanking.getUser().getUsername())
                .withUserImage(footprintRanking.getUser().getImage())
                .withUserRange(footprintRanking.getUser().getRange())
                .withUserFollowers(numberUtils.numberToGroupedString(footprintRanking.getUser().getFollowers()))
                .withUserLevel(String.valueOf(footprintRanking.getUser().getLevel()))
                .withUserTotalFootprints(numberUtils.numberToGroupedString(footprintRanking.getUser().getTotalFootprints()))
                .withUserRelationship(footprintRanking.getUser().getUserRelationship())
                .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                        footprintRanking.getRaddarLocation().getCreationMoment())))
                .withAspectRatio(footprintRanking.getAspectRatio())
                .withScope(footprintRanking.getScope())
                .withLatitude(footprintRanking.getRaddarLocation().getLatitude())
                .withLongitude(footprintRanking.getRaddarLocation().getLongitude())
                .withAudio(footprintRanking.getUser().getAudio())
                .withCategory(footprintRanking.getCategory())
                .withVisible(footprintRanking.isVisible())
                .withZoneName(footprintRanking.getZoneName())
                .withParentZoneName(footprintRanking.getParentZoneName())
                .withCountryEmoji(emojiUtils.emojiCountry(footprintRanking.getCountryEmoji()))
                .build();

        return FootprintRankingDetailsViewModel;
    }

    @Override
    public FootprintRanking reverseMap(FootprintRankingDetailsViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
