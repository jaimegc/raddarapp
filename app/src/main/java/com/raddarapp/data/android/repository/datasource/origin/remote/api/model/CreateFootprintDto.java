package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class CreateFootprintDto {

    @SerializedName("id")
    private String id;
    @SerializedName("mediaName")
    private String mediaName;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("mediaType")
    private int mediaType;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("aspectRatio")
    private int aspectRatio;
    @SerializedName("sponsored")
    private boolean sponsored;
    @SerializedName("category")
    private int category;
    @SerializedName("currentZoneName")
    private final String currentZoneName;
    @SerializedName("currentZoneParent")
    private final String currentZoneParentName;
    @SerializedName("countryEmoji")
    private final String countryEmoji;
    @SerializedName("type")
    private int type;
    @SerializedName("visible")
    private boolean visible;
    @SerializedName("raddarLocation")
    private RaddarLocationDto raddarLocationDto;

    public CreateFootprintDto(String id, String mediaName,String title,  String description, int mediaType,
            int visibility, int aspectRatio, boolean sponsored, int category, String currentZoneName,
            String currentZoneParentName, String countryEmoji, int type, boolean visible, RaddarLocationDto raddarLocationDto) {
        this.id = id;
        this.mediaName = mediaName;
        this.title = title;
        this.description = description;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.aspectRatio = aspectRatio;
        this.sponsored = sponsored;
        this.category = category;
        this.currentZoneName = currentZoneName;
        this.currentZoneParentName = currentZoneParentName;
        this.countryEmoji = countryEmoji;
        this.raddarLocationDto = raddarLocationDto;
        this.visible = visible;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getMediaType() {
        return mediaType;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public boolean getSponsored() {
        return sponsored;
    }

    public int getCategory() {
        return category;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public String getCurrentZoneName() {
        return currentZoneName;
    }

    public String getCurrentZoneParentName() {
        return currentZoneParentName;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public RaddarLocationDto getRaddarLocationDto() {
        return raddarLocationDto;
    }
}
