package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
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
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters for address registration.
     *                           If params does not contain "name", a UUID will be auto-generated.
     *                           Optional: "easypost_details" object for additional metadata like
     *                           "reference" or "description".
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration registerAddress(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> processedParams = ensureNameParameter(params);
        String endpoint = String.format("fedex_registrations/%s/address", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, processedParams, FedexRegistration.class, client);
    }

    /**
     * Request a PIN for FedEx account verification.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters for PIN request.
     *                           Required: "pin_method" - one of "SMS", "CALL", or "EMAIL".
     *                           If params does not contain "name", a UUID will be auto-generated.
     *                           Optional: "easypost_details" object for additional metadata.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration requestPin(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> processedParams = ensureNameParameter(params);
        String endpoint = String.format("fedex_registrations/%s/pin", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, processedParams, FedexRegistration.class, client);
    }

    /**
     * Validate the PIN entered by the user for FedEx account verification.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters for PIN validation.
     *                           Required: PIN value provided by the user.
     *                           If params does not contain "name", a UUID will be auto-generated.
     *                           Optional: "easypost_details" object for additional metadata.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration validatePin(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> processedParams = ensureNameParameter(params);
        String endpoint = String.format("fedex_registrations/%s/pin/validate", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, processedParams, FedexRegistration.class, client);
    }

    /**
     * Submit invoice information to complete FedEx account registration.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters for invoice submission.
     *                           Required: Invoice information.
     *                           If params does not contain "name", a UUID will be auto-generated.
     *                           Optional: "easypost_details" object for additional metadata.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration submitInvoice(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> processedParams = ensureNameParameter(params);
        String endpoint = String.format("fedex_registrations/%s/invoice", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, processedParams, FedexRegistration.class, client);
    }

    /**
     * Ensures the "name" parameter exists in the params map.
     * If not present, generates a UUID (with hyphens removed) as the name.
     * This follows the pattern used in the web UI implementation.
     *
     * @param params The original parameters map.
     * @return A new map with the "name" parameter ensured.
     */
    private Map<String, Object> ensureNameParameter(final Map<String, Object> params) {
        Map<String, Object> processedParams = new HashMap<>(params);
        if (!processedParams.containsKey("name") || processedParams.get("name") == null) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            processedParams.put("name", uuid);
        }
        return processedParams;
    }
}
