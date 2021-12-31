/**
 * CarrierAccount.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CarrierAccount extends EasyPostResource {
    public String id;
    private String readable;
    private String description;
    private String reference;
    private Map<String, Object> credentials;

    /**
     * Get ID of the carrier account.
     *
     * @return ID of the carrier account.
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID of the carrier account.
     *
     * @param id ID of the carrier account.
     */
    public void setId(final String id) {
        this.id = id;
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @return updated CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        CarrierAccount response =
                request(RequestMethod.PUT, instanceURL(CarrierAccount.class, this.getId()), wrappedParams,
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, instanceURL(CarrierAccount.class, this.getId()), null, CarrierAccount.class,
                apiKey);
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @return created CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount create(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        return request(RequestMethod.POST, classURL(CarrierAccount.class), wrappedParams, CarrierAccount.class, apiKey);
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @return CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public static CarrierAccount retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CarrierAccount.class, id), null, CarrierAccount.class, apiKey);
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
     * List all carrier accounts.
     *
     * @param params filters to apply to the list.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierAccount> all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        CarrierAccount[] response =
                request(RequestMethod.GET, classURL(CarrierAccount.class), params, CarrierAccount[].class, apiKey);
        return Arrays.asList(response);
    }
}
