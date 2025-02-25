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
import java.util.List;

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

        Error error = new Error();

        JsonElement errorResponse = jo.get("error");
        if (errorResponse == null) {
            error.setMessage(Constants.ErrorMessages.API_DID_NOT_RETURN_ERROR_DETAILS);
            error.setCode("NO RESPONSE CODE");
            return error;
        }
        JsonObject errorData = errorResponse.getAsJsonObject();

        JsonElement code = errorData.get("code");
        if (code != null) {
            error.setCode(code.getAsString());
        }

        JsonElement message = errorData.get("message");
        if (message != null) {
            if (message.isJsonPrimitive()) {
                error.setMessage(message.getAsString());
            } else if (message.isJsonObject() || message.isJsonArray()) {
                ArrayList<String> messagesList = new ArrayList<>();
                traverseJsonElement(message, messagesList);
                error.setMessage(String.join(", ", messagesList));
            } else {
                throw new JsonParseException("Invalid message format");
            }
        }

        JsonElement errorsAsJson = errorData.get("errors");
if (errorsAsJson != null) {
            JsonArray errorsAsArray = errorsAsJson.getAsJsonArray();
            List<Object> errors = new ArrayList<>();
            for (JsonElement errorAsJson : errorsAsArray) {
                if (errorAsJson.isJsonObject()) {
                    JsonObject errorAsJsonObject = errorAsJson.getAsJsonObject();
                    FieldError fieldError = new FieldError();

                    JsonElement field = errorAsJsonObject.get("field");
                    if (field != null) {
                        fieldError.setField(field.getAsString());
                    }

                    JsonElement fieldMessage = errorAsJsonObject.get("message");
                    if (fieldMessage != null) {
                        fieldError.setMessage(fieldMessage.getAsString());
                    }

                    JsonElement suggestion = errorAsJsonObject.get("suggestion");
                    if (suggestion != null && !suggestion.isJsonNull()) {
                        fieldError.setSuggestion(suggestion.getAsString());
                    }

                    errors.add(fieldError);
                } else if (errorAsJson.isJsonPrimitive() && errorAsJson.getAsJsonPrimitive().isString()) {
                    errors.add(errorAsJson.getAsString());
                }
            }

            if (!errors.isEmpty() && errors.get(0) instanceof FieldError) {
                error.setErrors(FieldErrorOrStringList.fromErrorList((List<FieldError>) (List<?>) errors));
            } else if (!errors.isEmpty() && errors.get(0) instanceof String) {
                error.setErrors(FieldErrorOrStringList.fromStringList((List<String>) (List<?>) errors));
            }
        }

        return error;
    }
}
