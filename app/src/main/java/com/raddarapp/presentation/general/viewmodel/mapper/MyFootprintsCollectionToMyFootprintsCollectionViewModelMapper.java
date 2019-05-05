package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.User;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintCollectionViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsCollectionToMyFootprintsCollectionViewModelMapper extends Mapper<PaginatedCollection<MyFootprintCollection>, List<MyFootprintCollectionViewModelContract>> {

    private List<String> keyPositions = new LinkedList<>();
    private final MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    @Inject
    public MyFootprintsCollectionToMyFootprintsCollectionViewModelMapper(MyUserProfilePreferencesDataSource userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public List<MyFootprintCollectionViewModelContract> map(PaginatedCollection<MyFootprintCollection> footprintsMain) {
        List<MyFootprintCollectionViewModelContract> footprintsViewModels = new LinkedList<>();

        footprintsViewModels.addAll(mapFootprintsMainToFootprintsViewModel(footprintsMain));

        return footprintsViewModels;
    }

    private List<MyFootprintCollectionViewModel> mapFootprintsMainToFootprintsViewModel(PaginatedCollection<MyFootprintCollection> footprintsMain) {
        List<MyFootprintCollectionViewModel> footprintsMainViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();
        keyPositions.clear();

        User userPreferences = userProfilePreferencesDataSource.userProfileToUserMapper();

        for (MyFootprintCollection myFootprintCollection : footprintsMain.getItems()) {

            // My own user is empty, so we must to add it
            if (myFootprintCollection.getUser() == null) {
                myFootprintCollection.setUser(userPreferences);
            }

            MyFootprintCollectionViewModel myFootprintCollectionViewModel =
                new MyFootprintCollectionViewModelBuilder()
                    .withKey(myFootprintCollection.getKey())
                    .withTitle(myFootprintCollection.getTitle())
                    .withMedia(myFootprintCollection.getMedia())
                    .withUserKey(myFootprintCollection.getUser().getKey())
                    .withUsername("@" + myFootprintCollection.getUser().getUsername())
                    .withUserImage(myFootprintCollection.getUser().getImage())
                    .withUserLevel(String.valueOf(myFootprintCollection.getUser().getLevel()))
                    .withUserRelationship(myFootprintCollection.getUser().getUserRelationship())
                    .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                            myFootprintCollection.getRaddarLocation().getCreationMoment())))
                    .withMediaType(myFootprintCollection.getMediaType())
                    .withVisibility(myFootprintCollection.getVisibility())
                    .withSponsored(myFootprintCollection.getSponsored())
                    .withComments(myFootprintCollection.getComments())
                    .withLatitude(myFootprintCollection.getRaddarLocation().getLatitude())
                    .withLongitude(myFootprintCollection.getRaddarLocation().getLongitude())
                    .withScope(myFootprintCollection.getScope())
                    .withCategory(myFootprintCollection.getCategory())
                    .withLevel(myFootprintCollection.getLevel())
                    .withAudio(myFootprintCollection.getUser().getAudio())
                    .withLikes(myFootprintCollection.getLikes())
                    .withDislikes(myFootprintCollection.getDislikes())
                    .withType(myFootprintCollection.getType())
                    .withVisible(myFootprintCollection.isVisible())
                    .withZoneName(myFootprintCollection.getZoneName())
                    .withParentZoneName(myFootprintCollection.getParentZoneName())
                    .withCountryEmoji(emojiUtils.emojiCountry(myFootprintCollection.getCountryEmoji()))
                    .withStatus(myFootprintCollection.getStatus())
                    .withPowerSelected(false)
                    .build();

            keyPositions.add(myFootprintCollection.getKey());

            footprintsMainViewModels.add(myFootprintCollectionViewModel);
        }

        return footprintsMainViewModels;
    }

    @Override
    public PaginatedCollection<MyFootprintCollection> reverseMap(List<MyFootprintCollectionViewModelContract> value) {
        throw new UnsupportedOperationException();
    }

    public List<String> getKeyPositions() {
        return keyPositions;
    }
}
