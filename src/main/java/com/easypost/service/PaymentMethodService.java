package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.PaymentMethod;

public class PaymentMethodService {
    private final EasyPostClient client;

    /**
     * PaymentMethodService constructor.
     *
     * @param client The client object.
     */
    PaymentMethodService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * List all payment methods.
     *
     * @return Billing object.
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link com.easypost.service.BillingService#retrievePaymentMethods()} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public PaymentMethod all() throws EasyPostException {
        String endpoint = "payment_methods";

        PaymentMethod response =
                Requestor.request(RequestMethod.GET, endpoint, null, PaymentMethod.class, client);

        if (response.getId() == null) {
            throw new InvalidObjectError(Constants.ErrorMessages.NO_PAYMENT_METHODS);
        }

        return response;
    }
}
