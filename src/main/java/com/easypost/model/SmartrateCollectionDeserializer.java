package com.easypost.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SmartrateCollectionDeserializer implements JsonDeserializer<SmartrateCollection> {
    @Override
    public SmartrateCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SmartrateCollection smartrateCollection = new SmartrateCollection();

        JsonObject jo = (JsonObject) json;
        JsonElement results = jo.get("result");

        if (results == null || !results.isJsonArray()) {
            return smartrateCollection;  // return empty collection if "results" key does not exist or corresponding value is not an array
        }

        // the JsonDeserializationContext should have access to the other type adapters, so we can tap into the RateDeserializer from here
        results.getAsJsonArray().forEach(rateData -> {
            smartrateCollection.addRate(context.deserialize(rateData, Rate.class));
        });

        return smartrateCollection;
    }
}
