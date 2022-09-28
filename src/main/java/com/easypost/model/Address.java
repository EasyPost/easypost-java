package com.easypost.model;

import com.easypost.exception.EasyPostException;

import java.util.HashMap;
import java.util.Map;

public final class Address extends BaseAddress {

    private String mode;
    private String message;
    private String carrierFacility;
    private String federalTaxId;
    private Boolean residential;
    private AddressVerifications verifications;

    /**
     * Get carrier facility for address.
     *
     * @return address carrier facility
     */
    public String getCarrierFacility() {
        return carrierFacility;
    }

    /**
     * Set carrier facility for address.
     *
     * @param carrierFacility address carrier facility
     */
    public void setCarrierFacility(final String carrierFacility) {
        this.carrierFacility = carrierFacility;
    }

    /**
     * Get federal tax id of address.
     *
     * @return address federal tax id
     */
    public String getFederalTaxId() {
        return federalTaxId;
    }

    /**
     * Set federal tax id of address.
     *
     * @param federalTaxId address federal tax id
     */
    public void setFederalTaxId(final String federalTaxId) {
        this.federalTaxId = federalTaxId;
    }

    /**
     * Get address message.
     *
     * @return address message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set address message.
     *
     * @param message address message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get address mode.
     *
     * @return address mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set address mode.
     *
     * @param mode address mode
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get whether address is residential.
     *
     * @return whether address is residential
     */
    public Boolean getResidential() {
        return residential;
    }

    /**
     * Set whether address is residential.
     *
     * @param residential whether address is residential
     */
    public void setResidential(final Boolean residential) {
        this.residential = residential;
    }

    /**
     * Get verifications for address.
     *
     * @return address verifications
     */
    public AddressVerifications getVerifications() {
        return verifications;
    }

    /**
     * Set verifications for address.
     *
     * @param verifications address verifications
     */
    public void setVerifications(final AddressVerifications verifications) {
        this.verifications = verifications;
    }

    /**
     * Create Address object from parameter map.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create Address object from parameter map.
     *
     * @param params Map of address parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        if (params.containsKey("verify")) {
            wrappedParams.put("verify", params.remove("verify"));
        }

        if (params.containsKey("verify_strict")) {
            wrappedParams.put("verify_strict", params.remove("verify_strict"));
        }

        wrappedParams.put("address", params);

        return request(RequestMethod.POST, classURL(Address.class), wrappedParams, Address.class, apiKey);
    }

    /**
     * Retrieve Address object from API.
     *
     * @param id ID of address to retrieve.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve Address object from API.
     *
     * @param id     ID of address to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Address.class, id), null, Address.class, apiKey);
    }

    /**
     * List all Address objects.
     *
     * @param params Map of parameters.
     * @return AddressCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static AddressCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Address objects.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return AddressCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static AddressCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Address.class), params, AddressCollection.class, apiKey);
    }

    /**
     * Create Address object from parameter map and immediately verify it.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerify(final Map<String, Object> params) throws EasyPostException {
        return createAndVerify(params, null);
    }

    /**
     * Create Address object from parameter map and immediately verify it.
     *
     * @param params Map of address parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerify(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("address", params);

        AddressVerifyResponse response =
                request(RequestMethod.POST, String.format("%s/create_and_verify", classURL(Address.class)),
                        wrappedParams, AddressVerifyResponse.class, apiKey);

        return response.getAddress();
    }

    /**
     * Verify this Address object.
     *
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verify() throws EasyPostException {
        return this.verify(null);
    }

    /**
     * Verify this Address object.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verify(final String apiKey) throws EasyPostException {
        AddressVerifyResponse response;
        response =
                request(RequestMethod.GET, String.format("%s/verify", instanceURL(Address.class, this.getId())), null,
                        AddressVerifyResponse.class, apiKey);

        return response.getAddress();
    }
}
