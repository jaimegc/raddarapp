package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommentApiRest {

    @GET("footprints/{footprintId}/comment")
    Call<ServerResponseCollection<CommentDto>> getCommentsByFootprintId(@Path("footprintId") String footprintId,
        @Query("page") int pageNumber, @Query("page_per") int pagePer);
}
