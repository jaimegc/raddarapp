package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.CreateFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.CreateFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.CreateFootprintViewModelBuilder;

import javax.inject.Inject;

public class CreateFootprintToCreateFootprintViewModelMapper extends Mapper<CreateFootprint, CreateFootprintViewModel> {

    @Inject
    public CreateFootprintToCreateFootprintViewModelMapper() {}

    @Override
    public CreateFootprintViewModel map(CreateFootprint createdFootprint) {

        EmojiUtils emojiUtils = new EmojiUtils();

        CreateFootprintViewModel createdFootprintViewModel = new CreateFootprintViewModelBuilder()
                .withKey(createdFootprint.getKey())
                .withMedia(createdFootprint.getMediaName())
                .withTitle(createdFootprint.getTitle())
                .withDescription(createdFootprint.getDescription())
                .withMediaType(createdFootprint.getMediaType())
                .withVisibility(createdFootprint.getVisibility())
                .withAspectRatio(createdFootprint.getAspectRatio())
                .withLongitude(createdFootprint.getRaddarLocation().getLongitude())
                .withLatitude(createdFootprint.getRaddarLocation().getLatitude())
                .withCurrentZoneName(createdFootprint.getCurrentZoneName())
                .withCountryEmoji(emojiUtils.emojiCountry(createdFootprint.getCountryEmoji()))
                .withCategory(createdFootprint.getCategory())
                .withType(createdFootprint.getType())
                .withVisible(createdFootprint.isVisible())
                .withCurrentZoneParentName(createdFootprint.getCurrentZoneParentName())
                .withCreationMoment(createdFootprint.getRaddarLocation().getCreationMoment())
                .build();

        return createdFootprintViewModel;
    }

    @Override
    public CreateFootprint reverseMap(CreateFootprintViewModel createFootprintViewModel) {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(createFootprintViewModel.getLatitude())
                .withLongitude(createFootprintViewModel.getLongitude())
                .withCreationMoment(createFootprintViewModel.getCreationMoment())
                .build();

        final CreateFootprint createFootprint = new CreateFootprintBuilder()
                .withDescription(createFootprintViewModel.getDescription())
                .withMediaName(createFootprintViewModel.getMedia())
                .withTitle(createFootprintViewModel.getTitle())
                .withDescription(createFootprintViewModel.getDescription())
                .withMediaType(createFootprintViewModel.getMediaType())
                .withVisibility(createFootprintViewModel.getVisibility())
                .withAspectRatio(createFootprintViewModel.getAspectRatio())
                .withCurrentZoneName(createFootprintViewModel.getCurrentZoneName())
                .withCurrentZoneParentName(createFootprintViewModel.getCurrentZoneParentName())
                .withCountryEmoji(createFootprintViewModel.getCountryEmoji())
                .withType(createFootprintViewModel.getType())
                .withVisible(createFootprintViewModel.isVisible())
                .withCategory(createFootprintViewModel.getCategory())
                .withRaddarLocation(raddarLocation)
                .build();

        return createFootprint;
    }
}
