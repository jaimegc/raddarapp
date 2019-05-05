package com.raddarapp.data.android.repository.datasource.origin.remote.api.interceptor;

import com.raddarapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    private static final String VERSION_KEY = "version";
    private static final String VERSION_VALUE = "v1";
    private static final String AUTHORIZATION_KEY = "raddar-api-authorization";
    private static final String AUTHORIZATION_VALUE = BuildConfig.RADDAR_API_AUTHORIZATION;
    private String token;

    public AuthInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(AUTHORIZATION, BEARER + " " + token)
                .addHeader(VERSION_KEY, VERSION_VALUE)
                .addHeader(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .build();

        return chain.proceed(request);
    }
}
