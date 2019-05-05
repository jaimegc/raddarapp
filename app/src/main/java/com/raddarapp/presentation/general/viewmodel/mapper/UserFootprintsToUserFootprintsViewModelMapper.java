package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.UserFootprintViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class UserFootprintsToUserFootprintsViewModelMapper extends Mapper<PaginatedCollection<UserFootprint>, List<UserFootprintViewModelContract>> {

    @Inject
    public UserFootprintsToUserFootprintsViewModelMapper() {}

    @Override
    public List<UserFootprintViewModelContract> map(PaginatedCollection<UserFootprint> userFootprints) {
        List<UserFootprintViewModelContract> userFootprintsViewModels = new LinkedList<>();

        userFootprintsViewModels.addAll(mapUserFootprintsToUserFootprintsViewModel(userFootprints));

        return userFootprintsViewModels;
    }

    private List<UserFootprintViewModel> mapUserFootprintsToUserFootprintsViewModel(PaginatedCollection<UserFootprint> userFootprints) {
        List<UserFootprintViewModel> userFootprintsViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();
        NumberUtils numberUtils = new NumberUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        for (UserFootprint userFootprint : userFootprints.getItems()) {
            UserFootprintViewModel footprintMainViewModel =
                new UserFootprintViewModelBuilder()
                        .withKey(userFootprint.getKey())
                        .withTitle(userFootprint.getTitle())
                        .withMedia(userFootprint.getMedia())
                        .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                                userFootprint.getRaddarLocation().getCreationMoment())))
                        .withMediaType(userFootprint.getMediaType())
                        .withVisibility(userFootprint.getVisibility())
                        .withAspectRatio(userFootprint.getAspectRatio())
                        .withSponsored(userFootprint.getSponsored())
                        .withComments(userFootprint.getComments())
                        .withLikes(userFootprint.getLikes())
                        .withDislikes(userFootprint.getDislikes())
                        .withLatitude(userFootprint.getRaddarLocation().getLatitude())
                        .withLongitude(userFootprint.getRaddarLocation().getLongitude())
                        .withScope(userFootprint.getScope())
                        .withCategory(userFootprint.getCategory())
                        .withLevel(userFootprint.getLevel())
                        .withType(userFootprint.getType())
                        .withVisible(userFootprint.isVisible())
                        .withZoneName(userFootprint.getZoneName())
                        .withParentZoneName(userFootprint.getParentZoneName())
                        .withCountryEmoji(emojiUtils.emojiCountry(userFootprint.getCountryEmoji()))
                        .withStatus(userFootprint.getStatus())
                        .withTotalLikes(userFootprint.getTotalLikes())
                        .withTotalDislikes(userFootprint.getTotalDislikes())
                        .withTotalFootprintsDead(numberUtils.numberToGroupedString(userFootprint.getTotalFootprintsDead()))
                        .build();

            userFootprintsViewModels.add(footprintMainViewModel);
        }

        return userFootprintsViewModels;
    }

    @Override
    public PaginatedCollection<UserFootprint> reverseMap(List<UserFootprintViewModelContract> value) {
        throw new UnsupportedOperationException();
    }
}
