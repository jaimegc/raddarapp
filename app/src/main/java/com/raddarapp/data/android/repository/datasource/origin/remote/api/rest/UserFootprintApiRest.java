package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UserFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserFootprintApiRest {

    @GET("footprints/{userId}/user")
    Call<ServerResponseCollection<UserFootprintDto>> getUserFootprintsByUserId(@Path("userId") String userKey, @Query("page") int page, @Query("page_per") int pagePer);
}
