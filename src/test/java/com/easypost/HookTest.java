package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.hooks.RequestHookResponses;
import com.easypost.hooks.ResponseHookResponses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.Function;

public class HookTest {
    private static TestUtils.VCR vcr;

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
     * Test failing a hook if we subscribed to a request hook.
     *
     * @param datas The RequestHookResponses object representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object failIfSubscribedToRequest(RequestHookResponses datas) {
        fail("Test failed");

        return false;
    }

    /**
     * Test failing a hook if we subscribed to a response hook.
     *
     * @param datas The ResponseHookResponses object representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object failIfSubscribedToResponse(ResponseHookResponses datas) {
        fail("Test failed");

        return false;
    }

    /**
     * Test subscribing a request hook.
     *
     * @param datas The RequestHookResponses object representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object testRequestHooks(RequestHookResponses datas) {
        assertEquals("https://api.easypost.com/v2/parcels", datas.getPath());
        assertEquals("POST", datas.getMethod());
        assertNotNull(datas.getHeaders());
        assertNotNull(datas.getRequestBody());
        assertNotNull(datas.getRequestTimestamp());
        assertNotNull(datas.getRequestUuid());

        return true;
    }

    /**
     * Test subscribing a response hook.
     *
     * @param datas The ResponseHookResponses object representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object testResponseHooks(ResponseHookResponses datas) {
        assertEquals("https://api.easypost.com/v2/parcels", datas.getPath());
        assertEquals("POST", datas.getMethod());
        assertEquals(201, datas.getHttpStatus());
        assertNotNull(datas.getHeaders());
        assertNotNull(datas.getResponseBody());
        assertNotNull(datas.getRequestTimestamp());
        assertNotNull(datas.getRequestTimestamp());
        assertNotNull(datas.getRequestUuid());

        return true;
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
}
