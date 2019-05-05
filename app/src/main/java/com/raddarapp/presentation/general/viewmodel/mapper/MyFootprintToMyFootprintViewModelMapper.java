package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.MyFootprintViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import javax.inject.Inject;

public class MyFootprintToMyFootprintViewModelMapper extends Mapper<MyFootprint, MyFootprintViewModelContract> {

    @Inject
    public MyFootprintToMyFootprintViewModelMapper() {
    }

    @Override
    public MyFootprintViewModelContract map(MyFootprint myFootprint) {
        DateUtils dateUtils = new DateUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        final MyFootprintViewModel myFootprintViewModel =
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

        return myFootprintViewModel;
    }

    @Override
    public MyFootprint reverseMap(MyFootprintViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
