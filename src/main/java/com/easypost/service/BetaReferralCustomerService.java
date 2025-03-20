package com.easypost.service;

import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.BetaPaymentRefund;
import com.easypost.model.ClientSecret;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;

public class BetaReferralCustomerService {
    private final EasyPostClient client;

    /**
     * BetaReferralCustomerService constructor.
     * 
     * @param client The client object.
     */
    BetaReferralCustomerService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Add Stripe payment method to referral customer.
     *
     * @param stripeCustomerId       ID of the Stripe account.
     * @param paymentMethodReference Reference of Stripe payment method.
     * @return PaymentMethodObject object.
     * @throws EasyPostException     When the request fails.
     */
    public PaymentMethodObject addPaymentMethod(String stripeCustomerId, String paymentMethodReference)
            throws EasyPostException {
        return addPaymentMethod(stripeCustomerId, paymentMethodReference, PaymentMethod.Priority.PRIMARY);
    }

    /**
     * Add Stripe payment method to referral customer.
     *
     * @param stripeCustomerId       ID of the Stripe account.
     * @param paymentMethodReference Reference of Stripe payment method.
     * @param primaryOrSecondary     Primary or secondary of this payment method.
     * @return PaymentMethodObject object.
     * @throws EasyPostException     When the request fails.
     */
    public PaymentMethodObject addPaymentMethod(String stripeCustomerId, String paymentMethodReference,
            PaymentMethod.Priority primaryOrSecondary) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("stripe_customer_id", stripeCustomerId);
        params.put("payment_method_reference", paymentMethodReference);
        params.put("priority", primaryOrSecondary);

        HashMap<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("payment_method", params);

        String endpoint = "referral_customers/payment_method";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams,
                PaymentMethodObject.class, client, "beta");
    }

    /**
     * Refund by amount for a recent payment.
     *
     * @param refundAmount Amount to be refunded by cents.
     * @return BetaPaymentRefund object.
     * @throws EasyPostException When the request fails.
     */
    public BetaPaymentRefund refundByAmount(int refundAmount) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refund_amount", refundAmount);

        String endpoint = "referral_customers/refunds";

        return Requestor.request(RequestMethod.POST, endpoint, params,
                BetaPaymentRefund.class, client, "beta");
    }

    /**
     * Refund a payment by a payment log ID.
     *
     * @param paymentLogId ID of the payment log.
     * @return BetaPaymentRefund object.
     * @throws EasyPostException When the request fails.
     */
    public BetaPaymentRefund refundByPaymentLog(String paymentLogId) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("payment_log_id", paymentLogId);

        String endpoint = "referral_customers/refunds";

        return Requestor.request(RequestMethod.POST, endpoint, params,
                BetaPaymentRefund.class, client, "beta");
    }

    /**
     * Creates a client secret to use with Stripe when adding a credit card.
     *
     * @return ClientSecret containing the client secret.
     * @throws EasyPostException When the request fails.
     */
    public ClientSecret createCreditCardClientSecret() throws EasyPostException {
        String endpoint = "setup_intents";

        return Requestor.request(RequestMethod.POST, endpoint, null, ClientSecret.class, client, "beta");
    }

    /**
     * Creates a client secret to use with Stripe when adding a bank account.
     *
     * @return ClientSecret containing the client secret.
     * @throws EasyPostException When the request fails.
     */
    public ClientSecret createBankAccountClientSecret() throws EasyPostException {
        return createBankAccountClientSecret(null);
    }

    /**
     * Creates a client secret to use with Stripe when adding a bank account.
     *
     * @param returnUrl Optional return URL for the bank account setup.
     * @return ClientSecret containing the client secret.
     * @throws EasyPostException When the request fails.
     */
    public ClientSecret createBankAccountClientSecret(String returnUrl) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        if (returnUrl != null) {
            params.put("return_url", returnUrl);
        }

        String endpoint = "financial_connections_sessions";

        return Requestor.request(RequestMethod.POST, endpoint, params, ClientSecret.class, client, "beta");
    }
}
