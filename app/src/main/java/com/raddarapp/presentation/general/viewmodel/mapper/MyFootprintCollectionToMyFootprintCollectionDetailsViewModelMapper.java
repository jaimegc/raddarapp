package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintCollectionDetailsViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionDetailsViewModelContract;

import javax.inject.Inject;

public class MyFootprintCollectionToMyFootprintCollectionDetailsViewModelMapper extends Mapper<MyFootprintCollection, MyFootprintCollectionDetailsViewModelContract> {

    @Inject
    public MyFootprintCollectionToMyFootprintCollectionDetailsViewModelMapper() {}

    @Override
    public MyFootprintCollectionDetailsViewModelContract map(MyFootprintCollection myFootprintCollection) {
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        MyFootprintCollectionDetailsViewModel myFootprintCollectionDetailsViewModel = new MyFootprintCollectionDetailsViewModelBuilder()
                .withKey(myFootprintCollection.getKey())
                .withMedia(myFootprintCollection.getMedia())
                .withTitle(myFootprintCollection.getTitle())
                .withDescription(myFootprintCollection.getDescription())
                .withViews(numberUtils.numberToGroupedString(myFootprintCollection.getViews()))
                .withComments(myFootprintCollection.getComments())
                .withLikes(myFootprintCollection.getLikes())
                .withDislikes(myFootprintCollection.getDislikes())
                .withUserKey(myFootprintCollection.getUser().getKey())
                .withUsername("@" + myFootprintCollection.getUser().getUsername())
                .withUserImage(myFootprintCollection.getUser().getImage())
                .withUserRange(myFootprintCollection.getUser().getRange())
                .withUserFollowers(numberUtils.numberToGroupedString(myFootprintCollection.getUser().getFollowers()))
                .withUserLevel(String.valueOf(myFootprintCollection.getUser().getLevel()))
                .withUserTotalFootprints(numberUtils.numberToGroupedString(myFootprintCollection.getUser().getTotalFootprints()))
                .withUserRelationship(myFootprintCollection.getUser().getUserRelationship())
                .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(
                        myFootprintCollection.getRaddarLocation().getCreationMoment())))
                .withAspectRatio(myFootprintCollection.getAspectRatio())
                .withScope(myFootprintCollection.getScope())
                .withLatitude(myFootprintCollection.getRaddarLocation().getLatitude())
                .withLongitude(myFootprintCollection.getRaddarLocation().getLongitude())
                .withAudio(myFootprintCollection.getUser().getAudio())
                .withCategory(myFootprintCollection.getCategory())
                .withVisible(myFootprintCollection.isVisible())
                .withZoneName(myFootprintCollection.getZoneName())
                .withParentZoneName(myFootprintCollection.getParentZoneName())
                .withCountryEmoji(emojiUtils.emojiCountry(myFootprintCollection.getCountryEmoji()))
                .build();

        return myFootprintCollectionDetailsViewModel;
    }

    @Override
    public MyFootprintCollection reverseMap(MyFootprintCollectionDetailsViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
