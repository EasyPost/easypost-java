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
     * Convenience method that automatically wraps parameters in the required structure.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param name               The account name (use a unique identifier).
     * @param street1            Street address line 1.
     * @param city               City.
     * @param state              State/province code.
     * @param postalCode         Postal/ZIP code.
     * @param countryCode        Country code (e.g., "US").
     * @param carrierAccountId   EasyPost carrier account ID to update.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration registerAddress(final String fedexAccountNumber, final String name, final String street1,
            final String city, final String state, final String postalCode, final String countryCode,
            final String carrierAccountId) throws EasyPostException {
        Map<String, Object> addressValidation = new HashMap<>();
        addressValidation.put("name", name);
        addressValidation.put("street1", street1);
        addressValidation.put("city", city);
        addressValidation.put("state", state);
        addressValidation.put("postal_code", postalCode);
        addressValidation.put("country_code", countryCode);

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", carrierAccountId);

        Map<String, Object> params = new HashMap<>();
        params.put("address_validation", addressValidation);
        params.put("easypost_details", easypostDetails);

        return registerAddress(fedexAccountNumber, params);
    }

    /**
     * Register the billing address for a FedEx account.
     * Advanced method for custom parameter structures.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters containing "address_validation" with address fields
     *                           (name, street1, city, state, postal_code, country_code).
     *                           If "address_validation.name" is not provided, a UUID will be
     *                           auto-generated.
     *                           Optional: "easypost_details" object with "carrier_account_id".
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration registerAddress(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> wrappedParams = wrapAddressValidation(params);
        String endpoint = String.format("fedex_registrations/%s/address", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedexRegistration.class, client);
    }

    /**
     * Request a PIN for FedEx account verification.
     * Convenience method that automatically wraps the PIN method in the required structure.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param pinMethodOption    The PIN delivery method: "SMS", "CALL", or "EMAIL".
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration requestPin(final String fedexAccountNumber, final String pinMethodOption)
            throws EasyPostException {
        Map<String, Object> pinMethod = new HashMap<>();
        pinMethod.put("option", pinMethodOption);

        Map<String, Object> params = new HashMap<>();
        params.put("pin_method", pinMethod);

        return requestPin(fedexAccountNumber, params);
    }

    /**
     * Request a PIN for FedEx account verification.
     * Advanced method for custom parameter structures.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters containing "pin_method" with "option" field.
     *                           The "option" value must be one of "SMS", "CALL", or "EMAIL".
     *                           Example: {"pin_method": {"option": "SMS"}}
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration requestPin(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        String endpoint = String.format("fedex_registrations/%s/pin", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, params, FedexRegistration.class, client);
    }

    /**
     * Validate the PIN entered by the user for FedEx account verification.
     * Convenience method that automatically wraps parameters in the required structure.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param pinCode            The PIN code received by the user.
     * @param name               The account name (use a unique identifier).
     * @param carrierAccountId   EasyPost carrier account ID to update.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration validatePin(final String fedexAccountNumber, final String pinCode, final String name,
            final String carrierAccountId) throws EasyPostException {
        Map<String, Object> pinValidation = new HashMap<>();
        pinValidation.put("pin_code", pinCode);
        pinValidation.put("name", name);

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", carrierAccountId);

        Map<String, Object> params = new HashMap<>();
        params.put("pin_validation", pinValidation);
        params.put("easypost_details", easypostDetails);

        return validatePin(fedexAccountNumber, params);
    }

    /**
     * Validate the PIN entered by the user for FedEx account verification.
     * Advanced method for custom parameter structures.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters containing "pin_validation" with "pin_code" and
     *                           "name" fields. If "pin_validation.name" is not provided, a UUID will be
     *                           auto-generated.
     *                           Optional: "easypost_details" object with "carrier_account_id".
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration validatePin(final String fedexAccountNumber, final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> wrappedParams = wrapPinValidation(params);
        String endpoint = String.format("fedex_registrations/%s/pin/validate", fedexAccountNumber);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, FedexRegistration.class, client);
    }

    /**
     * Submit invoice information to complete FedEx account registration.
     * Convenience method that automatically wraps parameters in the required structure.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param name               The account name (use a unique identifier).
     * @param invoiceNumber      The invoice number.
     * @param invoiceDate        The invoice date (format: YYYY-MM-DD).
     * @param invoiceAmount      The invoice amount (e.g., "100.00").
     * @param invoiceCurrency    The invoice currency code (e.g., "USD").
     * @param carrierAccountId   EasyPost carrier account ID to update.
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration submitInvoice(final String fedexAccountNumber, final String name,
            final String invoiceNumber, final String invoiceDate, final String invoiceAmount,
            final String invoiceCurrency, final String carrierAccountId) throws EasyPostException {
        Map<String, Object> invoiceValidation = new HashMap<>();
        invoiceValidation.put("name", name);
        invoiceValidation.put("invoice_number", invoiceNumber);
        invoiceValidation.put("invoice_date", invoiceDate);
        invoiceValidation.put("invoice_amount", invoiceAmount);
        invoiceValidation.put("invoice_currency", invoiceCurrency);

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", carrierAccountId);

        Map<String, Object> params = new HashMap<>();
        params.put("invoice_validation", invoiceValidation);
        params.put("easypost_details", easypostDetails);

        return submitInvoice(fedexAccountNumber, params);
    }

    /**
     * Submit invoice information to complete FedEx account registration.
     * Advanced method for custom parameter structures.
     *
     * @param fedexAccountNumber The FedEx account number.
     * @param params             Map of parameters containing "invoice_validation" with invoice fields
     *                           (name, invoice_number, invoice_date, invoice_amount, invoice_currency).
     *                           If "invoice_validation.name" is not provided, a UUID will be
     *                           auto-generated.
     *                           Optional: "easypost_details" object with "carrier_account_id".
     * @return FedexRegistration object.
     * @throws EasyPostException when the request fails.
     */
    public FedexRegistration submitInvoice(final String fedexAccountNumber, final Map<String, Object> params)
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
