package com.easypost.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.easypost.Constants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public final class StatelessRateDeserializer implements JsonDeserializer<StatelessRate[]> {
    /**
     * Deserialize a StatelessRate from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized StatelessRate object.
     * @throws JsonParseException if the JSON object is not a valid StatelessRate.
     */
    @Override
    public StatelessRate[] deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement results = jo.get("rates");

        if (results == null || !results.isJsonArray()) {
            return new StatelessRate[0];
        }

        List<StatelessRate> ratesList = new ArrayList<>();
        for (JsonElement element : results.getAsJsonArray()) {
            StatelessRate rate = Constants.Http.GSON.fromJson(element, StatelessRate.class);
            ratesList.add(rate);
        }

        return ratesList.toArray(new StatelessRate[0]);
    }
}
