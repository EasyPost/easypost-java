package com.easypost;

import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.EasyPostException;
import com.easypost.model.ClientSecret;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BetaReferralCustomerTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("beta_referral_customer", TestUtils.ApiKey.REFERRAL);
    }

    /**
     * Test add Stripe payment method for referral customer.
     *
     * @throws EasyPostException When the request fails.
     */
    @Test
    public void testAddPaymentMethod() throws EasyPostException {
        vcr.setUpTest("add_payment_method");
        InvalidRequestError exception = assertThrows(InvalidRequestError.class,
                () -> vcr.client.betaReferralCustomer.addPaymentMethod("cus_123", "ba_123"));

        assertEquals("BILLING.INVALID_PAYMENT_GATEWAY_REFERENCE", exception.getCode());
        assertEquals(422, exception.getStatusCode());
        assertEquals("Invalid connect integration.", exception.getMessage());
    }

    /**
     * Test refund by amount for a previous payment.
     *
     * @throws EasyPostException if an exception is thrown.
     */
    @Test
    public void testRefundByAmount() throws EasyPostException {
        vcr.setUpTest("refund_by_amount");
        InvalidRequestError exception =
                assertThrows(InvalidRequestError.class, () -> vcr.client.betaReferralCustomer.refundByAmount(2000));

        assertEquals("TRANSACTION.AMOUNT_INVALID", exception.getCode());
        assertEquals(422, exception.getStatusCode());
        assertEquals("Refund amount is invalid. Please use a valid amount or escalate to finance.",
                exception.getMessage());
    }

    /**
     * Test refund by payment log for a previous payment.
     *
     * @throws EasyPostException if an exception is thrown.
     */
    @Test
    public void testRefundByPaymentLogId() throws EasyPostException {
        vcr.setUpTest("refund_by_payment_log_id");
        InvalidRequestError exception = assertThrows(InvalidRequestError.class,
                () -> vcr.client.betaReferralCustomer.refundByPaymentLog("paylog_123"));

        assertEquals("TRANSACTION.DOES_NOT_EXIST", exception.getCode());
        assertEquals(422, exception.getStatusCode());
        assertEquals("We could not find a transaction with that id.", exception.getMessage());
    }

    /**
     * Test creating a client secret for credit cards.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateCreditCardClientSecret() throws EasyPostException {
        vcr.setUpTest("create_credit_card_client_secret");

        ClientSecret response = vcr.client.betaReferralCustomer.createCreditCardClientSecret();

        assertTrue(response.getClientSecret().startsWith("seti_"));
    }

    /**
     * Test creating a client secret for bank accounts.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateBankAccountClientSecret() throws EasyPostException {
        vcr.setUpTest("create_bank_account_client_secret");

        ClientSecret response = vcr.client.betaReferralCustomer.createBankAccountClientSecret();

        assertTrue(response.getClientSecret().startsWith("fcsess_client_secret_"));
    }
}
