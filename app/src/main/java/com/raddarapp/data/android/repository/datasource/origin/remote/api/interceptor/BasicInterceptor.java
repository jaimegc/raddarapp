package com.raddarapp.data.android.repository.datasource.origin.remote.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicInterceptor implements Interceptor {

    private static String VERSION_KEY = "version";
    private static String VERSION_VALUE = "v1";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(VERSION_KEY, VERSION_VALUE)
                .build();

        return chain.proceed(request);
    }
}
