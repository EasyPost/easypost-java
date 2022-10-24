package com.easypost.http;

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
 */
public final class HashMapSerializer implements JsonSerializer<HashMap<String, Object>> {

    /**
     * Clean a hash map.
     * @param hashMap the hash map to scrub
     * @return the scrubbed hash map
     */
    @SuppressWarnings ("unchecked")
    private HashMap<String, Object> cleanHashMap(HashMap<String, Object> hashMap) {
        HashMap<String, Object> newHashMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof HashMap) {
                value = cleanHashMap((HashMap<String, Object>) value);
            } else if (value instanceof ArrayList) {
                value = cleanArrayList((ArrayList<Object>) value);
            }

            newHashMap.put(key, value);
        }
        return newHashMap;
    }

    /**
     * Clean an array list.
     * @param arrayList the array list to scrub
     * @return the scrubbed array list
     */
    @SuppressWarnings ("unchecked")
    private ArrayList<Object> cleanArrayList(ArrayList<Object> arrayList) {
        ArrayList<Object> newArrayList = new ArrayList<>();
        for (Object object : arrayList) {

            if (object instanceof HashMap) {
                object = cleanHashMap((HashMap<String, Object>) object);
            } else if (object instanceof ArrayList) {
                object = cleanArrayList((ArrayList<Object>) object);
            }

            newArrayList.add(object);
        }
        return newArrayList;
    }

    @Override
    public JsonElement serialize(HashMap<String, Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        Gson gson = new Gson();

        HashMap<String, Object> cleanedHashMap = cleanHashMap(src);

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
