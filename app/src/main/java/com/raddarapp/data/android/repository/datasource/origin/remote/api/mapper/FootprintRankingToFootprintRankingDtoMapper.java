package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintRankingDto;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.FootprintRankingBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FootprintRankingToFootprintRankingDtoMapper extends Mapper<FootprintRanking, FootprintRankingDto> {

    @Override
    public FootprintRankingDto map(FootprintRanking footprintRanking) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FootprintRanking reverseMap(FootprintRankingDto footprintRankingDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(footprintRankingDto.getRaddarLocationDto().getLatitude())
                .withLongitude(footprintRankingDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(footprintRankingDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final User user;

        if (footprintRankingDto.getUser() != null) {
            user = new UserBuilder()
                    .withKey(footprintRankingDto.getUser().getId())
                    .withUsername(footprintRankingDto.getUser().getUsername())
                    .withImage(footprintRankingDto.getUser().getImage())
                    .withAudio(footprintRankingDto.getUser().getAudio())
                    .withRange(footprintRankingDto.getUser().getRange())
                    .withFollowers(footprintRankingDto.getUser().getFollowers())
                    .withFollowing(footprintRankingDto.getUser().getFollowing())
                    .withUserRelationship(footprintRankingDto.getUser().getUserRelationship())
                    .withLevel(footprintRankingDto.getUser().getLevel())
                    .withTotalFootprints(footprintRankingDto.getUser().getTotalFootprints())
                    .withUserCompliments(footprintRankingDto.getUser().getUserCompliments())
                    .withTotalLikes(footprintRankingDto.getUser().getTotalLikes())
                    .withTotalDislikes(footprintRankingDto.getUser().getTotalDislikes())
                    .withTotalFootprintsDead(footprintRankingDto.getUser().getTotalFootprintsDead())
                    .build();
        } else {
            // My own user is empty
            user = null;
        }

        final FootprintRanking footprintRanking = new FootprintRankingBuilder()
                .withKey(footprintRankingDto.getId())
                .withMedia(footprintRankingDto.getMedia())
                .withTitle(footprintRankingDto.getTitle())
                .withDescription(footprintRankingDto.getDescription())
                .withLikes(footprintRankingDto.getLikes())
                .withDislikes(footprintRankingDto.getDislikes())
                .withComments(footprintRankingDto.getComments())
                .withViews(footprintRankingDto.getViews())
                .withMediaType(footprintRankingDto.getMediaType())
                .withVoted(footprintRankingDto.getVoted())
                .withVisibility(footprintRankingDto.getVisibility())
                .withSponsored(!footprintRankingDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withScope(footprintRankingDto.getScope())
                .withCategory(footprintRankingDto.getCategory())
                .withAspectRatio(footprintRankingDto.getAspectRatio())
                .withLevel(footprintRankingDto.getLevel())
                .withType(footprintRankingDto.getType())
                .withVisible(footprintRankingDto.isVisible())
                .withZoneName(footprintRankingDto.getZoneName())
                .withParentZoneName(footprintRankingDto.getParentZoneName())
                .withCountryEmoji(footprintRankingDto.getCountryEmoji())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        return footprintRanking;
    }

    public Collection<FootprintRanking> reverseMapCollection(List<FootprintRankingDto> footprintsRankingDto) {
        List<FootprintRanking> footprintsRanking = new LinkedList<>();

        for (FootprintRankingDto footprintRankingDto : footprintsRankingDto) {
            footprintsRanking.add(reverseMap(footprintRankingDto));
        }

        return footprintsRanking;
    }
}
