package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintMainDto;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.FootprintMainBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FootprintMainToFootprintMainDtoMapper extends Mapper<FootprintMain, FootprintMainDto> {

    @Override
    public FootprintMainDto map(FootprintMain footprintMain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FootprintMain reverseMap(FootprintMainDto footprintMainDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(footprintMainDto.getRaddarLocationDto().getLatitude())
                .withLongitude(footprintMainDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(footprintMainDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final User user = new UserBuilder()
                .withKey(footprintMainDto.getUser().getId())
                .withUsername(footprintMainDto.getUser().getUsername())
                .withImage(footprintMainDto.getUser().getImage())
                .withAudio(footprintMainDto.getUser().getAudio())
                .withRange(footprintMainDto.getUser().getRange())
                .withFollowers(footprintMainDto.getUser().getFollowers())
                .withFollowing(footprintMainDto.getUser().getFollowing())
                .withUserRelationship(footprintMainDto.getUser().getUserRelationship())
                .withLevel(footprintMainDto.getUser().getLevel())
                .withTotalFootprints(footprintMainDto.getUser().getTotalFootprints())
                .withUserCompliments(footprintMainDto.getUser().getUserCompliments())
                .withTotalLikes(footprintMainDto.getUser().getTotalLikes())
                .withTotalDislikes(footprintMainDto.getUser().getTotalDislikes())
                .withTotalFootprintsDead(footprintMainDto.getUser().getTotalFootprintsDead())
                .build();

        final FootprintMain footprintMain = new FootprintMainBuilder()
                .withKey(footprintMainDto.getId())
                .withMedia(footprintMainDto.getMedia())
                .withTitle(footprintMainDto.getTitle())
                .withDescription(footprintMainDto.getDescription())
                .withLikes(footprintMainDto.getLikes())
                .withDislikes(footprintMainDto.getDislikes())
                .withComments(footprintMainDto.getComments())
                .withViews(footprintMainDto.getViews())
                .withMediaType(footprintMainDto.getMediaType())
                .withVoted(footprintMainDto.getVoted())
                .withVisibility(footprintMainDto.getVisibility())
                .withSponsored(!footprintMainDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withScope(footprintMainDto.getScope())
                .withCategory(footprintMainDto.getCategory())
                .withAspectRatio(footprintMainDto.getAspectRatio())
                .withLevel(footprintMainDto.getLevel())
                .withType(footprintMainDto.getType())
                .withVisible(footprintMainDto.isVisible())
                .withZoneName(footprintMainDto.getZoneName())
                .withParentZoneName(footprintMainDto.getParentZoneName())
                .withCountryEmoji(footprintMainDto.getCountryEmoji())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        return footprintMain;
    }

    public Collection<FootprintMain> reverseMapCollection(List<FootprintMainDto> footprintsMainDto) {
        List<FootprintMain> footprintsMainCollection = new LinkedList<>();

        for (FootprintMainDto footprintMainDto : footprintsMainDto) {
            footprintsMainCollection.add(reverseMap(footprintMainDto));
        }

        return footprintsMainCollection;
    }
}
