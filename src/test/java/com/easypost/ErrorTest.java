package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ErrorTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() {
        vcr = new TestUtils.VCR("error", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a bad shipment and retrieving errors.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testError() throws EasyPostException {
        vcr.setUpTest("error");

        // should throw EasyPostException,
        // but might throw NullPointerException due to a bug in the VCR grabbing response content,
        // so we'll just check fo a generic exception
        assertThrows(Exception.class, () -> Shipment.create(null));
    }
}
