package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UserFootprintDto;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.UserFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserFootprintToUserFootprintDtoMapper extends Mapper<UserFootprint, UserFootprintDto> {

    @Override
    public UserFootprintDto map(UserFootprint footprintMain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserFootprint reverseMap(UserFootprintDto userFootprintDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(userFootprintDto.getRaddarLocationDto().getLatitude())
                .withLongitude(userFootprintDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(userFootprintDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final UserFootprint userFootprint = new UserFootprintBuilder()
                .withKey(userFootprintDto.getId())
                .withMedia(userFootprintDto.getMedia())
                .withTitle(userFootprintDto.getTitle())
                .withDescription(userFootprintDto.getDescription())
                .withLikes(userFootprintDto.getLikes())
                .withDislikes(userFootprintDto.getDislikes())
                .withComments(userFootprintDto.getComments())
                .withViews(userFootprintDto.getViews())
                .withMediaType(userFootprintDto.getMediaType())
                .withVisibility(userFootprintDto.getVisibility())
                .withSponsored(!userFootprintDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withScope(userFootprintDto.getScope())
                .withCategory(userFootprintDto.getCategory())
                .withAspectRatio(userFootprintDto.getAspectRatio())
                .withLevel(userFootprintDto.getLevel())
                .withType(userFootprintDto.getType())
                .withVisible(userFootprintDto.isVisible())
                .withStatus(userFootprintDto.getStatus())
                .withZoneName(userFootprintDto.getZoneName())
                .withParentZoneName(userFootprintDto.getParentZoneName())
                .withCountryEmoji(userFootprintDto.getCountryEmoji())
                .withRaddarLocation(raddarLocation)
                .withTotalLikes(userFootprintDto.getTotalLikes())
                .withTotalDislikes(userFootprintDto.getTotalDislikes())
                .withTotalFootprintsDead(userFootprintDto.getTotalFootprintsDead())
                .build();

        return userFootprint;
    }

    public Collection<UserFootprint> reverseMapCollection(List<UserFootprintDto> userFootprintsDto) {
        List<UserFootprint> footprintsMainCollection = new LinkedList<>();

        for (UserFootprintDto footprintMainDto : userFootprintsDto) {
            footprintsMainCollection.add(reverseMap(footprintMainDto));
        }

        return footprintsMainCollection;
    }
}
