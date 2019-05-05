package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic;

import com.google.gson.annotations.SerializedName;

public class ServerResponse<T> {

    @SerializedName("content") private T response;

    public T getResponse(){
        return this.response;
    }
}
