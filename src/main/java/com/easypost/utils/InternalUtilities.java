package com.easypost.utils;

import com.easypost.exception.API.EncodingError;

import java.net.URLEncoder;

import java.util.Map;

public abstract class InternalUtilities {
    /**
     * Create Encoded URL from a Map.
     *
     * @param params    Map of parameters to be encoded.
     * @param parentKey Parent key in the encoded URL.
     * @return Encoded URL for Stripe API call.
     * @throws EncodingError when the URL encoding fails.
     */
    public static String getEncodedURL(Map<String, String> params, String parentKey) throws EncodingError {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    result.append("&");
                }

                result.append(URLEncoder.encode(parentKey + "[" + entry.getKey() + "]", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        } catch (Exception e) {
            throw new EncodingError("Something went wrong during the URL encoding.");
        }

        return result.toString();
    }

    /**
     * Extract a value from a Map, or return a default value if the key is not present.
     * @param map Map to extract from
     * @param key Key to extract
     * @param defaultValue Default value to return if the key is not present
     * @return Value from the map, or the default value if the key is not present
     * @param <TElement> Type of the value to extract
     */
    @SuppressWarnings("unchecked")
    public static <TElement> TElement getOrDefault(Map<String, Object> map, String key, TElement defaultValue) {
        // The built-in Map<String, Object>.getOrDefault() method extracts a value of type Object,
        // so this handles the cast.
        return (TElement) map.getOrDefault(key, defaultValue);
    }
}
