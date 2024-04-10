package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BillingTest {
    private static TestUtils.VCR vcr;
    private String jsonResponse = "{\"id\":\"cust_...\",\"object\":\"PaymentMethods\",\"primary_" +
            "payment_method\":{\"id\":\"pm_...\",\"disabled_at\":null,\"object\":\"CreditCard\",\"na" +
            "me\":null,\"last4\":\"4242\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Visa\"},\"secondar" +
            "y_payment_method\":{\"id\":\"pm_...\",\"disabled_at\":null,\"object\":\"BankAccount\",\"name\":nu" +
            "ll,\"last4\":\"4444\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Mastercard\"}}";
    private PaymentMethod paymentMethod = Constants.Http.GSON.fromJson(jsonResponse, PaymentMethod.class);

    private String jsonResponseLegacyPrefixes = "{\"id\":\"cust_...\",\"object\":\"PaymentMethods\",\"primary_" +
            "payment_method\":{\"id\":\"card_...\",\"disabled_at\":null,\"object\":null,\"na" +
            "me\":null,\"last4\":\"4242\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Visa\"},\"secondar" +
            "y_payment_method\":{\"id\":\"bank_...\",\"disabled_at\":null,\"object\":null,\"name\":nu" +
            "ll,\"last4\":\"4444\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Mastercard\"}}";

    private PaymentMethod paymentMethodLegacyPrefixes =
            Constants.Http.GSON.fromJson(jsonResponseLegacyPrefixes, PaymentMethod.class);

    private static MockedStatic<Requestor> requestMock = Mockito.mockStatic(Requestor.class);

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
     * Release the static mock once it has been used.
     */
    @AfterAll
    public static void cleanup() {
        requestMock.close();
    }

    /**
     * Test deleting a payment method.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDeletePaymentMethod() throws EasyPostException {
        requestMock.when(() -> Requestor.request(
                RequestMethod.GET, "payment_methods", null, PaymentMethod.class, vcr.client))
                .thenReturn(paymentMethod);

        PaymentMethodObject paymentMethodObject =
                vcr.client.billing.retrievePaymentMethods().getSecondaryPaymentMethod();

        requestMock.when(() -> Requestor.request(RequestMethod.GET,
                paymentMethodObject.getEndpoint() + "/" + paymentMethodObject.getId(),
                null, PaymentMethod.class,
                vcr.client)).thenReturn(null);

        assertDoesNotThrow(() -> vcr.client.billing.deletePaymentMethod(PaymentMethod.Priority.SECONDARY));
    }

    /**
     * Test funding a wallet.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testFundWallet() throws EasyPostException {
        requestMock.when(() -> Requestor.request(
                RequestMethod.GET, "payment_methods", null, PaymentMethod.class, vcr.client))
                .thenReturn(paymentMethod);

        PaymentMethodObject paymentMethodObject = vcr.client.billing.retrievePaymentMethods().getPrimaryPaymentMethod();

        requestMock.when(() -> Requestor.request(RequestMethod.GET,
                paymentMethodObject.getEndpoint() + "/" + paymentMethodObject.getId() + "/charges", null,
                PaymentMethod.class, vcr.client)).thenReturn(paymentMethod);

        assertDoesNotThrow(() -> vcr.client.billing.fundWallet("2000"));
    }

    /**
     * Test retrieving all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrievePaymentMethods() throws EasyPostException {
        requestMock.when(() -> Requestor.request(
                RequestMethod.GET, "payment_methods", null, PaymentMethod.class, vcr.client))
                .thenReturn(paymentMethod);

        PaymentMethod paymentMethods = vcr.client.billing.retrievePaymentMethods();

        assertNotNull(paymentMethods.getPrimaryPaymentMethod());
        assertNotNull(paymentMethods.getSecondaryPaymentMethod());
    }

    /**
     * Test determining a payment method type by its object type.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDeterminePaymentMethodTypeByObjectType() throws EasyPostException {
        requestMock.when(() -> Requestor.request(
                RequestMethod.GET, "payment_methods", null, PaymentMethod.class, vcr.client))
                .thenReturn(paymentMethod);

        // Should be a credit card with "CreditCard" object type and "pm_" prefix
        PaymentMethodObject creditCard =
                vcr.client.billing.retrievePaymentMethods().getPrimaryPaymentMethod();
        assertTrue(creditCard.getId().startsWith("pm_"));
        assertEquals("CreditCard", creditCard.getObject());
        assertEquals(PaymentMethodObject.PaymentMethodType.CREDIT_CARD, creditCard.getType());

        // Should be a bank account with "BankAccount" object type and "pm_" prefix
        PaymentMethodObject bankAccount =
                vcr.client.billing.retrievePaymentMethods().getSecondaryPaymentMethod();
        assertTrue(bankAccount.getId().startsWith("pm_"));
        assertEquals("BankAccount", bankAccount.getObject());
        assertEquals(PaymentMethodObject.PaymentMethodType.BANK_ACCOUNT, bankAccount.getType());
    }
}
