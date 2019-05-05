package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.User;

public class TerritoryBuilder {

    private String key;
    private String name;
    private String parentKey;
    private String parentName;
    private long area;
    private long totalFootprints;
    private String countryEmoji;
    private boolean hasSons;

    private User leader;

    public TerritoryBuilder() {}

    public Territory build() {
        return new Territory(key, name, parentKey, parentName, area, totalFootprints, countryEmoji, hasSons, leader);
    }

    public TerritoryBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public TerritoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TerritoryBuilder withParentKey(String parentKey) {
        this.parentKey = parentKey;
        return this;
    }

    public TerritoryBuilder withParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public TerritoryBuilder withArea(long area) {
        this.area = area;
        return this;
    }

    public TerritoryBuilder withTotalFootprints(long totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public TerritoryBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public TerritoryBuilder withHasSons(boolean hasSons) {
        this.hasSons = hasSons;
        return this;
    }

    public TerritoryBuilder withLeader(User leader) {
        this.leader = leader;
        return this;
    }
}
