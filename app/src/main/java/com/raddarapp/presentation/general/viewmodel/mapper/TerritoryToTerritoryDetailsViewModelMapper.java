package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.TerritoryViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;

import javax.inject.Inject;

public class TerritoryToTerritoryDetailsViewModelMapper extends Mapper<Territory, TerritoryViewModelContract> {

    @Inject
    public TerritoryToTerritoryDetailsViewModelMapper() {}

    @Override
    public TerritoryViewModelContract map(Territory territory) {
        NumberUtils numberUtils = new NumberUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        TerritoryViewModel territoryAreaViewModel = new TerritoryViewModelBuilder()
                .withKey(territory.getKey())
                .withName(territory.getName())
                .withParentKey(territory.getParentKey())
                .withParentName(territory.getParentName())
                .withArea(numberUtils.numberToGroupedString(territory.getArea()))
                .withTotalFootprints(numberUtils.numberToGroupedString(territory.getTotalFootprints()))
                .withEmojiCountry(emojiUtils.emojiCountry(territory.getCountryEmoji()))
                .withHasSons(territory.hasSons())
                .withLeaderKey(territory.getLeader() != null ? territory.getLeader().getKey() : "")
                .withLeaderUsername(territory.getLeader() != null ? territory.getLeader().getUsername() : "")
                .withLeaderImage(territory.getLeader() != null ? territory.getLeader().getImage() : "")
                .withLeaderFollowers(territory.getLeader() != null ? numberUtils.numberToGroupedString(territory.getLeader().getFollowers()) : "")
                .withLeaderRange(territory.getLeader() != null ? numberUtils.numberToGroupedString(territory.getLeader().getRange()) : "")
                .withLeaderLevel(territory.getLeader() != null ? numberUtils.numberToGroupedString(territory.getLeader().getLevel()) : "")
                .withLeaderAudio(territory.getLeader() != null ? territory.getLeader().getAudio()  : "")
                .withLeaderRelationship(territory.getLeader() != null ? territory.getLeader().getUserRelationship() : UserRelationshipType.UNKNOWN.getValue())
                .build();

        return territoryAreaViewModel;
    }

    @Override
    public Territory reverseMap(TerritoryViewModelContract value) {
        throw new UnsupportedOperationException();
    }
}
