package com.easypost.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * This class overrides how HashMaps are serialized to JSON by the Gson library.
 *
 * Effectively every JSON body for every HTTP call will flow through this serializer.
 *
 * The reason this custom ruleset is needed is for testing purposes, to on-the-fly convert Fixture class objects
 * (the data maps generated for fixtures used in unit tests) back into HashMaps.
 * Without this, Fixture classes will not serialize properly, causing the final JSON body to be empty.
 */
public final class HashMapSerializer implements JsonSerializer<HashMap<String, Object>> {

    /**
     * Check if an object is an anonymous subclass of Fixture.
     * @param object The object to check.
     * @return True if the object is an anonymous subclass of Fixture, false otherwise.
     */
    private boolean isFixture(Object object) {
        return object.getClass().getName().startsWith("com.easypost.utils.Fixture");
    }

    /**
     * Checks if an object is a primitive type, including null.
     * @param object the object to check
     * @return true if the object is a primitive type, false otherwise
     */
    private boolean isPrimitive(Object object) {
        if (object == null) {
            return true;
        }
        return object instanceof String || object instanceof Integer || object instanceof Boolean ||
                object instanceof Double;
    }

    /**
     * Converts a Fixture class object into an ArrayList.
     * @param fixture The Fixture class object to convert.
     * @return An ArrayList representation of the Fixture class object.
     */
    @SuppressWarnings ("unchecked")
    private ArrayList<Object> fixtureToArrayList(Object fixture) {
        ArrayList<Object> arrayList = (ArrayList<Object>) fixture;

        ArrayList<Object> newArrayList = new ArrayList<Object>();
        for (Object object : arrayList) {
            object = fixtureToObject(object);

            newArrayList.add(object);
        }

        return newArrayList;
    }

    /**
     * Converts a Fixture class object into a HashMap.
     * @param fixture The Fixture class object to convert.
     * @return A HashMap representation of the Fixture class object.
     */
    @SuppressWarnings ("unchecked")
    private HashMap<String, Object> fixtureToHashMap(Object fixture) {
        Type type = fixture.getClass().getGenericSuperclass();
        Map<String, Object> fixtureMap = (Map<String, Object>) fixture;

        HashMap<String, Object> hashMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : fixtureMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            value = fixtureToObject(value);

            hashMap.put(key, value);
        }
        return hashMap;
    }

    /**
     * Converts a Fixture class object into a HashMap or ArrayList if needed.
     * @param fixture The object to analyze and potentially convert.
     * @return The converted object, or the original object if no conversion was needed.
     */
    private Object fixtureToObject(Object fixture) {
        if (isPrimitive(fixture)) {
            return fixture;
        } else if (isFixture(fixture)) {
            if (fixture instanceof ArrayList) {
                return fixtureToArrayList(fixture);
            } else {
                return fixtureToHashMap(fixture);
            }
        } else {
            return fixture;
        }
    }

    /**
     * Convert any fixtures in a hash map to their corresponding objects.
     * @param hashMap the hash map to scrub
     * @return the scrubbed hash map
     */
    @SuppressWarnings ("unchecked")
    private HashMap<String, Object> removeFixturesFromHashMap(HashMap<String, Object> hashMap) {
        HashMap<String, Object> newHashMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            value = fixtureToObject(value);

            if (value instanceof HashMap) {
                value = removeFixturesFromHashMap((HashMap<String, Object>) value);
            } else if (value instanceof ArrayList) {
                value = removeFixturesFromArrayList((ArrayList<Object>) value);
            }

            newHashMap.put(key, value);
        }
        return newHashMap;
    }

    /**
     * Convert any fixtures in an array list to their corresponding objects.
     * @param arrayList the array list to scrub
     * @return the scrubbed array list
     */
    @SuppressWarnings ("unchecked")
    private ArrayList<Object> removeFixturesFromArrayList(ArrayList<Object> arrayList) {
        ArrayList<Object> newArrayList = new ArrayList<>();
        for (Object object : arrayList) {

            object = fixtureToObject(object);

            if (object instanceof HashMap) {
                object = removeFixturesFromHashMap((HashMap<String, Object>) object);
            } else if (object instanceof ArrayList) {
                object = removeFixturesFromArrayList((ArrayList<Object>) object);
            }

            newArrayList.add(object);
        }
        return newArrayList;
    }

    @Override
    public JsonElement serialize(HashMap<String, Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        Gson gson = new Gson();

        HashMap<String, Object> cleanedHashMap = removeFixturesFromHashMap(src);

        for (Map.Entry<String, Object> entry : cleanedHashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                // don't serialize nulls
                continue;
            }

            jsonObject.add(key, gson.toJsonTree(value));
        }

        return jsonObject;
    }
}
