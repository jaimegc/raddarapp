package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintDto;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.MyFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyFootprintToMyFootprintDtoMapper extends Mapper<MyFootprint, MyFootprintDto> {

    @Override
    public MyFootprintDto map(MyFootprint footprintMain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyFootprint reverseMap(MyFootprintDto myFootprintDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(myFootprintDto.getRaddarLocationDto().getLatitude())
                .withLongitude(myFootprintDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(myFootprintDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final MyFootprint myFootprint = new MyFootprintBuilder()
                .withKey(myFootprintDto.getId())
                .withMedia(myFootprintDto.getMedia())
                .withTitle(myFootprintDto.getTitle())
                .withDescription(myFootprintDto.getDescription())
                .withLikes(myFootprintDto.getLikes())
                .withDislikes(myFootprintDto.getDislikes())
                .withComments(myFootprintDto.getComments())
                .withViews(myFootprintDto.getViews())
                .withMediaType(myFootprintDto.getMediaType())
                .withVisibility(myFootprintDto.getVisibility())
                .withSponsored(!myFootprintDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withScope(myFootprintDto.getScope())
                .withCategory(myFootprintDto.getCategory())
                .withAspectRatio(myFootprintDto.getAspectRatio())
                .withLevel(myFootprintDto.getLevel())
                .withType(myFootprintDto.getType())
                .withVisible(myFootprintDto.isVisible())
                .withZoneName(myFootprintDto.getZoneName())
                .withParentZoneName(myFootprintDto.getParentZoneName())
                .withCountryEmoji(myFootprintDto.getCountryEmoji())
                .withStatus(myFootprintDto.getStatus())
                .withRaddarLocation(raddarLocation)
                .build();

        return myFootprint;
    }

    public Collection<MyFootprint> reverseMapCollection(List<MyFootprintDto> myFootprintsDto) {
        List<MyFootprint> footprintsMainCollection = new LinkedList<>();

        for (MyFootprintDto footprintMainDto : myFootprintsDto) {
            footprintsMainCollection.add(reverseMap(footprintMainDto));
        }

        return footprintsMainCollection;
    }
}
