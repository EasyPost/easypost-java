package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.PaymentMethod;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentMethodTest {
    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_PROD_API_KEY");
    }

    /**
     * Test retrieve all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        PaymentMethod billing = PaymentMethod.all();

        assertTrue(billing.getPrimaryPaymentMethod() != null);
        assertTrue(billing.getSecondaryPaymentMethod() != null);
    }
}
