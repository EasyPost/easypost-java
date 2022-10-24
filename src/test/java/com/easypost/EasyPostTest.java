package com.easypost;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.easypost.http.Requestor;

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

        Requestor.setConnectTimeoutMilliseconds(testTimeout);

        assertEquals(1, Requestor.getConnectTimeoutMilliseconds());

        // We have to set the connection timeout back to default to avoid other unit tests getting timeout.
        Requestor.setConnectTimeoutMilliseconds(30000);
    }

    /**
     * Test read timeout getter and setter.
     */
    @Test
    public void testRequestTimeout() {
        int testTimeout = 1;

        Requestor.setReadTimeoutMilliseconds(testTimeout);

        assertEquals(1, Requestor.getReadTimeoutMilliseconds());

        // We have to set the connection timeout back to default to avoid other unit tests getting timeout.
        Requestor.setReadTimeoutMilliseconds(60000);
    }
}
