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
 * This class is needed to override how HashMaps are serialized to JSON by the Gson library.
 * Specifically, this needs to on-the-fly convert Fixture class objects
 * (the data maps generated for fixtures used in unit tests) back into HashMaps.
 * Without this, Fixture classes will not serialize properly, causing the final JSON body to be empty.
 */
public final class HashMapSerializer implements JsonSerializer<HashMap<String, Object>> {

    private boolean isFixture(Object object) {
        return object.getClass().getName().startsWith("com.easypost.utils.Fixture");
    }

    private boolean isPrimitive(Object object) {
        if (object == null) {
            return true;
        }
        return object instanceof String || object instanceof Integer || object instanceof Boolean ||
                object instanceof Double;
    }

    private ArrayList<Object> fixtureToArrayList(Object fixture) {
        ArrayList<Object> arrayList = (ArrayList<Object>) fixture;

        ArrayList<Object> newArrayList = new ArrayList<Object>();
        for (Object object : arrayList) {
            object = fixtureToObject(object);

            newArrayList.add(object);
        }

        return newArrayList;
    }

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

    private HashMap<String, Object> removeFixturesFromHashMap(HashMap<String, Object> hashMap) {
        HashMap<String, Object> newHashMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            /*
            if (isPrimitive(value)) {
                newHashMap.put(key, value);
                continue;
            }
             */

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

    private ArrayList<Object> removeFixturesFromArrayList(ArrayList<Object> arrayList) {
        ArrayList<Object> newArrayList = new ArrayList<>();
        for (Object object : arrayList) {

            /*
            if (isPrimitive(object)) {
                newArrayList.add(object);
                continue;
            }
             */

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
