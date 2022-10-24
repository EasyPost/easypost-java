package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public final class Billing extends EasyPostResource {
    /**
     * Delete a payment method.
     *
     * @param priority Which type of payment method to delete.
     * @throws EasyPostException when the request fails.
     */
    public static void deletePaymentMethod(PaymentMethod.Priority priority) throws EasyPostException {
        deletePaymentMethod(priority, null);
    }

    /**
     * Delete a payment method.
     *
     * @param priority Which type of payment method to delete.
     * @param apiKey   API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public static void deletePaymentMethod(PaymentMethod.Priority priority, String apiKey) throws EasyPostException {
        PaymentMethodObject paymentMethodObject = getPaymentMethodByPriority(priority, apiKey);

        // will attempt to serialize empty JSON to a PaymentMethod.class, that's fine
        Requestor.request(RequestMethod.DELETE,
                String.format("%s/%s/%s", EasyPost.API_BASE, paymentMethodObject.getEndpoint(),
                        paymentMethodObject.getId()), null, PaymentMethod.class, apiKey);
    }

    /**
     * Fund your wallet from the primary payment method.
     *
     * @param amount amount to fund.
     * @throws EasyPostException when the request fails.
     */
    public static void fundWallet(String amount) throws EasyPostException {
        fundWallet(amount, PaymentMethod.Priority.PRIMARY, null);
    }

    /**
     * Fund your wallet from a specific payment method.
     *
     * @param amount   amount to fund.
     * @param priority which type of payment method to use to fund the wallet. Defaults to primary.
     * @throws EasyPostException when the request fails.
     */
    public static void fundWallet(String amount, PaymentMethod.Priority priority) throws EasyPostException {
        fundWallet(amount, priority, null);
    }

    /**
     * Fund your wallet from a specific payment method.
     *
     * @param amount   amount to fund.
     * @param priority which type of payment method to use to fund the wallet.
     * @param apiKey   API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public static void fundWallet(String amount, PaymentMethod.Priority priority, String apiKey)
            throws EasyPostException {
        PaymentMethodObject paymentMethodObject = getPaymentMethodByPriority(priority, apiKey);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);

        // will attempt to serialize empty JSON to a PaymentMethod.class, that's fine
        Requestor.request(RequestMethod.POST, String.format("%s/%s/%s/%s", EasyPost.API_BASE, 
            paymentMethodObject.getEndpoint(), paymentMethodObject.getId(), "charges"),
                params, PaymentMethod.class, apiKey);
    }

    /**
     * List all payment methods for this account.
     *
     * @return an EasyPost.PaymentMethod summary object.
     * @throws EasyPostException when the request fails or billing has not been set up.
     */
    public static PaymentMethod retrievePaymentMethods() throws EasyPostException {
        return retrievePaymentMethods(null);
    }

    /**
     * List all payment methods for this account.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return an EasyPost.PaymentMethod summary object.
     * @throws EasyPostException when the request fails or billing has not been set up.
     */
    public static PaymentMethod retrievePaymentMethods(String apiKey) throws EasyPostException {
        PaymentMethod response =
            Requestor.request(RequestMethod.GET, String.format("%s/%s", EasyPost.API_BASE, "payment_methods"), null,
                PaymentMethod.class, apiKey);

        if (response.getId() == null) {
            throw new InvalidObjectError(Constants.NO_PAYMENT_METHODS);
        }

        return response;
    }

    /**
     * Get a payment method by priority.
     *
     * @param priority which priority payment method to get.
     * @param apiKey   API key to use in request (overrides default API key).
     * @return an EasyPost.PaymentMethodObject instance.
     * @throws EasyPostException when the request fails.
     */
    private static PaymentMethodObject getPaymentMethodByPriority(PaymentMethod.Priority priority, String apiKey)
            throws EasyPostException {
        PaymentMethod paymentMethods = retrievePaymentMethods(apiKey);
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
