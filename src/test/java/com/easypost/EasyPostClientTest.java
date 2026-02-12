package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor.RequestMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class EasyPostClientTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("client", TestUtils.ApiKey.TEST);
    }

    /**
     * Test making a generic API call.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testMakeApiCall() throws EasyPostException {
        vcr.setUpTest("make_api_call");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", 1);

        Map<String, Object> response = vcr.client.makeApiCall(RequestMethod.GET, "addresses", params);

        assertNotNull(response);
        List<?> addresses = (List<?>) response.get("addresses");
        assertNotNull(addresses);
        assertEquals(1, addresses.size());

        Map<String, Object> firstAddress = (Map<String, Object>) addresses.get(0);
        assertEquals("Address", firstAddress.get("object"));
    }
}
