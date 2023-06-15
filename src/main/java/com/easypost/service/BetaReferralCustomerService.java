package com.easypost.service;

import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.BetaPaymentRefund;
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
     * @throws EasyPostException
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
     * @throws EasyPostException
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

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams,
                PaymentMethodObject.class, "beta");
    }

    /**
     * Refund by amount for a recent payment.
     *
     * @param refundAmount Amount to be refunded by cents.
     * @return BetaPaymentRefund object.
     * @throws EasyPostException
     */
    public BetaPaymentRefund refundByAmount(int refundAmount) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refund_amount", refundAmount);

        String endpoint = "referral_customers/refunds";

        return this.client.request(RequestMethod.POST, endpoint, params,
                BetaPaymentRefund.class, "beta");
    }

    /**
     * Refund a payment by a payment log ID.
     *
     * @param paymentLogId ID of the payment log.
     * @return BetaPaymentRefund object.
     * @throws EasyPostException
     */
    public BetaPaymentRefund refundByPaymentLog(String paymentLogId) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("payment_log_id", paymentLogId);

        String endpoint = "referral_customers/refunds";

        return this.client.request(RequestMethod.POST, endpoint, params,
                BetaPaymentRefund.class, "beta");
    }
}
