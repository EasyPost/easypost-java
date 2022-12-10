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
     * Get the URL for the instance object.
     * 
     * @param clazz The class of the instance object.
     * @param id    The id of the object.
     * @return The string of instance object name and its id.
     */
    public static String instanceURL(final Class<?> clazz, final String id) {
        return String.format("%s/%s", classURL(clazz), id);
    }

    /**
     * Get the URL for the class.
     *
     * @param clazz The class of the URL.
     * @return The string of the class name.
     */
    public static String classURL(final Class<?> clazz) {
        String singleURL = singleClassURL(clazz);
        if (singleURL.charAt(singleURL.length() - 1) == 's' || singleURL.charAt(singleURL.length() - 1) == 'h') {
            return String.format("%ses", singleClassURL(clazz));
        } else {
            return String.format("%ss", singleClassURL(clazz));
        }
    }

    /**
     * Get the URL for this class with the API base url.
     *
     * @param clazz The class name.
     * @return String that has API base and class name.
     */
    private static String singleClassURL(final Class<?> clazz) {
        return String.format("%s/%s", "%s/%s", className(clazz));
    }

    /**
     * Get the class name from the given parameter.
     *
     * @param clazz The class name.
     * @return String of class name.
     */
    private static String className(final Class<?> clazz) {
        return clazz.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase().replace("$", "");
    }
}
