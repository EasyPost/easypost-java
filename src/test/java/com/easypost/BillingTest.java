package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Billing;
import com.easypost.model.PaymentMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BillingTest {
    private static TestUtils.VCR vcr;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("billing", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test deleting a payment method.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Skipping due to the lack of an available real payment method in tests.
    public void testDeletePaymentMethod() throws EasyPostException {
        vcr.setUpTest("delete_payment_method");

        boolean success = Billing.deletePaymentMethod(PaymentMethod.Priority.PRIMARY);

        assertTrue(success);
    }

    /**
     * Test funding a wallet.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Skipping due to the lack of an available real payment method in tests.
    public void testFundWallet() throws EasyPostException {
        vcr.setUpTest("fund_wallet");

        boolean success = Billing.fundWallet("2000", PaymentMethod.Priority.PRIMARY);

        assertTrue(success);
    }

    /**
     * Test retrieving all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Skipping due to having to manually add and remove a payment method from the account.
    public void testRetrievePaymentMethods() throws EasyPostException {
        vcr.setUpTest("retrieve_payment_methods");

        PaymentMethod paymentMethods = Billing.retrievePaymentMethods();

        assertNotNull(paymentMethods.getPrimaryPaymentMethodObject());
        assertNotNull(paymentMethods.getSecondaryPaymentMethodObject());
    }
}
