package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;


public class ErrorTest {

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
     * Test creating a bad shipment and retrieving errors.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testError() throws EasyPostException {
        assertThrows(EasyPostException.class, () -> Shipment.create(null));
    }
}
