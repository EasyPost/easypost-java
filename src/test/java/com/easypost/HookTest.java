package com.easypost;

import com.easypost.exception.API.NotFoundError;
import com.easypost.exception.EasyPostException;
import com.easypost.hooks.RequestHookResponses;
import com.easypost.hooks.ResponseHookResponses;
import com.easypost.service.EasyPostClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HookTest {
    private static TestUtils.VCR vcr;

    private static boolean hookHit = false;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("hook", TestUtils.ApiKey.TEST);
    }

    /**
     * Clear the connection override after each test.
     */
    @AfterEach
    public void tearDown() {
        EasyPost._vcrUrlFunction = null;
    }

    /**
     * Test failing a hook if we subscribed to a request hook.
     *
     * @param data The RequestHookResponses object representing the hook data.
     * @return The result of the test.
     */
    public static Object failIfSubscribedToRequest(RequestHookResponses data) {
        fail("Test failed");

        return false;
    }

    /**
     * Test failing a hook if we subscribed to a response hook.
     *
     * @param data The ResponseHookResponses object representing the hook data.
     * @return The result of the test.
     */
    public static Object failIfSubscribedToResponse(ResponseHookResponses data) {
        fail("Test failed");

        return false;
    }

    /**
     * Test subscribing a request hook.
     *
     * @param data The RequestHookResponses object representing the hook data.
     * @return The result of the test.
     */
    public static Object testRequestHooks(RequestHookResponses data) {
        assertEquals("https://api.easypost.com/v2/parcels", data.getPath());
        assertEquals("POST", data.getMethod());
        assertNotNull(data.getHeaders());
        assertNotNull(data.getRequestBody());
        assertNotNull(data.getRequestTimestamp());
        assertNotNull(data.getRequestUuid());

        return true;
    }

    /**
     * Test subscribing a response hook when an HTTP error occurs.
     *
     * @param data The ResponseHookResponses object representing the hook data.
     * @return The result of the test.
     */
    public static Object testResponseHookOnHttpError(ResponseHookResponses data) {
        assertEquals("https://api.easypost.com/v2/parcels/par_123", data.getPath());
        assertEquals("GET", data.getMethod());
        assertEquals(404, data.getHttpStatus());
        assertNotNull(data.getHeaders());
        assertNotNull(data.getResponseBody());
        assertNotNull(data.getRequestTimestamp());
        assertNotNull(data.getRequestTimestamp());
        assertNotNull(data.getRequestUuid());

        hookHit = true;

        return true;
    }

    /**
     * Test subscribing a response hook.
     *
     * @param data The ResponseHookResponses object representing the hook data.
     * @return The result of the test.
     */
    public static Object testResponseHooks(ResponseHookResponses data) {
        assertEquals("https://api.easypost.com/v2/parcels", data.getPath());
        assertEquals("POST", data.getMethod());
        assertEquals(201, data.getHttpStatus());
        assertNotNull(data.getHeaders());
        assertNotNull(data.getResponseBody());
        assertNotNull(data.getRequestTimestamp());
        assertNotNull(data.getRequestTimestamp());
        assertNotNull(data.getRequestUuid());

        return true;
    }

    /**
     * Build a mocked connection that returns a successful Address response.
     *
     * @return HttpsURLConnection object.
     * @throws IOException if the mock cannot be set up.
     */
    private static HttpsURLConnection mockConnection() throws IOException {
        byte[] body = "{\"id\": \"adr_123\", \"object\": \"Address\"}".getBytes(StandardCharsets.UTF_8);
        HttpsURLConnection connection = Mockito.mock(HttpsURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(200);
        Mockito.when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(body));
        return connection;
    }

    /**
     * Make a request over a mocked connection and capture the headers passed to the request and response hooks.
     *
     * @param apiKey     The API key to make the request with.
     * @param connection The mocked connection to send the request over.
     * @return The headers passed to the request hook, followed by the headers passed to the response hook.
     * @throws EasyPostException when the request fails.
     */
    private static List<Map<String, String>> captureHookHeaders(String apiKey, HttpsURLConnection connection)
            throws EasyPostException {
        EasyPost._vcrUrlFunction = url -> connection;
        EasyPostClient client = new EasyPostClient(apiKey);
        List<Map<String, String>> hookHeaders = new ArrayList<>();
        client.subscribeToRequestHook(data -> hookHeaders.add(data.getHeaders()));
        client.subscribeToResponseHook(data -> hookHeaders.add(data.getHeaders()));

        client.address.retrieve("adr_123");

        assertEquals(2, hookHeaders.size());
        return hookHeaders;
    }

    /**
     * Test creating a Parcel with request hook subscribed.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateParcelWithRequestHook() throws EasyPostException {
        vcr.setUpTest("create");
        Function<RequestHookResponses, Object> requestHook = HookTest::testRequestHooks;
        vcr.client.subscribeToRequestHook(requestHook);
        vcr.client.parcel.create(Fixtures.basicParcel());
    }

    /**
     * Test creating a Parcel with response hook subscribed.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateParcelWithResponseHook() throws EasyPostException {
        vcr.setUpTest("create");
        Function<ResponseHookResponses, Object> requestHook = HookTest::testResponseHooks;
        vcr.client.subscribeToResponseHook(requestHook);
        vcr.client.parcel.create(Fixtures.basicParcel());
    }

    /**
     * Test creating a Parcel with unsubscribed hooks.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUnsubscribeHooks() throws EasyPostException {
        vcr.setUpTest("create");

        Function<RequestHookResponses, Object> failedRequestHook = HookTest::failIfSubscribedToRequest;

        vcr.client.subscribeToRequestHook(failedRequestHook);
        vcr.client.unsubscribeFromRequestHook(failedRequestHook);
    
        Function<ResponseHookResponses, Object> failedResponseHook = HookTest::failIfSubscribedToResponse;

        vcr.client.subscribeToResponseHook(failedResponseHook);
        vcr.client.unsubscribeFromResponseHook(failedResponseHook);

        vcr.client.parcel.create(Fixtures.basicParcel());
    }

    /**
     * Test that response hooks are still fired even if the HTTP call fails.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testResponseHookFiredOnHTTPError() throws EasyPostException {
        vcr.setUpTest("http_error");

        hookHit = false;

        Function<ResponseHookResponses, Object> requestHook = HookTest::testResponseHookOnHttpError;

        vcr.client.subscribeToResponseHook(requestHook);

        try {
            vcr.client.parcel.retrieve("par_123");
        } catch (NotFoundError e) {
            assertEquals(404, e.getStatusCode());
        }

        assertTrue(hookHit);
    }

    /**
     * Test that request and response hooks receive the API key redacted to its last four characters,
     * while the real request still sends the full API key.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the mock cannot be set up.
     */
    @Test
    public void testHooksReceiveRedactedApiKey() throws EasyPostException, IOException {
        String apiKey = "EZTKfakeapikey12345WXYZ";
        HttpsURLConnection connection = mockConnection();

        for (Map<String, String> headers : captureHookHeaders(apiKey, connection)) {
            assertEquals("Bearer ****WXYZ", headers.get("Authorization"));
            assertFalse(headers.values().stream().anyMatch(value -> value.contains(apiKey)));
        }

        Mockito.verify(connection).setRequestProperty("Authorization", "Bearer " + apiKey);
    }

    /**
     * Test that hooks receive a fully masked API key when the key is too short to partially reveal.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the mock cannot be set up.
     */
    @Test
    public void testHooksFullyRedactShortApiKey() throws EasyPostException, IOException {
        String apiKey = "short123";
        HttpsURLConnection connection = mockConnection();

        for (Map<String, String> headers : captureHookHeaders(apiKey, connection)) {
            assertEquals("Bearer ****", headers.get("Authorization"));
        }

        Mockito.verify(connection).setRequestProperty("Authorization", "Bearer " + apiKey);
    }
}
