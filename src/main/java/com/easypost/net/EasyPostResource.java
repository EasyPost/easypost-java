/**
 * EasyPostResource.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.net;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.model.EventDeserializer;
import com.easypost.model.Fee;
import com.easypost.model.Rate;
import com.easypost.model.RateDeserializer;
import com.easypost.model.Shipment;
import com.easypost.model.SmartrateCollection;
import com.easypost.model.SmartrateCollectionDeserializer;
import com.easypost.model.TrackingDetail;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class EasyPostResource {
    protected enum RequestMethod {
        GET,
        POST,
        DELETE,
        PUT
    }

    public static final String EASYPOST_SUPPORT_EMAIL = "support@easypost.com";
    public static final Gson GSON =
            new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(HashMap.class, new HashMapSerializer())
                    .registerTypeAdapter(Event.class, new EventDeserializer())
                    .registerTypeAdapter(Rate.class, new RateDeserializer())
                    .registerTypeAdapter(SmartrateCollection.class, new SmartrateCollectionDeserializer()).create();
    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Event.class, new EventDeserializer()).create();
    public static final String CHARSET = "UTF-8";
    public static final ArrayList<String> GLOBAL_FIELD_ACCESSORS =
            new ArrayList<>(Arrays.asList("getCreatedAt", "getUpdatedAt", "getFees"));
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 30000;
    public static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;
    public static final double DEFAULT_APP_ENGINE_TIMEOUT_SECONDS = 20.0;
    private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";
    private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.easypost.net.customURLStreamHandler";
    private static int connectTimeoutMilliseconds = DEFAULT_CONNECT_TIMEOUT_MILLISECONDS;
    private static int readTimeoutMilliseconds = DEFAULT_READ_TIMEOUT_MILLISECONDS;
    private static double appEngineTimeoutSeconds = DEFAULT_APP_ENGINE_TIMEOUT_SECONDS;
    private Date createdAt;
    private Date updatedAt;
    private ArrayList<Fee> fees;

    /**
     * Get the timeout in milliseconds for App Engine API requests.
     *
     * @return the timeout in milliseconds
     */
    public static double getAppEngineTimeoutSeconds() {
        return appEngineTimeoutSeconds;
    }

    /**
     * Set the timeout in seconds for App Engine API requests.
     *
     * @param seconds the timeout in seconds
     */
    public static void setAppEngineTimeoutSeconds(double seconds) {
        appEngineTimeoutSeconds = seconds;
    }

    /**
     * Get the timeout in milliseconds for connecting to the API.
     *
     * @return the timeout in milliseconds
     */
    public static int getConnectTimeoutMilliseconds() {
        return connectTimeoutMilliseconds;
    }

    /**
     * Set the timeout in milliseconds for connecting to the API.
     *
     * @param milliseconds the timeout in milliseconds
     */
    public static void setConnectTimeoutMilliseconds(int milliseconds) {
        connectTimeoutMilliseconds = milliseconds;
    }

    /**
     * @return the Date this object was created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the Date this object was created.
     *
     * @param createdAt the Date this object was created
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the Fees associated with this object
     */
    public ArrayList<Fee> getFees() {
        return fees;
    }

    /**
     * Set the Fees associated with this object.
     *
     * @param fees the Fees associated with this object
     */
    public void setFees(final ArrayList<Fee> fees) {
        this.fees = fees;
    }

    /**
     * @return the ID of this object
     */
    public String getId() {
        return "";
    }

    /**
     * @return the URL of the label for this object
     */
    public String getLabelUrl() {
        return "";
    }

    /**
     * @return the API mode used to create this object
     */
    public String getMode() {
        return "";
    }

    /**
     * Get the timeout in milliseconds for reading API responses.
     *
     * @return the timeout in milliseconds
     */
    public static int getReadTimeoutMilliseconds() {
        return readTimeoutMilliseconds;
    }

    /**
     * Set the timeout in milliseconds for reading API responses.
     *
     * @param milliseconds the timeout in milliseconds
     */
    public static void setReadTimeoutMilliseconds(int milliseconds) {
        readTimeoutMilliseconds = milliseconds;
    }

    /**
     * @return the ID of this shipment
     */
    public String getShipmentId() {
        return "";
    }

    /**
     * @return the list of shipments in this batch
     */
    public List<Shipment> getShipments() {
        return new ArrayList<Shipment>();
    }

    /**
     * @return the status of this object
     */
    public String getStatus() {
        return "";
    }

    /**
     * @return the tracking code of this shipment
     */
    public String getTrackingCode() {
        return "";
    }

    /**
     * @return the tracking details of this shipment
     */
    public List<TrackingDetail> getTrackingDetails() {
        return new ArrayList<TrackingDetail>();
    }

    /**
     * @return the Date this object was last updated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Set the Date this object was last updated.
     *
     * @param updatedAt the Date this object was last updated
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected static String instanceURL(final Class<?> clazz, final String id) {
        return String.format("%s/%s", classURL(clazz), id);
    }

    protected static String classURL(final Class<?> clazz) {
        String singleURL = singleClassURL(clazz);
        if (singleURL.charAt(singleURL.length() - 1) == 's' || singleURL.charAt(singleURL.length() - 1) == 'h') {
            return String.format("%ses", singleClassURL(clazz));
        } else {
            return String.format("%ss", singleClassURL(clazz));
        }
    }

    protected static String singleClassURL(final Class<?> clazz) {
        return String.format("%s/%s", EasyPost.API_BASE, className(clazz));
    }

    private static String className(final Class<?> clazz) {
        return clazz.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase().replace("$", "");

    }

    private static String urlEncodePair(final String key, final String value) throws UnsupportedEncodingException {
        return String.format("%s=%s", URLEncoder.encode(key, CHARSET), URLEncoder.encode(value, CHARSET));
    }

    static Map<String, String> getHeaders(String apiKey) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Charset", CHARSET);
        headers.put("User-Agent", String.format("EasyPost/v2 JavaClient/%s Java/%s OS/%s OSVersion/%s OSArch/%s " +
                "Implementation/%s", EasyPost.VERSION, System.getProperty("java.version"),
                convertSpaceToHyphen(System.getProperty("os.name")), System.getProperty("os.version"),
                convertSpaceToHyphen(System.getProperty("os.arch")),
                convertSpaceToHyphen(System.getProperties().getProperty("java.vm.name"))));

        if (apiKey == null) {
            apiKey = EasyPost.apiKey;
        }

        headers.put("Authorization", String.format("Bearer %s", apiKey));

        return headers;
    }

    private static String convertSpaceToHyphen(String string) {
        return string.replace(' ', '-');
    }

    private static javax.net.ssl.HttpsURLConnection createEasyPostConnection(final String url, final String apiKey,
                                                                             final String method) throws IOException {
        HttpsURLConnection conn = null;
        String customURLStreamHandlerClassName = System.getProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
        if (customURLStreamHandlerClassName != null) {
            // instantiate the custom handler provided
            try {
                @SuppressWarnings ("unchecked") Class<URLStreamHandler> clazz =
                        (Class<URLStreamHandler>) Class.forName(customURLStreamHandlerClassName);
                Constructor<URLStreamHandler> constructor = clazz.getConstructor();
                URLStreamHandler customHandler = constructor.newInstance();
                URL urlObj = new URL(null, url, customHandler);
                conn = (javax.net.ssl.HttpsURLConnection) urlObj.openConnection();
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            } catch (SecurityException e) {
                throw new IOException(e);
            } catch (NoSuchMethodException e) {
                throw new IOException(e);
            } catch (IllegalArgumentException e) {
                throw new IOException(e);
            } catch (InstantiationException e) {
                throw new IOException(e);
            } catch (IllegalAccessException e) {
                throw new IOException(e);
            } catch (InvocationTargetException e) {
                throw new IOException(e);
            }
        } else if (EasyPost._vcr != null) {
            try {
                conn = EasyPost._vcr.getHttpUrlConnection(url).openConnectionSecure();
            } catch (Exception vcrException) {
                throw new IOException(vcrException);
            }
        } else {
            URL urlObj = new URL(null, url);
            conn = (javax.net.ssl.HttpsURLConnection) urlObj.openConnection();
        }
        conn.setConnectTimeout(getConnectTimeoutMilliseconds());
        conn.setRequestMethod(method);

        int readTimeout;
        if (EasyPost.readTimeout != 0) {
            readTimeout = EasyPost.readTimeout;
        } else {
            readTimeout = getReadTimeoutMilliseconds();
        }
        conn.setReadTimeout(readTimeout);

        conn.setUseCaches(false);
        for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
            conn.setRequestProperty(header.getKey(), header.getValue());
        }

        return conn;
    }

    private static javax.net.ssl.HttpsURLConnection writeBody(final javax.net.ssl.HttpsURLConnection conn,
                                                              final JsonObject body) throws IOException {
        if (body != null) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream output = null;
            try {
                output = conn.getOutputStream();
                String jsonString = body.toString();
                output.write(jsonString.getBytes(CHARSET));
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        }
        return conn;
    }

    private static javax.net.ssl.HttpsURLConnection createGetConnection(final String url, final String query,
                                                                        final String apiKey) throws IOException {
        String getURL = url;
        if (query != null) {
            getURL = String.format("%s?%s", url, query);
        }
        javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(getURL, apiKey, "GET");
        return conn;
    }

    private static javax.net.ssl.HttpsURLConnection createPostConnection(final String url, final JsonObject body,
                                                                         final String apiKey) throws IOException {
        javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(url, apiKey, "POST");
        conn = writeBody(conn, body);
        return conn;
    }

    private static javax.net.ssl.HttpsURLConnection createDeleteConnection(final String url, final String query,
                                                                           final String apiKey) throws IOException {
        String deleteUrl = url;
        if (query != null) {
            deleteUrl = String.format("%s?%s", url, query);
        }
        javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(deleteUrl, apiKey, "DELETE");
        return conn;
    }

    private static javax.net.ssl.HttpsURLConnection createPutConnection(final String url, final JsonObject body,
                                                                        final String apiKey) throws IOException {
        javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(url, apiKey, "PUT");
        conn = writeBody(conn, body);
        return conn;
    }

    private static JsonObject createBody(final Map<String, Object> params) {
        // this is a hack to fix a broken concept: https://github.com/google/gson/issues/1080
        //noinspection rawtypes,unchecked
        JsonElement jsonElement = GSON.toJsonTree(new HashMap(params));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject;
    }

    private static String createQuery(final Map<String, Object> params) throws UnsupportedEncodingException {
        Map<String, String> flatParams = flattenParams(params);
        StringBuilder queryStringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : flatParams.entrySet()) {
            queryStringBuilder.append("&");
            queryStringBuilder.append(urlEncodePair(entry.getKey(), entry.getValue()));
        }
        if (queryStringBuilder.length() > 0) {
            queryStringBuilder.deleteCharAt(0);
        }
        return queryStringBuilder.toString();
    }

    private static Map<String, String> flattenParams(final Map<String, Object> params) {
        if (params == null) {
            return new HashMap<String, String>();
        }
        Map<String, String> flatParams = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map<?, ?>) {
                Map<String, Object> flatNestedMap = new HashMap<String, Object>();
                Map<?, ?> nestedMap = (Map<?, ?>) value;
                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    flatNestedMap.put(String.format("%s[%s]", key, nestedEntry.getKey()), nestedEntry.getValue());
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if (value instanceof List) {
                Map<String, Object> flatNestedMap = new HashMap<String, Object>();
                List<?> nestedList = (List<?>) value;
                for (int i = 0; i < nestedList.size(); i++) {
                    flatNestedMap.put(String.format("%s[%s]", key, i), nestedList.get(i));
                    flatParams.putAll(flattenParams(flatNestedMap));
                }
            } else if (value instanceof EasyPostResource) {
                flatParams.put(String.format("%s[%s]", key, "id"), value.toString());

            } else if (value != null) {
                flatParams.put(key, value.toString());
            }
        }

        return flatParams;
    }

    private static String getResponseBody(final InputStream responseStream) throws IOException {
        @SuppressWarnings ("resource") String rBody = new Scanner(responseStream, CHARSET).useDelimiter("\\A").next();
        responseStream.close();
        return rBody;
    }

    private static EasyPostResponse makeURLConnectionRequest(final EasyPostResource.RequestMethod method,
                                                             final String url, final String query,
                                                             final JsonObject body, final String apiKey)
            throws EasyPostException {
        javax.net.ssl.HttpsURLConnection conn = null;
        try {
            switch (method) {
                case GET:
                    conn = createGetConnection(url, query, apiKey);
                    break;
                case POST:
                    conn = createPostConnection(url, body, apiKey);
                    break;
                case PUT:
                    conn = createPutConnection(url, body, apiKey);
                    break;
                case DELETE:
                    conn = createDeleteConnection(url, query, apiKey);
                    break;
                default:
                    throw new EasyPostException(
                            String.format("Unrecognized HTTP method %s. Please contact EasyPost at %s.", method,
                                    EasyPostResource.EASYPOST_SUPPORT_EMAIL));
            }
            conn.connect(); // This line is crucial for getting VCR to work
            // (triggers internal pre-request processing needed for VCR)
            int rCode = conn.getResponseCode(); // sends the request
            String rBody = null;
            if (rCode == HttpURLConnection.HTTP_NO_CONTENT) {
                rBody = "";
            } else if (rCode >= HttpURLConnection.HTTP_OK && rCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                rBody = getResponseBody(conn.getInputStream());
            } else {
                rBody = getResponseBody(conn.getErrorStream());
            }
            return new EasyPostResponse(rCode, rBody);
        } catch (IOException e) {
            throw new EasyPostException(String.format("Could not connect to EasyPost (%s). " +
                    "Please check your internet connection and try again. If this problem persists," +
                    "please contact us at %s.", EasyPost.API_BASE, EasyPostResource.EASYPOST_SUPPORT_EMAIL), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    protected static <T> T request(final EasyPostResource.RequestMethod method, final String url,
                                   final Map<String, Object> params, final Class<T> clazz, final String apiKey)
            throws EasyPostException {
        return request(method, url, params, clazz, apiKey, true);
    }

    protected static <T> T request(final EasyPostResource.RequestMethod method, final String url,
                                   final Map<String, Object> params, final Class<T> clazz, final String apiKey,
                                   final boolean apiKeyRequired) throws EasyPostException {
        String originalDNSCacheTTL = null;
        boolean allowedToSetTTL = true;
        try {
            originalDNSCacheTTL = java.security.Security.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
            // disable DNS cache
            java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
        } catch (SecurityException se) {
            allowedToSetTTL = false;
        }

        try {
            return _request(method, url, params, clazz, apiKey, apiKeyRequired);
        } finally {
            if (allowedToSetTTL) {
                if (originalDNSCacheTTL == null) {
                    // value unspecified by implementation
                    java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "-1"); // cache forever
                } else {
                    java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
                }
            }
        }
    }

    @SuppressWarnings ("checkstyle:methodname")
    protected static <T> T _request(final EasyPostResource.RequestMethod method, final String url,
                                    final Map<String, Object> params, final Class<T> clazz, String apiKey,
                                    final boolean apiKeyRequired) throws EasyPostException {
        if ((EasyPost.apiKey == null || EasyPost.apiKey.length() == 0) && (apiKey == null || apiKey.length() == 0)) {
            if (apiKeyRequired) {
                throw new EasyPostException(String.format(
                        "No API key provided. (set your API key using 'EasyPost.apiKey = {KEY}'. " +
                                "Your API key can be found in your EasyPost dashboard, " +
                                "or you can email us at %s for assistance.", EasyPostResource.EASYPOST_SUPPORT_EMAIL));

            }
        }

        if (apiKey == null) {
            apiKey = EasyPost.apiKey;
        }

        String query = null;
        JsonObject body = null;
        if (params != null) {
            switch (method) {
                case GET:
                case DELETE:
                    try {
                        query = createQuery(params);
                    } catch (UnsupportedEncodingException e) {
                        throw new EasyPostException(
                                String.format("Unable to encode parameters to %s. Please email %s for assistance.",
                                        CHARSET, EasyPostResource.EASYPOST_SUPPORT_EMAIL), e);
                    }
                    break;
                case POST:
                case PUT:
                    try {
                        body = createBody(params);
                    } catch (Exception e) {
                        throw new EasyPostException(String.format(
                                "Unable to create JSON body from parameters. Please email %s for assistance.",
                                EasyPostResource.EASYPOST_SUPPORT_EMAIL), e);
                    }
                    break;
                default:
                    break;
            }
        }


        EasyPostResponse response;
        try {
            // HTTPSURLConnection verifies SSL cert by default
            response = makeURLConnectionRequest(method, url, query, body, apiKey);
        } catch (ClassCastException ce) {
            // appengine
            String appEngineEnv = System.getProperty("com.google.appengine.runtime.environment", null);
            if (appEngineEnv != null) {
                response = makeAppEngineRequest(method, url, query, body, apiKey);
            } else {
                throw ce;
            }
        }
        int rCode = response.getResponseCode();
        String rBody = response.getResponseBody();
        if (rCode < HttpURLConnection.HTTP_OK || rCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
            handleAPIError(rBody, rCode);
        }

        return GSON.fromJson(rBody, clazz);
    }

    private static void handleAPIError(final String rBody, final int rCode) throws EasyPostException {
        try {
            EasyPostResource.Error error = GSON.fromJson(rBody, EasyPostResource.Error.class);

            if (error.getError().length() > 0) {
                throw new EasyPostException(error.getError());
            }

            throw new EasyPostException(error.getMessage(), error.getParam(), null);
        } catch (Exception e) {
            throw new EasyPostException(
                    String.format("An error occurred. Response code: %s Response body: %s", rCode, rBody));
        }
    }

    private static EasyPostResponse makeAppEngineRequest(final RequestMethod method, String url, final String query,
                                                         final JsonObject body, final String apiKey)
            throws EasyPostException {
        String unknownErrorMessage = String.format(
                "Sorry, an unknown error occurred while trying to use the Google App Engine runtime." +
                        "Please email %s for assistance.", EasyPostResource.EASYPOST_SUPPORT_EMAIL);
        try {
            if ((method == RequestMethod.GET || method == RequestMethod.DELETE) && query != null) {
                url = String.format("%s?%s", url, query);
            }
            URL fetchURL = new URL(url);

            Class<?> requestMethodClass = Class.forName("com.google.appengine.api.urlfetch.HTTPMethod");
            Object httpMethod = requestMethodClass.getDeclaredField(method.name()).get(null);

            Class<?> fetchOptionsBuilderClass = Class.forName("com.google.appengine.api.urlfetch.FetchOptions$Builder");
            Object fetchOptions = null;
            try {
                fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod("validateCertificate").invoke(null);
            } catch (NoSuchMethodException e) {
                System.err.printf(
                        "Warning: this App Engine SDK version does not allow verification of SSL certificates;" +
                                "this exposes you to a MITM attack. Please upgrade your App Engine SDK to >=1.5.0. " +
                                "If you have questions, email %s.%n", EasyPostResource.EASYPOST_SUPPORT_EMAIL);
                fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod("withDefaults").invoke(null);
            }

            Class<?> fetchOptionsClass = Class.forName("com.google.appengine.api.urlfetch.FetchOptions");

            // Heroku times out after 30s, so leave some time for the API to return a response
            fetchOptionsClass.getDeclaredMethod("setDeadline", java.lang.Double.class)
                    .invoke(fetchOptions, getAppEngineTimeoutSeconds());

            Class<?> requestClass = Class.forName("com.google.appengine.api.urlfetch.HTTPRequest");

            Object request = requestClass.getDeclaredConstructor(URL.class, requestMethodClass, fetchOptionsClass)
                    .newInstance(fetchURL, httpMethod, fetchOptions);

            if ((method == RequestMethod.POST || method == RequestMethod.PUT) && body != null) {
                String bodyString = body.toString();
                requestClass.getDeclaredMethod("setPayload", byte[].class).invoke(request, bodyString.getBytes());
            }

            for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
                Class<?> httpHeaderClass = Class.forName("com.google.appengine.api.urlfetch.HTTPHeader");
                Object reqHeader = httpHeaderClass.getDeclaredConstructor(String.class, String.class)
                        .newInstance(header.getKey(), header.getValue());
                requestClass.getDeclaredMethod("setHeader", httpHeaderClass).invoke(request, reqHeader);
            }

            Class<?> urlFetchFactoryClass = Class.forName("com.google.appengine.api.urlfetch.URLFetchServiceFactory");
            Object urlFetchService = urlFetchFactoryClass.getDeclaredMethod("getURLFetchService").invoke(null);

            Method fetchMethod = urlFetchService.getClass().getDeclaredMethod("fetch", requestClass);
            fetchMethod.setAccessible(true);
            Object response = fetchMethod.invoke(urlFetchService, request);

            int responseCode = (Integer) response.getClass().getDeclaredMethod("getResponseCode").invoke(response);
            String responseBody =
                    new String((byte[]) response.getClass().getDeclaredMethod("getContent").invoke(response), CHARSET);

            return new EasyPostResponse(responseCode, responseBody);

        } catch (InvocationTargetException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (MalformedURLException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (NoSuchFieldException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (SecurityException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (NoSuchMethodException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (ClassNotFoundException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (IllegalArgumentException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (IllegalAccessException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (InstantiationException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        } catch (UnsupportedEncodingException e) {
            throw new EasyPostException(unknownErrorMessage, e);
        }
    }

    /**
     * Get all static methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllStaticMethods(Class<?> type) {
        List<Method> allMethods = getAllMethods(type);

        List<Method> staticMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if (Modifier.isStatic(method.getModifiers())) {
                staticMethods.add(method);
            }
        }

        return staticMethods;
    }

    /**
     * Get all methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllMethods(Class<?> type) {
        return Arrays.asList(type.getMethods());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {

        return (String) this.getIdString();
    }

    private Object getIdString() {
        try {
            Field idField = this.getClass().getDeclaredField("id");
            return idField.get(this);
        } catch (SecurityException e) {
            return "";
        } catch (NoSuchFieldException e) {
            return "";
        } catch (IllegalArgumentException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    /**
     * Pretty print the JSON representation of the object.
     *
     * @return the JSON representation of the object.
     */
    public String prettyPrint() {
        return String.format("<%s@%s id=%s> JSON: %s", this.getClass().getName(), System.identityHashCode(this),
                this.getIdString(), PRETTY_PRINT_GSON.toJson(this));
    }

    /**
     * Merge two EasyPostResource objects.
     *
     * @param obj    the base object
     * @param update the object to merge
     */
    public void merge(final EasyPostResource obj, final EasyPostResource update) {
        if (!obj.getClass().isAssignableFrom(update.getClass())) {
            return;
        }

        // get all methods from the obj class and its superclasses
        List<Method> methods = getAllNonStaticMethods(obj.getClass());

        for (Method fromMethod : methods) {
            if (fromMethod.getName().startsWith("get") || GLOBAL_FIELD_ACCESSORS.contains(fromMethod.getName())) {

                if (fromMethod.isAnnotationPresent(Deprecated.class)) {
                    // skip deprecated methods
                    continue;
                }

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Object value = fromMethod.invoke(update, (Object[]) null);
                    if (value != null) {
                        Method toMethod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                        toMethod.invoke(obj, value);
                    }
                } catch (Exception e) {
                    // TODO: Address situation below
                    /*
                      The method getSmartrates() on the Shipment object is causing this exception.
                      Since it found a method with "get" in the name, it expects there to be a "set" equivalent.
                      There is not, causing this exception to be thrown, although nothing wrong has really happened.
                      This code block was copy-pasted from StackOverflow: https://stackoverflow.com/a/7526414/13343799
                      Per the comments, there are some built-in expectations for how this will work,
                      and should eventually be re-written or removed entirely
                      (explore returning a brand-new object rather than modifying the existing one).
                      For now, the easiest fix would be to
                      a) just ignore this exception, or
                      b) rename getSmartrates() in the Shipment class to just smartrates()
                      (similar to how the other methods are named).
                     */
                    // e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get all non-static methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllNonStaticMethods(Class<?> type) {
        List<Method> allMethods = getAllMethods(type);

        List<Method> nonStaticMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if (!Modifier.isStatic(method.getModifiers())) {
                nonStaticMethods.add(method);
            }
        }

        return nonStaticMethods;
    }

    @SuppressWarnings ("unused")
    private static class ErrorContainer {
        private EasyPostResource.Error error;
    }

    private static class Error {
        private String type;
        private String message;
        private String code;
        private String param;
        private String error;

        @SuppressWarnings ("unused")
        public String getCode() {
            return code;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getParam() {
            return param;
        }

        @SuppressWarnings ("unused")
        public String getType() {
            return type;
        }
    }

}
