package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class EasyPostTest {
    
    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");
    }

    /**
     * Test connection timeout getter and setter.
     *
     * @throws EasyPostException when the request fails.
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
     *
     * @throws EasyPostException when the request fails.
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
