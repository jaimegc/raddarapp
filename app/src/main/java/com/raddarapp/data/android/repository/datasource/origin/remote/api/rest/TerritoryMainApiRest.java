package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryMainDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TerritoryMainApiRest {

    @POST("zones/coordinates")
    Call<TerritoryMainDto> getTerritoryMainByCoordinates(@Body RaddarLocationDto raddarLocationDto);

    @GET("zones/{zoneId}")
    Call<TerritoryMainDto> getTerritoryMainByZoneId(@Path("zoneId") String zoneId);
}
