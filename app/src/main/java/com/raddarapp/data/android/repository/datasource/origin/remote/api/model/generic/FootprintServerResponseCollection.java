package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic;

import com.google.gson.annotations.SerializedName;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;

public class FootprintServerResponseCollection<T> {
    @SerializedName("footprints") private ServerResponseCollection<T> footprints;
    @SerializedName("user") private MyUserProfileDto myUserProfile;

    public ServerResponseCollection getFootprints() {
        return footprints;
    }

    public MyUserProfileDto getMyUserProfile() {
        return myUserProfile;
    }
}
