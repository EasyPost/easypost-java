package com.easypost;

import com.easypost.exception.General.MissingParameterError;
import com.easypost.service.EasyPostClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class EasyPostTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws MissingParameterError
     */
    @BeforeAll
    public static void setup() throws MissingParameterError {
        vcr = new TestUtils.VCR("client", TestUtils.ApiKey.TEST);
    }

    /**
     * Test connection timeout getter and setter.
     *
     * @throws MissingParameterError
     */
    @Test
    public void testConnectionTimeout() throws MissingParameterError {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1);

        assertEquals(1, client.getConnectionTimeoutMilliseconds());
    }

    /**
     * Test read timeout getter and setter.
     *
     * @throws MissingParameterError
     */
    @Test
    public void testRequestTimeout() throws MissingParameterError {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1, 10);

        assertEquals(10, client.getReadTimeoutMilliseconds());
    }

    /**
     * Test setting API base.
     *
     * @throws MissingParameterError
     */
    @Test
    public void testApiBase() throws MissingParameterError {
        EasyPostClient client = new EasyPostClient("fake_api_key", "https://api.easypostExample.com");

        assertEquals("https://api.easypostExample.com", client.getApiBase());
    }

    /**
     * Test create EasyPostClient with invalid API key.
     *
     * @throws MissingParameterError
     */
    @Test
    public void testCreateEasyPostClientWithInvalidKey() throws MissingParameterError {
        assertThrows(MissingParameterError.class, () -> new EasyPostClient(null));
        assertThrows(MissingParameterError.class, () -> new EasyPostClient(null));
    }
}
