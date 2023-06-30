package com.easypost;

import com.easypost.exception.EasyPostException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
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
     * Test failing a hook if we subscribed.
     *
     * @param input The input HashMap representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object failIfSubscribed(HashMap<String, Object> input) {
        fail("Test failed");

        return false;
    }

    /**
     * Test subscribing a request hook.
     *
     * @param datas The input HashMap representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object testRequestHooks(HashMap<String, Object> datas) {
        assertEquals("https://api.easypost.com/v2/parcels", datas.get("path"));
        assertEquals("POST", datas.get("method"));
        assertTrue(datas.containsKey("headers"));
        assertTrue(datas.containsKey("request_body"));
        assertTrue(datas.containsKey("request_timestamp"));
        assertTrue(datas.containsKey("request_uuid"));

        return true;
    }

    /**
     * Test subscribing a response hook.
     *
     * @param datas The input HashMap representing the hook data.
     * @return The result of the test.
     * @throws EasyPostException when the request fails.
     */
    public static Object testResponseHooks(HashMap<String, Object> datas) {
        assertEquals("https://api.easypost.com/v2/parcels", datas.get("path"));
        assertEquals("POST", datas.get("method"));
        assertEquals(201, datas.get("http_status"));
        assertTrue(datas.containsKey("headers"));
        assertTrue(datas.containsKey("response_body"));
        assertTrue(datas.containsKey("response_timestamp"));
        assertTrue(datas.containsKey("request_timestamp"));
        assertTrue(datas.containsKey("request_uuid"));

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
        Function<HashMap<String, Object>, Object> requestHook = HookTest::testRequestHooks;
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
        Function<HashMap<String, Object>, Object> requestHook = HookTest::testResponseHooks;
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

        Function<HashMap<String, Object>, Object> failedHook = HookTest::failIfSubscribed;

        vcr.client.subscribeToRequestHook(failedHook);
        vcr.client.unsubscribeFromRequestHook(failedHook);
    
        vcr.client.subscribeToResponseHook(failedHook);
        vcr.client.unsubscribeFromResponseHook(failedHook);

        vcr.client.parcel.create(Fixtures.basicParcel());

    }
}
