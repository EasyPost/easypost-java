package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.FedExAccountValidationResponse;
import com.easypost.model.FedexRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FedexRegistrationService {
    private final EasyPostClient client;

    /**
     * FedexRegistrationService constructor.
     *
     * @param client The client object.
     */
    FedexRegistrationService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Register the billing address for a FedEx account.
     * Advanced method for custom parameter structures.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object with next steps (PIN or invoice validation).
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse registerAddress(final String fedexAccountNumber,
            final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = wrapAddressValidation(params);
        String endpoint = String.format("fedex_registrations/%s/address", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedExAccountValidationResponse.class,
                client);
    }

    /**
     * Request a PIN for FedEx account verification.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param pinMethodOption The PIN delivery method: "SMS", "CALL", or "EMAIL".
     * @return FedExRequestPinResponse object confirming PIN was sent.
     * @throws EasyPostException when the request fails.
     */
    public FedExRequestPinResponse requestPin(final String fedexAccountNumber, final String pinMethodOption)
            throws EasyPostException {
        Map<String, Object> pinMethod = new HashMap<>();
        pinMethod.put("option", pinMethodOption);

        Map<String, Object> params = new HashMap<>();
        params.put("pin_method", pinMethod);

        return requestPin(fedexAccountNumber, params);
    }

    /**
     * Validate the PIN entered by the user for FedEx account verification.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object.
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse validatePin(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> wrappedParams = wrapPinValidation(params);
        String endpoint = String.format("fedex_registrations/%s/pin/validate", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedexRegistration.class, client);
    }

    /**
     * Submit invoice information to complete FedEx account registration.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params Map of parameters.
     * @return FedExAccountValidationResponse object.
     * @throws EasyPostException when the request fails.
     */
    public FedExAccountValidationResponse submitInvoice(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> wrappedParams = wrapInvoiceValidation(params);
        String endpoint = String.format("fedex_registrations/%s/invoice", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedexRegistration.class, client);
    }

    /**
     * Wraps address validation parameters and ensures the "name" field exists.
     * If not present, generates a UUID (with hyphens removed) as the name.
     *
     * @param params The original parameters map.
     * @return A new map with properly wrapped address_validation and easypost_details.
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> wrapAddressValidation(final Map<String, Object> params) {
        Map<String, Object> wrappedParams = new HashMap<>();

        if (params.containsKey("address_validation")) {
            Map<String, Object> addressValidation = new HashMap<>(
                (Map<String, Object>) params.get("address_validation"));
            ensureNameField(addressValidation);
            wrappedParams.put("address_validation", addressValidation);
        }

        if (params.containsKey("easypost_details")) {
            wrappedParams.put("easypost_details", params.get("easypost_details"));
        }

        return wrappedParams;
    }

    /**
     * Wraps PIN validation parameters and ensures the "name" field exists.
     * If not present, generates a UUID (with hyphens removed) as the name.
     *
     * @param params The original parameters map.
     * @return A new map with properly wrapped pin_validation and easypost_details.
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> wrapPinValidation(final Map<String, Object> params) {
        Map<String, Object> wrappedParams = new HashMap<>();

        if (params.containsKey("pin_validation")) {
            Map<String, Object> pinValidation = new HashMap<>(
                (Map<String, Object>) params.get("pin_validation"));
            ensureNameField(pinValidation);
            wrappedParams.put("pin_validation", pinValidation);
        }

        if (params.containsKey("easypost_details")) {
            wrappedParams.put("easypost_details", params.get("easypost_details"));
        }

        return wrappedParams;
    }

    /**
     * Wraps invoice validation parameters and ensures the "name" field exists.
     * If not present, generates a UUID (with hyphens removed) as the name.
     *
     * @param params The original parameters map.
     * @return A new map with properly wrapped invoice_validation and easypost_details.
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> wrapInvoiceValidation(final Map<String, Object> params) {
        Map<String, Object> wrappedParams = new HashMap<>();

        if (params.containsKey("invoice_validation")) {
            Map<String, Object> invoiceValidation = new HashMap<>(
                (Map<String, Object>) params.get("invoice_validation"));
            ensureNameField(invoiceValidation);
            wrappedParams.put("invoice_validation", invoiceValidation);
        }

        if (params.containsKey("easypost_details")) {
            wrappedParams.put("easypost_details", params.get("easypost_details"));
        }

        return wrappedParams;
    }

    /**
     * Ensures the "name" field exists in the provided map.
     * If not present, generates a UUID (with hyphens removed) as the name.
     * This follows the pattern used in the web UI implementation.
     *
     * @param map The map to ensure the "name" field in.
     */
    private void ensureNameField(final Map<String, Object> map) {
        if (!map.containsKey("name") || map.get("name") == null) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            map.put("name", uuid);
        }
    }
}
