package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintMainDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.AddVoteDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.FootprintServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootprintMainApiRest {

    @POST("footprints/near_outside")
    Call<FootprintServerResponseCollection<FootprintMainDto>> getFootprintsMain(@Body RaddarLocationDto raddarLocationDto, @Query("page") int pageNumber, @Query("page_per") int pagePer);

    @GET("footprintsByUserId")
    Call<ServerResponseCollection<FootprintMainDto>> getFootprintsMainByUserId(@Query("id") String userKey, @Query("page") int page, @Query("page_per") int pagePer);

    @PUT("footprints/{footprintId}/vote/{vote}")
    Call<AddVoteDto> addVote(@Path("footprintId") String footprintId, @Path("vote") int addVoteType);
}
