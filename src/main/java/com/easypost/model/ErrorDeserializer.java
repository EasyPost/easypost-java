package com.easypost.model;

import com.easypost.Constants;
import com.easypost.exception.APIException;
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
import java.util.List;
import java.util.Map.Entry;

public final class ErrorDeserializer implements JsonDeserializer<APIException> {
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
     * Deserialize an APIException from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param typeOfT Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized APIException object.
     * @throws JsonParseException if the JSON object is not a valid SmartrateCollection.
     */
    @Override
    public APIException deserialize(final JsonElement json, final Type typeOfT,
                                       final JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();

        String message = null;
        String code = null;
        List<Object> errors = new ArrayList<>();

        JsonElement errorResponse = jo.get("error");
        if (errorResponse == null) {
            message = Constants.ErrorMessages.API_DID_NOT_RETURN_ERROR_DETAILS;
            code = "NO RESPONSE CODE";
            return new APIException(message, code, null);
        }
        JsonObject errorData = errorResponse.getAsJsonObject();

        JsonElement codeElement = errorData.get("code");
        if (codeElement != null) {
            code = codeElement.getAsString();
        }

        JsonElement messageElement = errorData.get("message");
        if (messageElement != null) {
            if (messageElement.isJsonPrimitive()) {
                message = messageElement.getAsString();
            } else if (messageElement.isJsonObject() || messageElement.isJsonArray()) {
                ArrayList<String> messagesList = new ArrayList<>();
                traverseJsonElement(messageElement, messagesList);
                message = String.join(", ", messagesList);
            } else {
                throw new JsonParseException("Invalid message format");
            }
        }

        JsonElement errorsAsJson = errorData.get("errors");
        List<Object> errorList = new ArrayList<>();
        if (errorsAsJson != null) {
            JsonArray errorsAsArray = errorsAsJson.getAsJsonArray();
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

                    errorList.add(fieldError);
                } else {
                    errorList.add(errorAsJson.getAsString());
                }
            }
        }

        return new APIException(message, code, errorList);
    }
}
