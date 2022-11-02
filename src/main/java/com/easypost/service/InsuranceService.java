package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Insurance;
import com.easypost.model.InsuranceCollection;
import com.easypost.utils.Utilities;

import java.util.HashMap;
import java.util.Map;

public class InsuranceService {
    private final EasyPostClient client;

    /**
     * InsuranceService constructor.
     * 
     * @param client The client object.
     */
    InsuranceService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a new Insurance object from a map of parameters.
     *
     * @param params Map of parameters.
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("insurance", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Insurance.class), wrappedParams,
                Insurance.class, client);
    }

    /**
     * Retrieve an Insurance from the API.
     *
     * @param id The ID of the Insurance to retrieve.
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Insurance.class, id), null, Insurance.class,
                client);
    }

    /**
     * Get a list of Insurances.
     *
     * @param params a map of parameters
     * @return InsuranceCollection object
     * @throws EasyPostException when the request fails.
     */
    public InsuranceCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Insurance.class),
                params, InsuranceCollection.class, client);
    }

    /**
     * Refresh this Insurance.
     *
     * @param id The ID of insurance.
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh(String id) throws EasyPostException {
        return this.refresh(null, id);
    }

    /**
     * Refresh this Insurance.
     *
     * @param params Map of parameters.
     * @param id The ID of insurance.
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET,
                String.format("%s", Utilities.instanceURL(Insurance.class, id)), params, Insurance.class,
                client);
    }
}
