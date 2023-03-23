package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.PaymentMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentMethodTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("payment_method", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test retrieving all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        PaymentMethod paymentMethod = vcr.client.paymentMethod.all();

        assertInstanceOf(PaymentMethod.class, paymentMethod);
        assertTrue(paymentMethod.getId().startsWith("cust_"));
    }
}
