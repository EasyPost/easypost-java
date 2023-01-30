package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Refund;
import com.easypost.model.RefundCollection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefundService {
    private final EasyPostClient client;

    /**
     * RefundService constructor.
     *
     * @param client The client object.
     */
    RefundService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Refund object from a map of parameters.
     *
     * @param params Map of parameters
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public List<Refund> create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("refund", params);

        String endpoint = "refunds";

        Refund[] response = Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Refund[].class, client);

        return Arrays.asList(response);
    }

    /**
     * Retrieve a Refund object from the API.
     *
     * @param id ID of refund to retrieve
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public Refund retrieve(final String id) throws EasyPostException {
        String endpoint = "refunds/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Refund.class, client);
    }

    /**
     * List all Refunds objects.
     *
     * @param params Map of parameters
     * @return RefundCollection object
     * @throws EasyPostException when the request fails.
     */
    public RefundCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "refunds";

        return Requestor.request(RequestMethod.GET, endpoint, params, RefundCollection.class, client);
    }
}
