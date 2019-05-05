package com.raddarapp.data.general.datasource.base;


import com.raddarapp.BuildConfig;

public abstract class BaseDataSourceFactory {

    protected static final String API_FAKE = "API_FAKE";
    protected static final String API_REMOTE_PRO = "API_REMOTE_PRO";
    protected static final String API_LOCAL = "API_LOCAL";
    protected static final String API_REMOTE_DEV = "API_REMOTE_DEV";
    protected static final String API_REMOTE_QA = "API_REMOTE_QA";

    protected String getOrigin() {
        return BuildConfig.DATA_ORIGIN;
    }
}
