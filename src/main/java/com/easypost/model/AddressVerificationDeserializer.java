package com.easypost.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class AddressVerificationDeserializer implements JsonDeserializer<AddressVerification> {
    /**
     * Deserialize an AddressVerification from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized AddressVerification object.
     * @throws JsonParseException if the JSON object is not a valid SmartrateCollection.
     */
    @Override
    public AddressVerification deserialize(final JsonElement json, final Type typeOfT,
    final JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();

        AddressVerification addressVerification = new AddressVerification();

        boolean success = jo.get("success").getAsBoolean();
        addressVerification.setSuccess(success);

        AddressDetail details = context.deserialize(jo.get("details"), AddressDetail.class);
        addressVerification.setDetails(details);

        JsonElement errorsAsJson = jo.get("errors");

        if (errorsAsJson != null) {
            JsonArray errorsAsArray = errorsAsJson.getAsJsonArray();
            ArrayList<AddressVerificationFieldError> errors = new ArrayList<>();
            for (JsonElement errorAsJson : errorsAsArray) {
                JsonObject errorAsJsonObject = errorAsJson.getAsJsonObject();

                AddressVerificationFieldError error = new AddressVerificationFieldError();

                JsonElement code = errorAsJsonObject.get("code");
                if (code != null) {
                    error.setCode(code.getAsString());
                }

                JsonElement message = errorAsJsonObject.get("message");
                if (message != null) {
                    error.setMessage(message.getAsString());
                }

                JsonElement field = errorAsJsonObject.get("field");
                if (field != null) {
                    error.setField(field.getAsString());
                }

                JsonElement suggestion = errorAsJsonObject.get("suggestion");
                if (suggestion != null && !suggestion.isJsonNull()) {
                    error.setSuggestion(suggestion.getAsString());
                }

                errors.add(error);
            }
            addressVerification.setErrors(errors);
        }

        return addressVerification;
    }
}
