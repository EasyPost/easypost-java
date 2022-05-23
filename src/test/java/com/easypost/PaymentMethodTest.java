package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.PaymentMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class PaymentMethodTest {

    private static TestUtils.VCR _vcr;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        _vcr = new TestUtils.VCR("payment_method", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test retrieve all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

        PaymentMethod paymentMethods = PaymentMethod.all();

        assertInstanceOf(PaymentMethod.class, paymentMethods);
    }
}
