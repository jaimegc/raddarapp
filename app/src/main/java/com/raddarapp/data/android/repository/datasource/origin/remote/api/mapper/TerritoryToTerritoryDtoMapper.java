package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryDto;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.builder.TerritoryBuilder;

public class TerritoryToTerritoryDtoMapper extends Mapper<Territory, TerritoryDto> {

    private final UserToUserDtoMapper mapper = new UserToUserDtoMapper();

    @Override
    public TerritoryDto map(Territory territory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Territory reverseMap(TerritoryDto territoryDto) {

        final Territory territory = new TerritoryBuilder()
                .withKey(territoryDto.getId())
                .withName(territoryDto.getName())
                .withParentKey(territoryDto.getParentId())
                .withParentName(territoryDto.getParentName())
                .withArea(territoryDto.getArea())
                .withTotalFootprints(territoryDto.getTotalFootprints())
                .withCountryEmoji(territoryDto.getCountryEmoji())
                .withHasSons(territoryDto.hasSons())
                .withLeader(territoryDto.getLeader() != null ? mapper.reverseMap(territoryDto.getLeader()) : null)
                .build();

        return territory;
    }
}
