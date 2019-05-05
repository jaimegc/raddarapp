package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintCollectionDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyFootprintCollectionApiRest {

    @GET("footprints/favs")
    Call<ServerResponseCollection<MyFootprintCollectionDto>> getMyFootprintsCollection(@Query("page") int page, @Query("page_per") int pagePer);

    @PUT("footprints/{footprintId}/disfav")
    Call<ResponseDto> deleteMyFootprintCollection(@Path("footprintId") String footprintId);
}
