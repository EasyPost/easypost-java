package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.PaymentMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        assertDoesNotThrow(() -> vcr.client.billing.deletePaymentMethod(PaymentMethod.Priority.PRIMARY));
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

        assertDoesNotThrow(() -> vcr.client.billing.fundWallet("2000", PaymentMethod.Priority.PRIMARY));
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

        PaymentMethod paymentMethods = vcr.client.billing.retrievePaymentMethods();

        assertNotNull(paymentMethods.getPrimaryPaymentMethodObject());
        assertNotNull(paymentMethods.getSecondaryPaymentMethodObject());
    }
}
