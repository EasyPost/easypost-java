package com.easypost.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.easypost.model.Error;
import com.easypost.model.ErrorDeserializer;
import com.easypost.model.SmartrateCollection;
import com.easypost.model.SmartrateCollectionDeserializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Constant {
    public static final String API_BASE = "https://api.easypost.com";
    public static final String CHARSET = "UTF-8";
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 30000;
    public static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;
    public static final String EASYPOST_SUPPORT_EMAIL = "support@easypost.com";
    public static final ArrayList<String> GLOBAL_FIELD_ACCESSORS = new ArrayList<>(
            Arrays.asList("getCreatedAt", "getUpdatedAt", "getFees"));
    public static final Gson GSON = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(HashMap.class, new HashMapSerializer())
        .registerTypeAdapter(SmartrateCollection.class, new SmartrateCollectionDeserializer())
        .registerTypeAdapter(Error.class, new ErrorDeserializer()).create();
    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
}
