package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserRankingDto;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.builder.MyUserRankingBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyUserRankingToMyUserRankingDtoMapper extends Mapper<MyUserRanking, MyUserRankingDto> {

    @Override
    public MyUserRankingDto map(MyUserRanking footprintMain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyUserRanking reverseMap(MyUserRankingDto myUserRankingDto) {

        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(myUserRankingDto.getId())
                .withUsername(myUserRankingDto.getUsername())
                .withImage(myUserRankingDto.getImage())
                .withAudio(myUserRankingDto.getAudio())
                .withRange(myUserRankingDto.getRange())
                .withFollowers(myUserRankingDto.getFollowers())
                .withFollowing(myUserRankingDto.getFollowing())
                .withUserRelationship(myUserRankingDto.getUserRelationship())
                .withLevel(myUserRankingDto.getLevel())
                .withTotalScopeZone(myUserRankingDto.getTotalScopeZone())
                .withTotalFootprints(myUserRankingDto.getTotalFootprints())
                .withUserCompliments(myUserRankingDto.getUserCompliments())
                .withTotalFootprintsZone(myUserRankingDto.getTotalFootprintsZone())
                .withTotalLikesZone(myUserRankingDto.getTotalLikesZone())
                .withTotalDislikesZone(myUserRankingDto.getTotalDislikesZone())
                .withTotalLikes(myUserRankingDto.getTotalLikes())
                .withTotalDislikes(myUserRankingDto.getTotalDislikes())
                .withTotalFootprintsDead(myUserRankingDto.getTotalFootprintsDead())
                .build();

        return myUserRanking;
    }

    public Collection<MyUserRanking> reverseMapCollection(List<MyUserRankingDto> myUsersRankingDto) {
        List<MyUserRanking> footprintsMainCollection = new LinkedList<>();

        for (MyUserRankingDto myUserRankingDto : myUsersRankingDto) {
            footprintsMainCollection.add(reverseMap(myUserRankingDto));
        }

        return footprintsMainCollection;
    }
}
