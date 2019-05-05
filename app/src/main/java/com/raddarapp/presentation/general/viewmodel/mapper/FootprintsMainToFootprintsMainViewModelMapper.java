package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintInsigniaMainViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.builder.FootprintMainViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class FootprintsMainToFootprintsMainViewModelMapper extends Mapper<PaginatedCollection<FootprintMain>, List<FootprintMainViewModelContract>> {

    private DateUtils dateUtils = new DateUtils();
    private EmojiUtils emojiUtils = new EmojiUtils();

    @Inject
    public FootprintsMainToFootprintsMainViewModelMapper() {}

    @Override
    public List<FootprintMainViewModelContract> map(PaginatedCollection<FootprintMain> footprintsMain) {
        List<FootprintMainViewModelContract> footprintsViewModels = new LinkedList<>();

        for (FootprintMain footprintMain : footprintsMain.getItems()) {
            if (footprintMain.getType() == FootprintType.FOOTPRINT.getValue()) {
                footprintsViewModels.add(mapFootprintMainToFootprintMainViewModel(footprintMain));
            } else if (footprintMain.getType() == FootprintType.INSIGNE.getValue()){
                footprintsViewModels.add(mapFootprintInsigniaMainToFootprintInsigniaMainViewModel(footprintMain));
            }
        }

        return footprintsViewModels;
    }

    private FootprintMainViewModel mapFootprintMainToFootprintMainViewModel(FootprintMain footprintMain) {
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
                        .withZoneName(footprintMain.getZoneName())
                        .withParentZoneName(footprintMain.getParentZoneName())
                        .withCountryEmoji(emojiUtils.emojiCountry(footprintMain.getCountryEmoji()))
                        .withPowerSelected(false)
                        .build();

        return footprintMainViewModel;
    }

    private FootprintInsigniaMainViewModel mapFootprintInsigniaMainToFootprintInsigniaMainViewModel(FootprintMain footprintMain) {
        FootprintInsigniaMainViewModel footprintInsigniaMainViewModel =
                new FootprintInsigniaMainViewModelBuilder()
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
                        .withZoneName(footprintMain.getZoneName())
                        .withParentZoneName(footprintMain.getParentZoneName())
                        .withCountryEmoji(footprintMain.getCountryEmoji())
                        .withPowerSelected(false)
                        .build();

        return footprintInsigniaMainViewModel;
    }

    private List<FootprintMainViewModel> mapFootprintsMainToFootprintsViewModel(PaginatedCollection<FootprintMain> footprintsMain) {
        List<FootprintMainViewModel> footprintsMainViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();

        for (FootprintMain footprintMain : footprintsMain.getItems()) {
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
                    .withZoneName(footprintMain.getZoneName())
                    .withParentZoneName(footprintMain.getParentZoneName())
                    .withCountryEmoji(emojiUtils.emojiCountry(footprintMain.getCountryEmoji()))
                    .withPowerSelected(false)
                    .build();

            footprintsMainViewModels.add(footprintMainViewModel);
        }

        return footprintsMainViewModels;
    }

    @Override
    public PaginatedCollection<FootprintMain> reverseMap(List<FootprintMainViewModelContract> value) {
        throw new UnsupportedOperationException();
    }
}
