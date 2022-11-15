package com.easypost.service;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;

import java.util.HashMap;
import java.util.Map;

public class BillingService {
    private final EasyPostClient client;

    /**
     * BillingService constructor.
     * 
     * @param client The client object.
     */
    BillingService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Delete a payment method.
     *
     * @param priority Which type of payment method to delete.
     * @throws EasyPostException when the request fails.
     */
    public void deletePaymentMethod(PaymentMethod.Priority priority) throws EasyPostException {
        PaymentMethodObject paymentMethodObject = getPaymentMethodByPriority(priority);
        String url = String.format("%s/%s/%s/%s", client.getApiBase(), client.getApiVersion(),
                paymentMethodObject.getEndpoint(), paymentMethodObject.getId());

        // will attempt to serialize empty JSON to a PaymentMethod.class, that's fine
        Requestor.request(RequestMethod.DELETE, url, null, PaymentMethod.class, client);
    }

    /**
     * Fund your wallet from the primary payment method.
     *
     * @param amount Amount to fund.
     * @throws EasyPostException when the request fails.
     */
    public void fundWallet(String amount) throws EasyPostException {
        fundWallet(amount, PaymentMethod.Priority.PRIMARY);
    }

    /**
     * Fund your wallet from a specific payment method.
     *
     * @param amount   Amount to fund.
     * @param priority which type of payment method to use to fund the wallet.
     * @throws EasyPostException when the request fails.
     */
    public void fundWallet(String amount, PaymentMethod.Priority priority)
            throws EasyPostException {
        PaymentMethodObject paymentMethodObject = getPaymentMethodByPriority(priority);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);

        String url = String.format("%s/%s/%s/%s/%s", client.getApiBase(), client.getApiVersion(),
                paymentMethodObject.getEndpoint(), paymentMethodObject.getId(), "charges");

        // will attempt to serialize empty JSON to a PaymentMethod.class, that's fine
        Requestor.request(RequestMethod.POST, url, params, PaymentMethod.class, client);
    }

    /**
     * List all payment methods for this account.
     *
     * @return an EasyPost.PaymentMethod summary object.
     * @throws EasyPostException when the request fails or billing has not been set
     *                           up.
     */
    public PaymentMethod retrievePaymentMethods() throws EasyPostException {
        String url = String.format("%s/%s/%s", client.getApiBase(), client.getApiVersion(), "payment_methods");
        PaymentMethod response = Requestor.request(RequestMethod.GET, url, null, PaymentMethod.class, client);

        if (response.getId() == null) {
            throw new InvalidObjectError(Constants.NO_PAYMENT_METHODS);
        }

        return response;
    }

    /**
     * Get a payment method by priority.
     *
     * @param priority Which priority payment method to get.
     * @return an EasyPost.PaymentMethodObject instance.
     * @throws EasyPostException when the request fails.
     */
    private PaymentMethodObject getPaymentMethodByPriority(PaymentMethod.Priority priority)
            throws EasyPostException {
        PaymentMethod paymentMethods = retrievePaymentMethods();
        PaymentMethodObject paymentMethod = null;
        switch (priority) {
            case PRIMARY:
                paymentMethod = paymentMethods.getPrimaryPaymentMethodObject();
                break;
            case SECONDARY:
                paymentMethod = paymentMethods.getSecondaryPaymentMethodObject();
                break;
            default:
                break;
        }

        if (paymentMethod == null || paymentMethod.getId() == null) {
            throw new InvalidObjectError(Constants.NO_PAYMENT_METHODS);
        }

        return paymentMethod;
    }
}
