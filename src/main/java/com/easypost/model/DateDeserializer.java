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
        // Basic formats
        "yyyy-MM-dd",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd'T'HH:mm:ss'Z'",

        // With milliseconds
        "yyyy-MM-dd'T'HH:mm:ss.SSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",

        // With time zone offset
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ssXXX",

        // Alternative time formats
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm:ss.SSS",

        // Month and day only
        "yyyy-MM-dd",
        "MM/dd/yyyy",
        "MM-dd-yyyy",

        // Year and week
        "yyyy-'W'ww",
        "yyyy-'W'ww-u",

        // Full date and time
        "EEE, d MMM yyyy HH:mm:ss Z",
        "EEE, d MMM yyyy HH:mm:ss z",
        "EEEE, MMMM d, yyyy h:mm:ss a",

        // Short formats
        "M/d/yy",
        "M-d-yy",
        "M.d.yy",
        "MM/dd/yyyy",
        "MM-dd-yyyy"
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
                throw new JsonParseException("Unable to parse this date format");
            }
        }
        throw new JsonParseException("Unparseable date: \"" + json.getAsString() + "\"");
    }
}
