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

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(final String readable) {
        this.readable = readable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    public Map<String, Object> getCredentials() {
        return credentials;
    }

    public void setCredentials(final Map<String, Object> credentials) {
        this.credentials = credentials;
    }

    // update
    public CarrierAccount update(final Map<String, Object> params)
            throws EasyPostException {
        return this.update(params, null);
    }

    public CarrierAccount update(final Map<String, Object> params,
                                 final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        CarrierAccount response = request(RequestMethod.PUT,
                instanceURL(CarrierAccount.class, this.getId()), wrappedParams,
                CarrierAccount.class, apiKey);

        this.merge(this, response);
        return this;
    }

    // delete
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    public void delete(final String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE,
                instanceURL(CarrierAccount.class, this.getId()), null,
                CarrierAccount.class, apiKey);
    }

    // create
    public static CarrierAccount create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static CarrierAccount create(final Map<String, Object> params,
                                        final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier_account", params);

        return request(RequestMethod.POST, classURL(CarrierAccount.class),
                wrappedParams, CarrierAccount.class, apiKey);
    }

    // retrieve
    public static CarrierAccount retrieve(final String id)
            throws EasyPostException {
        return retrieve(id, null);
    }

    public static CarrierAccount retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CarrierAccount.class, id),
                null, CarrierAccount.class, apiKey);
    }

    // all
    public static List<CarrierAccount> all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static List<CarrierAccount> all(final Map<String, Object> params,
                                           final String apiKey)
            throws EasyPostException {
        CarrierAccount[] response =
                request(RequestMethod.GET, classURL(CarrierAccount.class),
                        params, CarrierAccount[].class, apiKey);
        return Arrays.asList(response);
    }


}
