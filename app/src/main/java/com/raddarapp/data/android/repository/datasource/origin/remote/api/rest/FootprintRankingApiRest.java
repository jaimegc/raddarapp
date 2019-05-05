package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootprintRankingApiRest {

    @GET("footprints/ranking/{zoneId}/zone")
    Call<ServerResponseCollection<FootprintRankingDto>>
        getFootprintsRankingByZoneKey(@Path("zoneId") String zoneId, @Query("page") int page, @Query("page_per") int pagePer);
}
