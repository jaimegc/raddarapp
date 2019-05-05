package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintCollectionDto;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.MyFootprintCollectionBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.SponsoredType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyFootprintCollectionToMyFootprintCollectionDtoMapper extends Mapper<MyFootprintCollection, MyFootprintCollectionDto> {

    @Override
    public MyFootprintCollectionDto map(MyFootprintCollection myFootprintCollection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyFootprintCollection reverseMap(MyFootprintCollectionDto myFootprintCollectionDto) {
        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(myFootprintCollectionDto.getRaddarLocationDto().getLatitude())
                .withLongitude(myFootprintCollectionDto.getRaddarLocationDto().getLongitude())
                .withCreationMoment(myFootprintCollectionDto.getRaddarLocationDto().getCreationMoment())
                .build();

        final User user;

        if (myFootprintCollectionDto.getUser() != null) {
            user = new UserBuilder()
                    .withKey(myFootprintCollectionDto.getUser().getId())
                    .withUsername(myFootprintCollectionDto.getUser().getUsername())
                    .withImage(myFootprintCollectionDto.getUser().getImage())
                    .withAudio(myFootprintCollectionDto.getUser().getAudio())
                    .withRange(myFootprintCollectionDto.getUser().getRange())
                    .withFollowers(myFootprintCollectionDto.getUser().getFollowers())
                    .withFollowing(myFootprintCollectionDto.getUser().getFollowing())
                    .withUserRelationship(myFootprintCollectionDto.getUser().getUserRelationship())
                    .withLevel(myFootprintCollectionDto.getUser().getLevel())
                    .withTotalFootprints(myFootprintCollectionDto.getUser().getTotalFootprints())
                    .withUserCompliments(myFootprintCollectionDto.getUser().getUserCompliments())
                    .withTotalLikes(myFootprintCollectionDto.getUser().getTotalLikes())
                    .withTotalDislikes(myFootprintCollectionDto.getUser().getTotalDislikes())
                    .withTotalFootprintsDead(myFootprintCollectionDto.getUser().getTotalFootprintsDead())
                    .build();
        } else {
            // My own user is empty
            user = null;
        }

        final MyFootprintCollection myFootprintCollection = new MyFootprintCollectionBuilder()
                .withKey(myFootprintCollectionDto.getId())
                .withMedia(myFootprintCollectionDto.getMedia())
                .withTitle(myFootprintCollectionDto.getTitle())
                .withDescription(myFootprintCollectionDto.getDescription())
                .withLikes(myFootprintCollectionDto.getLikes())
                .withDislikes(myFootprintCollectionDto.getDislikes())
                .withComments(myFootprintCollectionDto.getComments())
                .withViews(myFootprintCollectionDto.getViews())
                .withMediaType(myFootprintCollectionDto.getMediaType())
                .withVoted(myFootprintCollectionDto.getVoted())
                .withVisibility(myFootprintCollectionDto.getVisibility())
                .withSponsored(!myFootprintCollectionDto.getSponsored() ?
                        SponsoredType.NO_SPONSORED.getValue() : SponsoredType.ENTERPRISE.getValue())
                .withScope(myFootprintCollectionDto.getScope())
                .withCategory(myFootprintCollectionDto.getCategory())
                .withAspectRatio(myFootprintCollectionDto.getAspectRatio())
                .withLevel(myFootprintCollectionDto.getLevel())
                .withType(myFootprintCollectionDto.getType())
                .withVisible(myFootprintCollectionDto.isVisible())
                .withZoneName(myFootprintCollectionDto.getZoneName())
                .withParentZoneName(myFootprintCollectionDto.getParentZoneName())
                .withCountryEmoji(myFootprintCollectionDto.getCountryEmoji())
                .withStatus(myFootprintCollectionDto.getStatus())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        return myFootprintCollection;
    }

    public Collection<MyFootprintCollection> reverseMapCollection(List<MyFootprintCollectionDto> myFootprintsCollectionDto) {
        List<MyFootprintCollection> myFootprintsCollection = new LinkedList<>();

        for (MyFootprintCollectionDto myFootprintCollectionDto : myFootprintsCollectionDto) {
            myFootprintsCollection.add(reverseMap(myFootprintCollectionDto));
        }

        return myFootprintsCollection;
    }
}
