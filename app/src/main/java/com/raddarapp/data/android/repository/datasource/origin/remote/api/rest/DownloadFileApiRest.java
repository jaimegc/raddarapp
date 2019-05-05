package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DownloadFileApiRest {

    @GET("/{url}")
    Call<ResponseBody> downloadFileByUrl(@Path("url") String url);
}
