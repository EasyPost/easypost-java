package com.easypost.service;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
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
        PaymentMethod response =
            Requestor.request(RequestMethod.GET, String.format("%s/%s", EasyPost.API_BASE, "payment_methods"), null,
                        PaymentMethod.class, client);

        if (response.getId() == null) {
            throw new InvalidObjectError(Constants.NO_PAYMENT_METHODS);
        }

        return response;
    }
}
