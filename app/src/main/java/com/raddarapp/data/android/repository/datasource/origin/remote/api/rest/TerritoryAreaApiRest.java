package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryAreaDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TerritoryAreaApiRest {

    @POST("zones/coordinates")
    Call<TerritoryAreaDto> getTerritoryAreaByCoordinates(@Body RaddarLocationDto raddarLocationDto);
}
