package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsToMyFootprintsViewModelMapper extends Mapper<PaginatedCollection<MyFootprint>, List<MyFootprintViewModelContract>> {

    private List<String> keyPositions = new LinkedList<>();

    @Inject
    public MyFootprintsToMyFootprintsViewModelMapper() {
    }

    @Override
    public List<MyFootprintViewModelContract> map(PaginatedCollection<MyFootprint> myFootprints) {
        List<MyFootprintViewModelContract> footprintsViewModels = new LinkedList<>();

        footprintsViewModels.addAll(mapMyFootprintsToMyFootprintsViewModel(myFootprints));

        return footprintsViewModels;
    }

    private List<MyFootprintViewModel> mapMyFootprintsToMyFootprintsViewModel(PaginatedCollection<MyFootprint> myFootprints) {
        List<MyFootprintViewModel> footprintsMainViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();
        keyPositions.clear();

        for (MyFootprint myFootprint : myFootprints.getItems()) {
            MyFootprintViewModel myFootprintViewModel =
                new MyFootprintViewModelBuilder()
                    .withKey(myFootprint.getKey())
                    .withTitle(myFootprint.getTitle())
                    .withMedia(myFootprint.getMedia())
                    .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                            myFootprint.getRaddarLocation().getCreationMoment())))
                    .withMediaType(myFootprint.getMediaType())
                    .withVisibility(myFootprint.getVisibility())
                    .withAspectRatio(myFootprint.getAspectRatio())
                    .withSponsored(myFootprint.getSponsored())
                    .withComments(myFootprint.getComments())
                    .withLikes(myFootprint.getLikes())
                    .withDislikes(myFootprint.getDislikes())
                    .withLatitude(myFootprint.getRaddarLocation().getLatitude())
                    .withLongitude(myFootprint.getRaddarLocation().getLongitude())
                    .withScope(myFootprint.getScope())
                    .withCategory(myFootprint.getCategory())
                    .withLevel(myFootprint.getLevel())
                    .withType(myFootprint.getType())
                    .withVisible(myFootprint.isVisible())
                    .withZoneName(myFootprint.getZoneName())
                    .withParentZoneName(myFootprint.getParentZoneName())
                    .withCountryEmoji(emojiUtils.emojiCountry(myFootprint.getCountryEmoji()))
                    .withStatus(myFootprint.getStatus())
                    .build();

            keyPositions.add(myFootprint.getKey());

            footprintsMainViewModels.add(myFootprintViewModel);
        }

        return footprintsMainViewModels;
    }

    @Override
    public PaginatedCollection<MyFootprint> reverseMap(List<MyFootprintViewModelContract> value) {
        throw new UnsupportedOperationException();
    }

    public List<String> getKeyPositions() {
        return keyPositions;
    }
}
