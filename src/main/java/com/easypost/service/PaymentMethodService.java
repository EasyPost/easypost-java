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
}
