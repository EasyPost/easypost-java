package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CarrierAccount extends EasyPostResource {
    private String object;
    private String type;
    private Fields fields;
    private boolean clone;
    private String readable;
    private String description;
    private String reference;
    private String billingType;
    private Map<String, Object> credentials;
    private Map<String, Object> testCredentials;

    /**
     * Get billing type of the carrier account.
     *
     * @return billing type of the carrier account.
     */
    public String getBillingType() {
        return billingType;
    }

    /**
     * Set billing type of the carrier account.
     *
     * @param billingType billing type of the carrier account.
     */
    public void setBillingType(final String billingType) {
        this.billingType = billingType;
    }

    /**
     * Get credentials of the carrier account.
     *
     * @return credentials of the carrier account.
     */
    public Map<String, Object> getCredentials() {
        return credentials;
    }

    /**
     * Set credentials of the carrier account.
     *
     * @param credentials credentials of the carrier account.
     */
    public void setCredentials(final Map<String, Object> credentials) {
        this.credentials = credentials;
    }

    /**
     * Get description of the carrier account.
     *
     * @return description of the carrier account.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the carrier account.
     *
     * @param description description of the carrier account.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get readable name of the carrier account.
     *
     * @return readable name of the carrier account.
     */
    public String getReadable() {
        return readable;
    }

    /**
     * Set readable name of the carrier account.
     *
     * @param readable readable name of the carrier account.
     */
    public void setReadable(final String readable) {
        this.readable = readable;
    }

    /**
     * Get reference of the carrier account.
     *
     * @return reference of the carrier account.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set reference of the carrier account.
     *
     * @param reference reference of the carrier account.
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Create a carrier account.
     *
     * @param params parameters to create.
     * @return created CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a carrier account.
     *
     * @param params parameters to create.
     * @param apiKey API key to use in request (overrides default API key).
     * @return created CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount create(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        return Requestor.request(RequestMethod.POST, classURL(CarrierAccount.class),
            wrappedParams, CarrierAccount.class, apiKey);
    }

    /**
     * Retrieve a carrier account from the API.
     *
     * @param id id of the carrier account.
     * @return CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a carrier account from the API.
     *
     * @param id     id of the carrier account.
     * @param apiKey API key to use in request (overrides default API key).
     * @return CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(CarrierAccount.class, id),
            null, CarrierAccount.class, apiKey);
    }

    /**
     * List all carrier accounts.
     *
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierAccount> all() throws EasyPostException {
        return all(null, null);
    }

    /**
     * List all carrier accounts.
     *
     * @param params filters to apply to the list.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierAccount> all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        CarrierAccount[] response = Requestor.request(RequestMethod.GET,
            classURL(CarrierAccount.class), params, CarrierAccount[].class, apiKey);
        
        return Arrays.asList(response);
    }

    /**
     * List all carrier accounts.
     *
     * @param params filters to apply to the list.
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierAccount> all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get object type of the carrier account.
     *
     * @return object type of the carrier account.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set object type of the carrier account.
     *
     * @param object object type of the carrier account.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get type of the carrier account.
     *
     * @return type of the carrier account.
     */
    public String getType() {
        return type;
    }

    /**
     * Set type of the carrier account.
     *
     * @param type type of the carrier account.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get fields of the carrier account.
     *
     * @return fields of the carrier account.
     */
    public Fields getFields() {
        return fields;
    }

    /**
     * Set fields of the carrier account.
     *
     * @param fields fields of the carrier account.
     */
    public void setFields(Fields fields) {
        this.fields = fields;
    }

    /**
     * Get whether the carrier account is a clone.
     *
     * @return True if carrier account is a clone, false otherwise.
     */
    public boolean isClone() {
        return clone;
    }

    /**
     * Set whether the carrier account is a clone.
     *
     * @param clone True if carrier account is a clone, false otherwise.
     */
    public void setClone(boolean clone) {
        this.clone = clone;
    }

    /**
     * Get test credentials of the carrier account.
     *
     * @return test credentials of the carrier account.
     */
    public Map<String, Object> getTestCredentials() {
        return testCredentials;
    }

    /**
     * Set test credentials of the carrier account.
     *
     * @param testCredentials test credentials of the carrier account.
     */
    public void setTestCredentials(Map<String, Object> testCredentials) {
        this.testCredentials = testCredentials;
    }

    /**
     * Update this carrier account.
     *
     * @param params parameters to update.
     * @return updated carrier account.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount update(final Map<String, Object> params) throws EasyPostException {
        return this.update(params, null);
    }

    /**
     * Update this carrier account.
     *
     * @param params parameters to update.
     * @param apiKey API key to use in request (overrides default API key).
     * @return updated CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        CarrierAccount response =
            Requestor.request(RequestMethod.PUT, instanceURL(CarrierAccount.class, this.getId()), wrappedParams,
                CarrierAccount.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Delete this carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    /**
     * Delete this carrier account.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String apiKey) throws EasyPostException {
        Requestor.request(RequestMethod.DELETE, instanceURL(CarrierAccount.class, this.getId()),
            null, CarrierAccount.class, apiKey);
    }
}
