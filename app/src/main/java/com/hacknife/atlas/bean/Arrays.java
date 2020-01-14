package com.hacknife.atlas.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Arrays {
    public static String toString(String[] strings) {
        Gson gson = new Gson();
        return gson.toJson(strings);
    }

    public static String[] toList(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, new TypeToken<String[]>() {
        }.getType());
    }
}
