package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.TerritoryViewModelBuilder;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class TerritoriesToTerritoriesViewModelMapper extends Mapper<PaginatedCollection<Territory>, List<TerritoryViewModelContract>> {

    @Inject
    public TerritoriesToTerritoriesViewModelMapper() {}

    @Override
    public List<TerritoryViewModelContract> map(PaginatedCollection<Territory> territories) {
        List<TerritoryViewModelContract> footprintsViewModels = new LinkedList<>();

        footprintsViewModels.addAll(mapTerritoriesToTerritoriesViewModel(territories));

        return footprintsViewModels;
    }

    private List<TerritoryViewModel> mapTerritoriesToTerritoriesViewModel(PaginatedCollection<Territory> territories) {
        List<TerritoryViewModel> territoriesViewModels = new LinkedList<>();

        NumberUtils numberUtils = new NumberUtils();
        EmojiUtils emojiUtils = new EmojiUtils();

        for (Territory territory : territories.getItems()) {
            TerritoryViewModel territoryViewModel = new TerritoryViewModelBuilder()
                .withKey(territory.getKey())
                .withName(territory.getName())
                .withParentKey(territory.getParentKey())
                .withParentName(territory.getParentName())
                .withArea(numberUtils.numberToGroupedString(territory.getArea()))
                .withTotalFootprints(numberUtils.numberToGroupedString(territory.getTotalFootprints()))
                .withEmojiCountry(emojiUtils.emojiCountry(territory.getCountryEmoji()))
                .withHasSons(territory.hasSons())
                .withLeaderKey(territory.getLeader().getKey())
                .withLeaderUsername("@" + territory.getLeader().getUsername())
                .withLeaderImage(territory.getLeader().getImage())
                .withLeaderFollowers(numberUtils.numberToGroupedString(territory.getLeader().getFollowers()))
                .withLeaderRange(numberUtils.numberToGroupedString(territory.getLeader().getRange()))
                .withLeaderRelationship(territory.getLeader().getUserRelationship())
                .withLeaderAudio(territory.getLeader() != null && territory.getLeader().getAudio() != null ? territory.getLeader().getAudio()  : "")
                .build();

            territoriesViewModels.add(territoryViewModel);
        }

        return territoriesViewModels;
    }

    @Override
    public PaginatedCollection<Territory> reverseMap(List<TerritoryViewModelContract> value) {
        throw new UnsupportedOperationException();
    }
}
