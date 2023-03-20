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
import java.util.Map.Entry;

public final class ErrorDeserializer implements JsonDeserializer<Error> {
    /**
     * Recursively traverse an error JSON element and its sub-element(s), and extracts all
     * error string values found into the specified string list.
     *
     * @param element the JSON element to traverse
     * @param messagesList the list of strings to append found values to
     */
    private void traverseJsonElement(JsonElement element, ArrayList<String> messagesList) {
        if (element.isJsonPrimitive()) {
            messagesList.add(element.getAsString());
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement arrayElement : array) {
                traverseJsonElement(arrayElement, messagesList);
            }
        } else if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            for (Entry<String, JsonElement> entry : object.entrySet()) {
                traverseJsonElement(entry.getValue(), messagesList);
            }
        }
    }

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
    final JsonDeserializationContext context) throws JsonParseException {
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
        ArrayList<String> messages = new ArrayList<>();

        try {
            if (errorMessage.isJsonArray()) {
                JsonArray jsonArray = errorMessage.getAsJsonArray();
                for (JsonElement arrayElement : jsonArray) {
                    traverseJsonElement(arrayElement, messages);
                }

                JsonPrimitive value = new JsonPrimitive(String.join(", ", messages));
                results.getAsJsonObject().add("message", value);
            } else if (errorMessage.isJsonObject()) {
                JsonObject object = errorMessage.getAsJsonObject();
                for (Entry<String, JsonElement> entry : object.entrySet()) {
                    traverseJsonElement(entry.getValue(), messages);
                }

                JsonPrimitive value = new JsonPrimitive(String.join(", ", messages));
                results.getAsJsonObject().add("message", value);
            }
        } catch (Exception e) {
            Error error = new Error();
            error.setMessage("Error deserializing JSON response");
            error.setCode("ERROR_DESERIALIZATION_ERROR");
            return error;
        }

        return gson.fromJson(results, Error.class);
    }
}
