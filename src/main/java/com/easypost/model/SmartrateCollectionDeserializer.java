package com.easypost.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class SmartrateCollectionDeserializer implements JsonDeserializer<SmartrateCollection> {
    /**
     * Deserialize a SmartrateCollection from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized SmartrateCollection object.
     * @throws JsonParseException if the JSON object is not a valid SmartrateCollection.
     */
    @Override
    public SmartrateCollection deserialize(final JsonElement json, final Type typeOfT,
                                           final JsonDeserializationContext context) throws JsonParseException {
        SmartrateCollection smartrateCollection = new SmartrateCollection();

        JsonObject jo = (JsonObject) json;
        JsonElement results = jo.get("result");

        if (results == null || !results.isJsonArray()) {
            return smartrateCollection;
            // return empty collection if "results" key does not exist or corresponding value is not an array
        }
        // the JsonDeserializationContext should have access to the other type adapters,
        // so we can tap into the RateDeserializer from here
        results.getAsJsonArray().forEach(rateData -> {
            smartrateCollection.addRate(context.deserialize(rateData, SmartRate.class));
        });

        return smartrateCollection;
    }
}
