package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class TerritoryDto {

    @SerializedName("id")
    private final String id;
    @SerializedName("name")
    private final String name;
    @SerializedName("parentId")
    private final String parentId;
    @SerializedName("parentName")
    private final String parentName;
    @SerializedName("area")
    private final long area;
    @SerializedName("totalFootprints")
    private final long totalFootprints;
    @SerializedName("countryEmoji")
    private final String countryEmoji;
    @SerializedName("hasSons")
    private final boolean hasSons;

    @SerializedName("leader")
    private final UserDto leader;

    public TerritoryDto(String id, String name, String parentId, String parentName, long area, long totalFootprints,
            String countryEmoji, boolean hasSons, UserDto leader) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
        this.area = area;
        this.totalFootprints = totalFootprints;
        this.countryEmoji = countryEmoji;
        this.hasSons = hasSons;
        this.leader = leader;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
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

    public UserDto getLeader() {
        return leader;
    }

    public boolean hasSons() {
        return hasSons;
    }
}
