package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.service.EasyPostClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class EasyPostTest {
    /**
     * Test connection timeout getter and setter.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testConnectionTimeout() throws EasyPostException {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1);

        assertEquals(1, client.getConnectionTimeoutMilliseconds());
    }

    /**
     * Test read timeout getter and setter.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testRequestTimeout() throws EasyPostException {
        EasyPostClient client = new EasyPostClient("fake_api_key", 1, 10);

        assertEquals(10, client.getReadTimeoutMilliseconds());
    }

    /**
     * Test setting API base.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testApiBase() throws EasyPostException {
        EasyPostClient client = new EasyPostClient("fake_api_key", "https://api.easypostExample.com");

        assertEquals("https://api.easypostExample.com", client.getApiBase());
    }

    /**
     * Test create EasyPostClient with invalid API key.
     *
     * @throws MissingParameterError if any required parameter is missing.
     */
    @Test
    public void testCreateEasyPostClientWithInvalidKey() throws MissingParameterError {
        assertThrows(MissingParameterError.class, () -> new EasyPostClient(null));
        assertThrows(MissingParameterError.class, () -> new EasyPostClient(null));
    }
}
