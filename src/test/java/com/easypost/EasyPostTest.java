package com.easypost;

import com.easypost.net.EasyPostResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        int testTimeout = 1;

        EasyPostResource.setConnectTimeoutMilliseconds(testTimeout);

        assertEquals(1, EasyPostResource.getConnectTimeoutMilliseconds());

        // We have to set the connection timeout back to default to avoid other unit tests getting timeout.
        EasyPostResource.setConnectTimeoutMilliseconds(30000);
    }

    /**
     * Test read timeout getter and setter.
     */
    @Test
    public void testRequestTimeout() {
        int testTimeout = 1;

        EasyPostResource.setReadTimeoutMilliseconds(testTimeout);

        assertEquals(1, EasyPostResource.getReadTimeoutMilliseconds());

        // We have to set the connection timeout back to default to avoid other unit tests getting timeout.
        EasyPostResource.setReadTimeoutMilliseconds(60000);
    }
}
