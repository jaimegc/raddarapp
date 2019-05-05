package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CreateCommentApiRest {

    @POST("footprints/{footprintId}/comment")
    Call<CreateCommentDto> createComment(@Path("footprintId") String footprintId, @Body CreateCommentDto createCommentDto);
}
