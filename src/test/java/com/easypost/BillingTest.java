package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.mocking.MockClient;
import com.easypost.mocking.MockRequest;
import com.easypost.mocking.MockRequestMatchRules;
import com.easypost.mocking.MockResponse;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class BillingTest {
    private final String retrievePaymentMethodResponseJson =
            "{\"id\":\"cust_...\",\"object\":\"PaymentMethods\",\"primary_" +
                    "payment_method\":{\"id\":\"card_...\",\"disabled_at\":null,\"object\":\"CreditCard\",\"na" +
                    "me\":null,\"last4\":\"4242\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Visa\"},\"secondary_" +
                    "payment_method\":{\"id\":\"card_...\",\"disabled_at\":null,\"object\":\"CreditCard\",\"name\":nu" +
                    "ll,\"last4\":\"4444\",\"exp_month\":1,\"exp_year\":2025,\"brand\":\"Mastercard\"}}";

    /**
     * Test deleting a payment method.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDeletePaymentMethod() throws EasyPostException {
        MockClient mockClient = new MockClient();

        mockClient.addRequest(new MockRequest(new MockRequestMatchRules(RequestMethod.GET, ".*payment_methods$"),
                new MockResponse(200, retrievePaymentMethodResponseJson)));

        PaymentMethodObject paymentMethodObject =
                mockClient.billing.retrievePaymentMethods().getSecondaryPaymentMethod();

        mockClient.addRequest(new MockRequest(new MockRequestMatchRules(RequestMethod.DELETE,
                ".*" + paymentMethodObject.getEndpoint() + "/" + paymentMethodObject.getId() + "$"),
                new MockResponse(200, null)));

        assertDoesNotThrow(() -> mockClient.billing.deletePaymentMethod(PaymentMethod.Priority.SECONDARY));
    }

    /**
     * Test funding a wallet.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testFundWallet() throws EasyPostException {
        MockClient mockClient = new MockClient();

        mockClient.addRequest(new MockRequest(new MockRequestMatchRules(RequestMethod.GET, ".*payment_methods$"),
                new MockResponse(200, retrievePaymentMethodResponseJson)));

        PaymentMethodObject paymentMethodObject = mockClient.billing.retrievePaymentMethods().getPrimaryPaymentMethod();

        mockClient.addRequest(new MockRequest(new MockRequestMatchRules(RequestMethod.POST,
                ".*" + paymentMethodObject.getEndpoint() + "/" + paymentMethodObject.getId() + "/charges$"),
                new MockResponse(200, retrievePaymentMethodResponseJson)));

        assertDoesNotThrow(() -> mockClient.billing.fundWallet("2000"));
    }

    /**
     * Test retrieving all payment methods.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrievePaymentMethods() throws EasyPostException {
        MockClient mockClient = new MockClient();

        mockClient.addRequest(new MockRequest(new MockRequestMatchRules(RequestMethod.GET, ".*payment_methods$"),
                new MockResponse(200, retrievePaymentMethodResponseJson)));

        PaymentMethod paymentMethods = mockClient.billing.retrievePaymentMethods();

        assertNotNull(paymentMethods.getPrimaryPaymentMethod());
        assertNotNull(paymentMethods.getSecondaryPaymentMethod());
    }
}
