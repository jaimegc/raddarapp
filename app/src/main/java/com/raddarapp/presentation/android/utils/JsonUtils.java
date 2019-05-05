package com.raddarapp.presentation.android.utils;

import com.google.gson.Gson;

public class JsonUtils {

    public String objetToJson(Object object) {
        Gson gson = new Gson();

        return gson.toJson(object);
    }
}
