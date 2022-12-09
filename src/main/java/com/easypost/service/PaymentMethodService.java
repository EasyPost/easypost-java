package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.PaymentMethod;
import com.easypost.utils.InternalUtilities;

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
        String url = InternalUtilities.classURL(PaymentMethod.class);
        PaymentMethod response =
            Requestor.request(RequestMethod.GET, url, null, PaymentMethod.class, client);

        if (response.getId() == null) {
            throw new InvalidObjectError(Constants.ErrorMessages.NO_PAYMENT_METHODS);
        }

        return response;
    }
}
