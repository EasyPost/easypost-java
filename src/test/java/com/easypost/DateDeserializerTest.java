package com.easypost;

import com.easypost.model.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateDeserializerTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

    /**
     * Get hour in UTC time zone.
     *
     * @param date The input date object.
     * @return int of hour in UTC time zone.
     */
    public static int getUtcHour(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get date of the month in UTC time zone.
     *
     * @param date The input date object.
     * @return int of date in UTC time zone.
     */
    public static int getUtcDate(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Test parsing a date format (yyyy-MM-dd'T'HH:mm:ssX).
     *
     */
    @Test
    public void testDeserializeIso8601WithTimezone() {
        String json = "\"2024-09-11T10:15:30Z\"";
        Date date = gson.fromJson(json, Date.class);

        assertEquals(8, date.getMonth()); // .getMonth() returns between 0 and 11, with the value 0 representing January
        assertEquals(11, date.getDate());
        assertEquals(10, getUtcHour(date));
        assertEquals(15, date.getMinutes());
        assertEquals(30, date.getSeconds());
    }

    /**
     * Test parsing a date format (yyyy-MM-dd'T'HH:mm:ss).
     *
     */
    @Test
    public void testDeserializeIso8601WithoutTimezone() {
        String json = "\"2024-09-11T10:15:30\"";
        Date date = gson.fromJson(json, Date.class);

        assertEquals(8, date.getMonth());
        assertEquals(11, date.getDate());
        assertEquals(10, getUtcHour(date));
        assertEquals(15, date.getMinutes());
        assertEquals(30, date.getSeconds());
    }

    /**
     * Test parsing a regular date format (yyyy-MM-dd).
     *
     */
    @Test
    public void testDeserializeRegularDateFormat() {
        String json = "\"2024-09-11\"";
        Date date = gson.fromJson(json, Date.class);

        assertEquals(8, date.getMonth());
        assertEquals(11, getUtcDate(date));
        assertEquals(0, getUtcHour(date));
        assertEquals(0, date.getMinutes());
        assertEquals(0, date.getSeconds());
    }
}
