package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic;

import com.google.gson.annotations.SerializedName;

public class MyUserRankingServerResponseCollection<T> {
    @SerializedName("ranking") private ServerResponseCollection<T> ranking;
    @SerializedName("position") private Long position;
    @SerializedName("totalFootprintsZone") private Long totalFootprintsZone;

    public ServerResponseCollection<T> getRanking() {
        return ranking;
    }

    public Long getPosition() {
        return position;
    }

    public Long getTotalFootprintsZone() {
        return totalFootprintsZone;
    }
}
