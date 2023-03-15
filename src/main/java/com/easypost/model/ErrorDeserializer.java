package com.easypost.model;

import com.easypost.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ErrorDeserializer implements JsonDeserializer<Error> {
    /**
     * Deserialize an Error from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized Error object.
     * @throws JsonParseException if the JSON object is not a valid SmartrateCollection.
     */
    @Override
    public Error deserialize(final JsonElement json, final Type typeOfT,
    final JsonDeserializationContext context) throws JsonParseException{
        JsonObject jo = json.getAsJsonObject();
        JsonElement results = jo.get("error");
        Gson gson = new Gson();

        if (results == null) {
            Error error = new Error();
            error.setMessage(Constants.ErrorMessages.API_DID_NOT_RETURN_ERROR_DETAILS);
            error.setCode("NO RESPONSE CODE");
            return error;
        }

        JsonElement errorMessage = results.getAsJsonObject().get("message");
        if (errorMessage.isJsonArray() || errorMessage.isJsonObject()) {
            JsonArray jsonArray = errorMessage.isJsonArray() ? errorMessage.getAsJsonArray() : errorMessage
                    .getAsJsonObject().get("errors").getAsJsonArray();
            ArrayList<String> messages = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                messages.add(jsonArray.get(i).getAsString());
            }

            JsonPrimitive value = new JsonPrimitive(String.join(", ", messages));
            results.getAsJsonObject().add("message", value);
        }

        return gson.fromJson(results, Error.class);
    }
}
