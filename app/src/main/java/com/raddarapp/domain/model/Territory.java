package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class Territory implements Identifiable<String> {

    private final String key;
    private final String name;
    private final String parentKey;
    private final String parentName;
    private final long area;
    private final long totalFootprints;
    private final String countryEmoji;
    private final boolean hasSons;

    private final User leader;

    public Territory(String key, String name, String parentKey, String parentName, long area, long totalFootprints,
            String countryEmoji, boolean hasSons, User leader) {
        this.key = key;
        this.name = name;
        this.parentKey = parentKey;
        this.parentName = parentName;
        this.area = area;
        this.totalFootprints = totalFootprints;
        this.countryEmoji = countryEmoji;
        this.hasSons = hasSons;
        this.leader = leader;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getParentKey() {
        return parentKey;
    }

    public String getParentName() {
        return parentName;
    }

    public long getArea() {
        return area;
    }

    public long getTotalFootprints() {
        return totalFootprints;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }

    public User getLeader() {
        return leader;
    }

    public boolean hasSons() {
        return hasSons;
    }

    public void updateLeaderRelationship(int leaderRelationship) {
        leader.updateUserRelationship(leaderRelationship);
    }
}
