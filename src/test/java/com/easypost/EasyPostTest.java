package com.easypost;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.easypost.http.Constant;
import com.easypost.service.EasyPostClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class EasyPostTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     */
    @BeforeAll
    public static void setup() {
        vcr = new TestUtils.VCR("client", TestUtils.ApiKey.TEST);
    }

    /**
     * Test connection timeout getter and setter.
     */
    @Test
    public void testConnectionTimeout() {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1);

        assertEquals(1, client.getConnectionTimeoutMilliseconds());
    }

    /**
     * Test read timeout getter and setter.
     */
    @Test
    public void testRequestTimeout() {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1, 10);

        assertEquals(10, client.getReadTimeoutMilliseconds());
    }

    /**
     * Test create multiple EasyPostClient with different API keys.
     */
    @Test
    public void testMultipleClients() {
        EasyPostClient clientOne = new EasyPostClient("fake_api_key_1", 22222, 33333);
        EasyPostClient clientTwo = new EasyPostClient("fake_api_key_2", 55555);

        assertEquals("fake_api_key_1", clientOne.getApiKey());
        assertEquals(22222, clientOne.getConnectionTimeoutMilliseconds());
        assertEquals(33333, clientOne.getReadTimeoutMilliseconds());

        assertEquals("fake_api_key_2", clientTwo.getApiKey());
        assertEquals(55555, clientTwo.getConnectionTimeoutMilliseconds());
        assertEquals(Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS, clientTwo.getReadTimeoutMilliseconds());
    }
}
