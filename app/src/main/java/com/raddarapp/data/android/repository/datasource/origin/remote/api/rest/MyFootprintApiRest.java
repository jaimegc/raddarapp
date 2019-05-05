package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyFootprintApiRest {

    @GET("footprints/mine")
    Call<ServerResponseCollection<MyFootprintDto>> getMyFootprintsMain(@Query("page") int pageNumber, @Query("page_per") int pagePer);

    @DELETE("footprints/{footprintId}")
    Call<ResponseDto> deleteMyFootprint(@Path("footprintId") String footprintId);
}
