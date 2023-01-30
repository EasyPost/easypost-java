package com.easypost.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

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
    final JsonDeserializationContext context) throws JsonParseException{
        JsonObject jo = json.getAsJsonObject();
        JsonElement results = jo.get("rates");
        Gson gson = new Gson();

        return gson.fromJson(results, StatelessRate[].class);
    }
}
