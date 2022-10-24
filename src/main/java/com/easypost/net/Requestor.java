/**
 * EasyPostResource.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.net;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.API.ForbiddenError;
import com.easypost.exception.API.GatewayTimeoutError;
import com.easypost.exception.API.InternalServerError;
import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.API.MethodNotAllowedError;
import com.easypost.exception.API.NotFoundError;
import com.easypost.exception.API.PaymentError;
import com.easypost.exception.API.RateLimitError;
import com.easypost.exception.API.RedirectError;
import com.easypost.exception.API.ServiceUnavailablError;
import com.easypost.exception.API.TimeoutError;
import com.easypost.exception.API.UnauthorizedError;
import com.easypost.exception.API.UnknownApiError;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.model.Error;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Requestor {
    public enum RequestMethod {
        GET,
        POST,
        DELETE,
        PUT
    }

    private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";
    private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.easypost.net.customURLStreamHandler";

    private static String urlEncodePair(final String key, final String value) throws UnsupportedEncodingException {
        return String.format("%s=%s",
                URLEncoder.encode(key, Constant.CHARSET), URLEncoder.encode(value, Constant.CHARSET));
    }

    static Map<String, String> getHeaders(String apiKey) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Charset", Constant.CHARSET);
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
                @SuppressWarnings("unchecked")
                Class<URLStreamHandler> clazz = (Class<URLStreamHandler>) Class
                        .forName(customURLStreamHandlerClassName);
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
        conn.setConnectTimeout(EasyPostResource.getConnectTimeoutMilliseconds());
        conn.setRequestMethod(method);

        int readTimeout;
        if (EasyPost.readTimeout != 0) {
            readTimeout = EasyPost.readTimeout;
        } else {
            readTimeout = EasyPostResource.getReadTimeoutMilliseconds();
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
                output.write(jsonString.getBytes(Constant.CHARSET));
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
        // this is a hack to fix a broken concept:
        // https://github.com/google/gson/issues/1080
        // noinspection rawtypes,unchecked
        JsonElement jsonElement = Constant.GSON.toJsonTree(new HashMap(params));
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
        if (responseStream.available() == 0) {
            // Return empty string if the InputSteam is empty to avoid exceptions.
            return "";
        }

        @SuppressWarnings("resource")
        String rBody = new Scanner(responseStream, Constant.CHARSET).useDelimiter("\\A").next();
        responseStream.close();
        return rBody;
    }

    private static EasyPostResponse makeURLConnectionRequest(final RequestMethod method,
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
                                    Constant.EASYPOST_SUPPORT_EMAIL));
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
                    "please contact us at %s.", EasyPost.API_BASE, Constant.EASYPOST_SUPPORT_EMAIL), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Send an HTTP request to EasyPost.
     *
     * @param <T>    Any class.
     * @param method The method of the API request.
     * @param url    The URL of the API request.
     * @param params The params of the API request.
     * @param clazz  The class of the object for deserialization
     * @param apiKey The API key for this API request.
     * 
     * @return A class object.
     * @throws EasyPostException when the request fails.
     */
    public static <T> T request(final RequestMethod method, final String url,
            final Map<String, Object> params, final Class<T> clazz, final String apiKey)
            throws EasyPostException {
        return request(method, url, params, clazz, apiKey, true);
    }

    /**
     * Send an HTTP request to EasyPost.
     *
     * @param <T>            Any class.
     * @param method         The method of the API request.
     * @param url            The URL of the API request.
     * @param params         The params of the API request.
     * @param clazz          The class of the object for deserialization
     * @param apiKey         The API key for this API request.
     * @param apiKeyRequired The API key for this HTTP request call if needed.
     *
     * @return A class object.
     * @throws EasyPostException when the request fails.
     */
    protected static <T> T request(final RequestMethod method, final String url,
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

    @SuppressWarnings("checkstyle:methodname")
    protected static <T> T _request(final RequestMethod method, final String url,
            final Map<String, Object> params, final Class<T> clazz, String apiKey,
            final boolean apiKeyRequired) throws EasyPostException {
        if ((EasyPost.apiKey == null || EasyPost.apiKey.length() == 0) && (apiKey == null || apiKey.length() == 0)) {
            if (apiKeyRequired) {
                throw new MissingParameterError(Constants.INVALID_API_KEY_TYPE);
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
                                        Constant.CHARSET, Constant.EASYPOST_SUPPORT_EMAIL),
                                e);
                    }
                    break;
                case POST:
                case PUT:
                    try {
                        body = createBody(params);
                    } catch (Exception e) {
                        throw new EasyPostException(String.format(
                                "Unable to create JSON body from parameters. Please email %s for assistance.",
                                Constant.EASYPOST_SUPPORT_EMAIL), e);
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

        return Constant.GSON.fromJson(rBody, clazz);
    }

    /**
     * Handles API error based on the error status code.
     *
     * @param rBody Body of the error message.
     * @param rCode Status code of the error messsage.
     */
    protected static void handleAPIError(String rBody, final int rCode) throws EasyPostException {
        if (rBody == null || rBody.length() == 0) {
            rBody = "{}";
        }
        Error error = Constant.GSON.fromJson(rBody, Error.class);
        String errorMessage = error.getMessage();
        String errorCode = error.getCode();
        List<Error> errors = error.getErrors();

        if (rCode >= Constants.ErrorCode.REDIRECT_CODE_BEGIN && rCode <= Constants.ErrorCode.REDIRECT_CODE_END) {
            throw new RedirectError(errorMessage, errorCode, rCode, errors);
        }

        switch (rCode) {
            case Constants.ErrorCode.UNAUTHORIZED_ERROR:
                throw new UnauthorizedError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.FORBIDDEN_ERROR:
                throw new ForbiddenError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.PAYMENT_ERROR:
                throw new PaymentError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.NOT_FOUND_ERROR:
                throw new NotFoundError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.METHOD_NOT_ALLOWED_ERROR:
                throw new MethodNotAllowedError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.TIMEOUT_ERROR:
                throw new TimeoutError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.INVALID_REQUEST_ERROR:
                throw new InvalidRequestError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.RATE_LIMIT_ERROR:
                throw new RateLimitError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.INTERNAL_SERVER_ERROR:
                throw new InternalServerError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.SERVICE_UNAVAILABLE_ERROR:
                throw new ServiceUnavailablError(errorMessage, errorCode, rCode, errors);
            case Constants.ErrorCode.GATEWAY_TIMEOUT_ERROR:
                throw new GatewayTimeoutError(errorMessage, errorCode, rCode, errors);
            default:
                throw new UnknownApiError(errorMessage, errorCode, rCode, errors);
        }
    }

    private static EasyPostResponse makeAppEngineRequest(final RequestMethod method, String url, final String query,
            final JsonObject body, final String apiKey)
            throws EasyPostException {
        String unknownErrorMessage = String.format(
                "Sorry, an unknown error occurred while trying to use the Google App Engine runtime." +
                        "Please email %s for assistance.",
                Constant.EASYPOST_SUPPORT_EMAIL);
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
                                "If you have questions, email %s.%n",
                        Constant.EASYPOST_SUPPORT_EMAIL);
                fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod("withDefaults").invoke(null);
            }

            Class<?> fetchOptionsClass = Class.forName("com.google.appengine.api.urlfetch.FetchOptions");

            // Heroku times out after 30s, so leave some time for the API to return a
            // response
            fetchOptionsClass.getDeclaredMethod("setDeadline", java.lang.Double.class)
                    .invoke(fetchOptions, EasyPostResource.getAppEngineTimeoutSeconds());

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
            String responseBody = new String(
                    (byte[]) response.getClass().getDeclaredMethod("getContent").invoke(response), Constant.CHARSET);

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
}
