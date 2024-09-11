package com.easypost.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {
    private static final String[] DATE_FORMATS = new String[]{
        // EasyPost API returns below date formats
        "yyyy-MM-dd'T'HH:mm:ssX",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd",
    };
    
    /**
     * Deserialize the Date format from a JSON object.
     *
     * @param json    JSON object to deserialize.
     * @param type    Type of the object to deserialize.
     * @param context Deserialization context.
     * @return Deserialized Date object.
     * @throws JsonParseException if the JSON object is not a valid SmartrateCollection.
     */
    @Override
    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context)
        throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                return sdf.parse(json.getAsString());
            } catch (ParseException e) {
                // Continue to try the next format
            }
        }

        // Throw an exception if no formats matched from the DATE_FORMATS
        throw new JsonParseException("Unparseable date: \"" + json.getAsString() + "\"");
    }
}
