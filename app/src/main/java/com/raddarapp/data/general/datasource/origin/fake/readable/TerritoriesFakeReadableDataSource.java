package com.raddarapp.data.general.datasource.origin.fake.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoriesReadableDataSourceContract;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.TerritoryBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.model.util.GenerateUsers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class TerritoriesFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, Territory>
    implements TerritoriesReadableDataSourceContract {

    private static final int NUMBER_OF_TERRITORIES = 100;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, Territory> items = new HashMap<>();
    private int primaryKey = 0;
    private GenerateUsers generateUsers = new GenerateUsers();

    @Inject
    public TerritoriesFakeReadableDataSource() {
    }

    @Override
    public Territory getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedCollection<Territory> getTerritoriesFirstLevel(Page page) throws Exception {
        Collection<Territory> territories = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_TERRITORIES; i++) {
            territories.add(getTerritory());
            primaryKey++;
        }

        boolean hasMore = (offset + 1) * territories.size() < NUMBER_OF_TERRITORIES;
        PaginatedCollection<Territory> paginatedTerritories = new PaginatedCollection<>(territories);
        paginatedTerritories.setPage(page);
        paginatedTerritories.setHasMore(hasMore);

        return paginatedTerritories;
    }

    @Override
    public PaginatedCollection<Territory> getTerritoriesByZoneId(String zoneKey, Page page) throws Exception {
        return null;
    }

    @Override
    public Territory getTerritoryDetailsByCoordinates(RaddarLocation raddarLocation) throws Exception {
        fakeDelay();

        return getDosHermanas();
    }

    private Territory getTerritory() {
        Territory[] allTerritories = {getMontequinto(), getCoripe(), getAlfalfa(), getAlmeria(), getDosHermanas(), getCarmona()};

        Territory territory = allTerritories[RANDOM.nextInt(allTerritories.length)];

        return territory;
    }

    private Territory getMontequinto() {
        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Montequinto" + String.valueOf(primaryKey))
                .withName("Montequinto")
                .withParentName("Dos Hermanas")
                .withArea(9500)
                .withTotalFootprints(1200)
                .withCountryEmoji("ES")
                .withHasSons(false)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getCoripe() {
        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Coripe" + String.valueOf(primaryKey))
                .withName("Coripe")
                .withParentName("Sevilla")
                .withArea(98500)
                .withTotalFootprints(900)
                .withCountryEmoji("ES")
                .withHasSons(false)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getAlfalfa() {
        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Alfalfa" + String.valueOf(primaryKey))
                .withName("Alfalfa")
                .withParentName("Casco Antiguo")
                .withArea(8500)
                .withTotalFootprints(300)
                .withCountryEmoji("ES")
                .withHasSons(false)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getAlmeria() {
        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Almería" + String.valueOf(primaryKey))
                .withName("Almería")
                .withParentName("Andalucía")
                .withArea(285000)
                .withTotalFootprints(44300)
                .withCountryEmoji("ES")
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getDosHermanas() {
        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Dos Hermanas" + String.valueOf(primaryKey))
                .withName("Dos Hermanas")
                .withParentName("Sevilla")
                .withArea(8500)
                .withTotalFootprints(300)
                .withCountryEmoji("ES")
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private Territory getCarmona() {
        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final Territory territory = new TerritoryBuilder()
                .withKey("Carmona" + String.valueOf(primaryKey))
                .withName("Carmona")
                .withParentName("Sevilla")
                .withArea(78500)
                .withTotalFootprints(6300)
                .withCountryEmoji("ES")
                .withHasSons(true)
                .withLeader(user)
                .build();

        return territory;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
