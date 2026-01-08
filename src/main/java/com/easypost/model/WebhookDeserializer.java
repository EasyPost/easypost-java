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
            final JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement results = jo.get("webhooks");

        if (results == null || !results.isJsonArray()) {
            return new Webhook[0];
        }

        List<Webhook> webhooksList = new ArrayList<>();
        for (JsonElement element : results.getAsJsonArray()) {
            Webhook webhook = Constants.Http.GSON.fromJson(element, Webhook.class);
            webhooksList.add(webhook);
        }

        return webhooksList.toArray(new Webhook[0]);
    }
}
