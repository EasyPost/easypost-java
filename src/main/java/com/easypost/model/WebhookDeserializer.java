package com.easypost.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class WebhookDeserializer implements JsonDeserializer<Webhook[]> {
    /**
     * Deserialize a list of Webhook from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized Webhook object.
     * @throws JsonParseException if the JSON object is not a valid Webhook.
     */
    @Override
    public Webhook[] deserialize(final JsonElement json, final Type typeOfT,
    final JsonDeserializationContext context) throws JsonParseException{
        JsonObject jo = json.getAsJsonObject();
        JsonElement results = jo.get("webhooks");
        Gson gson = new Gson();

        return gson.fromJson(results, Webhook[].class);
    }
}
