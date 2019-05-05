package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.CreateFootprintDtoBuilder;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.RaddarLocationDtoBuilder;
import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.CreateFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

public class CreateFootprintToCreateFootprintDtoMapper extends Mapper<CreateFootprint, CreateFootprintDto> {

    @Override
    public CreateFootprint reverseMap(CreateFootprintDto createFootprintDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(createFootprintDto.getRaddarLocationDto().getLatitude())
                .withLongitude(createFootprintDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(createFootprintDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final CreateFootprint createFootprint = new CreateFootprintBuilder()
                .withKey(createFootprintDto.getId())
                .withMediaName(createFootprintDto.getMediaName())
                .withTitle(createFootprintDto.getTitle())
                .withDescription(createFootprintDto.getDescription())
                .withMediaType(createFootprintDto.getMediaType())
                .withVisibility(createFootprintDto.getVisibility())
                .withAspectRatio(createFootprintDto.getAspectRatio())
                .withSponsored(!createFootprintDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withCategory(createFootprintDto.getCategory())
                .withCurrentZoneName(createFootprintDto.getCurrentZoneName())
                .withCurrentZoneParentName(createFootprintDto.getCurrentZoneParentName())
                .withCountryEmoji(createFootprintDto.getCountryEmoji())
                .withType(createFootprintDto.getType())
                .withVisible(createFootprintDto.isVisible())
                .withRaddarLocation(raddarLocation)
                .build();

        return createFootprint;
    }

    @Override
    public CreateFootprintDto map(CreateFootprint createFootprint) {
        final RaddarLocationDto raddarLocationDto = new RaddarLocationDtoBuilder()
                .withLatitude(createFootprint.getRaddarLocation().getLatitude())
                .withLongitude(createFootprint.getRaddarLocation().getLongitude())
                .withCreationMoment(createFootprint.getRaddarLocation().getCreationMoment())
                .build();

        final CreateFootprintDto createFootprintDto = new CreateFootprintDtoBuilder()
                .withId(createFootprint.getKey())
                .withMediaName(createFootprint.getMediaName())
                .withTitle(createFootprint.getTitle())
                .withDescription(createFootprint.getDescription())
                .withMediaType(createFootprint.getMediaType())
                .withVisibility(createFootprint.getVisibility())
                .withAspectRatio(createFootprint.getAspectRatio())
                .withSponsored(createFootprint.getSponsored() ==  SponsoredType.NO_SPONSORED.getValue() ? false : true)
                .withCategory(createFootprint.getCategory())
                .withCurrentZoneName(createFootprint.getCurrentZoneName())
                .withCurrentZoneParentName(createFootprint.getCurrentZoneParentName())
                .withCountryEmoji(createFootprint.getCountryEmoji())
                .withType(createFootprint.getType())
                .withVisible(createFootprint.isVisible())
                .withRaddarLocationDto(raddarLocationDto)
                .build();

        return createFootprintDto;
    }
}
