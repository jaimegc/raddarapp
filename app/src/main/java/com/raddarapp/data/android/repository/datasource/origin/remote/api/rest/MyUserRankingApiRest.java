package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.MyUserRankingServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyUserRankingApiRest {

    @GET("user_rankings/{territoryId}/zone")
    Call<MyUserRankingServerResponseCollection<MyUserRankingDto>> getMyUsersRankingByTerritoryId(@Path("territoryId") String territoryId, @Query("page") int pageNumber, @Query("page_per") int pagePer);
}
