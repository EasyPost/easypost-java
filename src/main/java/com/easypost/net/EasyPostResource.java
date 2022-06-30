package com.easypost.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.BatchStatus;
import com.easypost.model.Event;
import com.easypost.model.EventDeserializer;
import com.easypost.model.Fee;
import com.easypost.model.Rate;
import com.easypost.model.RateDeserializer;
import com.easypost.model.Shipment;
import com.easypost.model.TrackingDetail;

public abstract class EasyPostResource {

	public static final Gson gson = new GsonBuilder()
		.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
		.registerTypeAdapter(Event.class, new EventDeserializer())
		.registerTypeAdapter(Rate.class, new RateDeserializer())
		.create();

	public static final Gson prettyPrintGson = new GsonBuilder().
		setPrettyPrinting().
		serializeNulls().
		setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
		registerTypeAdapter(Event.class, new EventDeserializer()).
		create();

	@Override public String toString() {

		return (String) this.getIdString();
	}

	public String prettyPrint() {
		return String.format(
			"<%s@%s id=%s> JSON: %s",
			this.getClass().getName(),
			System.identityHashCode(this),
			this.getIdString(),
			prettyPrintGson.toJson(this));
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

	private static String className(Class<?> clazz) {
		return clazz.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase().replace("$", "");

	}

	protected static String singleClassURL(Class<?> clazz) {
		return String.format("%s/%s", EasyPost.API_BASE, className(clazz));
	}

	protected static String classURL(Class<?> clazz) {
		String singleURL = singleClassURL(clazz);
		if (singleURL.charAt(singleURL.length() - 1) == 's' || singleURL.charAt(singleURL.length() - 1) == 'h') {
			return String.format("%ses", singleClassURL(clazz));
		} else {
			return String.format("%ss", singleClassURL(clazz));
		}
	}

	protected static String instanceURL(Class<?> clazz, String id) {
		return String.format("%s/%s", classURL(clazz), id);
	}

	public void merge(EasyPostResource obj, EasyPostResource update) {
	    if (!obj.getClass().isAssignableFrom(update.getClass())) {
	        return;
	    }

	    Method[] methods = obj.getClass().getMethods();

	    for (Method fromMethod: methods) {
	        if ((fromMethod.getDeclaringClass().equals(obj.getClass()) && fromMethod.getName().startsWith("get"))
					|| GLOBAL_FIELD_ACCESSORS.contains(fromMethod.getName())) {

	            String fromName = fromMethod.getName();
	            String toName = fromName.replace("get", "set");

	            try {
	                Object value = fromMethod.invoke(update, (Object[]) null);
	                if (value != null) {
		                Method toMethod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
	                    toMethod.invoke(obj, value);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	public static final String CHARSET = "UTF-8";

	private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";


	// Set this property to override your environment's default URLStreamHandler.
	private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.easypost.net.customURLStreamHandler";

	protected enum RequestMethod {
		GET, POST, DELETE, PUT
	}

	private static String urlEncodePair(String k, String v) throws UnsupportedEncodingException {
		return String.format("%s=%s", URLEncoder.encode(k, CHARSET), URLEncoder.encode(v, CHARSET));
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

	private static javax.net.ssl.HttpsURLConnection createEasyPostConnection(String url, String apiKey) throws IOException {
		URL easypostURL = null;
		String customURLStreamHandlerClassName = System.getProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
		if (customURLStreamHandlerClassName != null) {
			// instantiate the custom handler provided
			try {
				Class<URLStreamHandler> clazz = (Class<URLStreamHandler>) Class.forName(customURLStreamHandlerClassName);
				Constructor<URLStreamHandler> constructor = clazz.getConstructor();
				URLStreamHandler customHandler = constructor.newInstance();
				easypostURL = new URL(null, url, customHandler);
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
		} else {
			easypostURL = new URL(url);
		}
		javax.net.ssl.HttpsURLConnection conn = (javax.net.ssl.HttpsURLConnection) easypostURL.openConnection();
		conn.setConnectTimeout(20000); // 20 seconds

		int readTimeout;
		if (EasyPost.readTimeout != 0) {
			readTimeout = EasyPost.readTimeout;
		} else{
			readTimeout = 40000;                  // 40 seconds by default
		}
		conn.setReadTimeout(readTimeout);

		conn.setUseCaches(false);
		for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}

		return conn;
	}

	private static javax.net.ssl.HttpsURLConnection createGetConnection(String url, String query, String apiKey) throws IOException {
		String getURL = String.format("%s?%s", url, query);
		javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(getURL, apiKey);
		conn.setRequestMethod("GET");
		return conn;
	}

	private static javax.net.ssl.HttpsURLConnection createPostConnection(String url, String query, String apiKey) throws IOException {
		javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(url, apiKey);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", CHARSET));
		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;
	}

	private static javax.net.ssl.HttpsURLConnection createDeleteConnection(String url, String query, String apiKey) throws IOException {
		String deleteUrl = String.format("%s?%s", url, query);
		javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(deleteUrl, apiKey);
		conn.setRequestMethod("DELETE");
		return conn;
	}

	private static javax.net.ssl.HttpsURLConnection createPutConnection(String url, String query, String apiKey) throws IOException {
		javax.net.ssl.HttpsURLConnection conn = createEasyPostConnection(url, apiKey);
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", CHARSET));
		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;
	}

	private static String createQuery(Map<String, Object> params) throws UnsupportedEncodingException {
		Map<String, String> flatParams = flattenParams(params);
		StringBuffer queryStringBuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : flatParams.entrySet()) {
			queryStringBuffer.append("&");
			queryStringBuffer.append(urlEncodePair(entry.getKey(), entry.getValue()));
		}
		if (queryStringBuffer.length() > 0) {
			queryStringBuffer.deleteCharAt(0);
		}
		return queryStringBuffer.toString();
	}

	private static Map<String, String> flattenParams(Map<String, Object> params) {
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
					flatNestedMap.put(
						String.format("%s[%s]", key, nestedEntry.getKey()),
						nestedEntry.getValue());
				}
				flatParams.putAll(flattenParams(flatNestedMap));
			} else if (value instanceof List) {
				Map<String, Object> flatNestedMap = new HashMap<String, Object>();
				List<?> nestedList = (List<?>) value;
				for (int i = 0; i < nestedList.size(); i++) {
					flatNestedMap.put(
						String.format("%s[%s]", key, i),
						nestedList.get(i));
					flatParams.putAll(flattenParams(flatNestedMap));
				}
			} else if (value instanceof EasyPostResource) {
				flatParams.put(
						String.format("%s[%s]", key, "id"),
						value.toString());

			} else if (value != null) {
				flatParams.put(key, value.toString());
			}
		}

        // System.out.println(flatParams);

		return flatParams;
	}

	// represents Errors returned as JSON
	private static class ErrorContainer {
		private EasyPostResource.Error error;
	}

	private static class Error {
		@SuppressWarnings("unused")
		String type;
		String message;
		String code;
		String param;
		String error;
	}

	private static String getResponseBody(InputStream responseStream) throws IOException {
		String rBody = new Scanner(responseStream, CHARSET).useDelimiter("\\A").next();
		responseStream.close();
		return rBody;
	}

	private static EasyPostResponse makeURLConnectionRequest(EasyPostResource.RequestMethod method, String url, String query, String apiKey) throws EasyPostException {
		javax.net.ssl.HttpsURLConnection conn = null;
		try {
			switch (method) {
			case GET:
				conn = createGetConnection(url, query, apiKey);
				break;
			case POST:
				conn = createPostConnection(url, query, apiKey);
				break;
			case DELETE:
				conn = createDeleteConnection(url, query, apiKey);
				break;
			case PUT:
				conn = createPutConnection(url, query, apiKey);
				break;
			default:
				throw new EasyPostException(
					String.format("Unrecognized HTTP method %s. Please contact EasyPost at contact@easypost.com.", method));
			}
			int rCode = conn.getResponseCode(); // sends the request
			String rBody = null;
			if (rCode == 204) {
				rBody = "";
			} else if (rCode >= 200 && rCode < 300) {
				rBody = getResponseBody(conn.getInputStream());
			} else {
				rBody = getResponseBody(conn.getErrorStream());
			}
			return new EasyPostResponse(rCode, rBody);
		} catch (IOException e) {
			throw new EasyPostException(
				String.format(
					"Could not connect to EasyPost (%s). "
						+ "Please check your internet connection and try again. If this problem persists,"
						+ "please contact us at contact@easypost.com.", EasyPost.API_BASE), e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected static <T> T request(EasyPostResource.RequestMethod method, String url, Map<String, Object> params, Class<T> clazz, String apiKey) throws EasyPostException {
		return request(method, url, params, clazz, apiKey, true);
	}

	protected static <T> T request(EasyPostResource.RequestMethod method, String url, Map<String, Object> params, Class<T> clazz, String apiKey, boolean apiKeyRequired) throws EasyPostException {
		String originalDNSCacheTTL = null;
		Boolean allowedToSetTTL = true;
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

	protected static <T> T _request(EasyPostResource.RequestMethod method, String url, Map<String, Object> params, Class<T> clazz, String apiKey, boolean apiKeyRequired) throws EasyPostException {
		if ((EasyPost.apiKey == null || EasyPost.apiKey.length() == 0) && (apiKey == null || apiKey.length() == 0)) {
			if (apiKeyRequired) {
				throw new EasyPostException(
						"No API key provided. (set your API key using 'EasyPost.apiKey = {KEY}'. "
								+ "Your API key can be found in your EasyPost dashboard, or you can email us at contact@easypost.com for assistance.");

			}
		}

		if (apiKey == null) {
			apiKey = EasyPost.apiKey;
		}

		String query;
		try {
			query = createQuery(params);
		} catch (UnsupportedEncodingException e) {
			throw new EasyPostException("Unable to encode parameters to "
				+ CHARSET
				+ ". Please email contact@easypost.com for assistance.", e);
		}

        // System.out.println(url);

		EasyPostResponse response;
		try {
			// HTTPSURLConnection verifies SSL cert by default
			response = makeURLConnectionRequest(method, url, query, apiKey);
		} catch (ClassCastException ce) {
			// appengine
			String appEngineEnv = System.getProperty("com.google.appengine.runtime.environment", null);
			if (appEngineEnv != null) {
				response = makeAppEngineRequest(method, url, query, apiKey);
			} else {
				throw ce;
			}
		}
		int rCode = response.responseCode;
		String rBody = response.responseBody;
		if (rCode < 200 || rCode >= 300) {
			handleAPIError(rBody, rCode);
		}

		return gson.fromJson(rBody, clazz);
	}

	private static void handleAPIError(String rBody, int rCode) throws EasyPostException {
		try {
			EasyPostResource.Error error = gson.fromJson(rBody, EasyPostResource.Error.class);

			if(error.error.length() > 0) {
				throw new EasyPostException(error.error);
			}

			throw new EasyPostException(error.message, error.param, null);
		} catch (Exception e) {
            throw new EasyPostException(String.format("An error occured. Response code: %s Response body: %s", rCode, rBody));
        }
	}

	private static EasyPostResponse makeAppEngineRequest(RequestMethod method, String url, String query, String apiKey) throws EasyPostException {
		String unknownErrorMessage = "Sorry, an unknown error occurred while trying to use the "
			+ "Google App Engine runtime. Please email contact@easypost.com for assistance.";
		try {
			if (method == RequestMethod.GET || method == RequestMethod.DELETE) {
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
				System.err
					.println("Warning: this App Engine SDK version does not allow verification of SSL certificates;"
						+ "this exposes you to a MITM attack. Please upgrade your App Engine SDK to >=1.5.0. "
						+ "If you have questions, email contact@easypost.com.");
				fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod("withDefaults").invoke(null);
			}

			Class<?> fetchOptionsClass = Class.forName("com.google.appengine.api.urlfetch.FetchOptions");

			// Heroku times out after 30s, so leave some time for the API to return a response
			fetchOptionsClass.getDeclaredMethod("setDeadline", java.lang.Double.class).invoke(fetchOptions, new Double(20));

			Class<?> requestClass = Class.forName("com.google.appengine.api.urlfetch.HTTPRequest");

			Object request = requestClass.getDeclaredConstructor(URL.class, requestMethodClass, fetchOptionsClass).newInstance(fetchURL, httpMethod, fetchOptions);

			if (method == RequestMethod.POST) {
				requestClass.getDeclaredMethod("setPayload", byte[].class).invoke(request, query.getBytes());
			}

			for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
				Class<?> httpHeaderClass = Class.forName("com.google.appengine.api.urlfetch.HTTPHeader");
				Object reqHeader = httpHeaderClass.getDeclaredConstructor(String.class, String.class).newInstance(header.getKey(), header.getValue());
				requestClass.getDeclaredMethod("setHeader", httpHeaderClass).invoke(request, reqHeader);
			}

			Class<?> urlFetchFactoryClass = Class.forName("com.google.appengine.api.urlfetch.URLFetchServiceFactory");
			Object urlFetchService = urlFetchFactoryClass.getDeclaredMethod("getURLFetchService").invoke(null);

			Method fetchMethod = urlFetchService.getClass().getDeclaredMethod("fetch", requestClass);
			fetchMethod.setAccessible(true);
			Object response = fetchMethod.invoke(urlFetchService, request);

			int responseCode = (Integer) response.getClass().getDeclaredMethod("getResponseCode").invoke(response);
			String body = new String((byte[]) response.getClass().getDeclaredMethod("getContent").invoke(response), CHARSET);

			return new EasyPostResponse(responseCode, body);

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

	public static final ArrayList<String> GLOBAL_FIELD_ACCESSORS = new ArrayList<>(Arrays.asList("getCreatedAt", "getUpdatedAt", "getFees"));
	Date CreatedAt;
	Date UpdatedAt;
	ArrayList<Fee> fees;

	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public ArrayList<Fee> getFees() {
		return fees;
	}
	public void setFees(ArrayList<Fee> fees) {
		this.fees = fees;
	}

	public String getId() {
		return "";
	}
	public String getMode() {
		return "";
	}
	// Batch
	public List<Shipment> getShipments() {
		return new ArrayList<Shipment>();
	}
	// public BatchStatus getStatus() {
	// 	return new BatchStatus();
	// }
	public String getLabelUrl() {
		return "";
	}
	// Tracker
	public String getShipmentId() {
		return "";
	}
	public String getTrackingCode() {
		return "";
	}
	public String getStatus() {
		return "";
	}
	public List<TrackingDetail> getTrackingDetails() {
		return new ArrayList<TrackingDetail>();
	}

}
