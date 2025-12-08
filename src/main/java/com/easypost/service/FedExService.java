package com.easypost.service;

import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.FedExAccountValidationResponse;
import com.easypost.model.FedExGeneratePinResponse;

public class FedExService {
    private final EasyPostClient client;

    /**
     * FedExService constructor.
     *
     * @param client The client object.
     */
    FedExService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Verify a FedEx billing address via the API.
     *
     * @param fedexAccountNumber FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse verifyBillingAddress(final String fedexAccountNumber, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "fedex_registrations/" + fedexAccountNumber + "/address";

        return Requestor.request(RequestMethod.POST, endpoint, params, FedExAccountValidationResponse.class, client);
    }

    /**
     * Generate a pin to verify your FedEx account via the API.
     *
     * @param fedexAccountNumber FedEx account number.
     * @param pinMethod Method of delivery for the pin.
     * @return FedExGeneratePinResponse object
     * @throws EasyPostException when the request fails.
     */
    public FedExGeneratePinResponse generatePin(final String fedexAccountNumber, final String pinMethod) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        Map<String, Object> pinMethodMap = new HashMap<>();
        pinMethodMap.put("option", pinMethod);
        wrappedParams.put("pin_method", pinMethodMap);
        String endpoint = "fedex_registrations/" + fedexAccountNumber + "/pin";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedExGeneratePinResponse.class, client);
    }

    /**
     * Validates a pin for a FedEx account via the API.
     *
     * @param fedexAccountNumber FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse validatePin(final String fedexAccountNumber, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "fedex_registrations/" + fedexAccountNumber + "/pin/validate";

        return Requestor.request(RequestMethod.POST, endpoint, params, FedExAccountValidationResponse.class, client);
    }

    /**
     * Validates details of an invoice for a FedEx account via the API.
     *
     * @param fedexAccountNumber FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse validateInvoice(final String fedexAccountNumber, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "fedex_registrations/" + fedexAccountNumber + "/invoice";

        return Requestor.request(RequestMethod.POST, endpoint, params, FedExAccountValidationResponse.class, client);
    }
}
