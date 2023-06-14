package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.EndShipper;
import com.easypost.model.EndShipperCollection;

import java.util.HashMap;
import java.util.Map;

public class EndShipperService {
    private final EasyPostClient client;

    /**
     * EndShipperService constructor.
     *
     * @param client The client object.
     */
    EndShipperService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create EndShipper object from parameter map.
     *
     * @param params Map of EndShipper parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        String endpoint = "end_shippers";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, EndShipper.class);
    }

    /**
     * Retrieve EndShipper object from API.
     *
     * @param id ID of EndShipper to retrieve.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper retrieve(final String id) throws EasyPostException {
        String endpoint = "end_shippers/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, EndShipper.class);
    }

    /**
     * List all EndShipper objects.
     *
     * @param params Map of parameters.
     * @return EndShipperCollection object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipperCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "end_shippers";

        return this.client.request(RequestMethod.GET, endpoint, params, EndShipperCollection.class);
    }

    // TODO: Add getNextPage function when "before_id" is available for EndShipper All endpoint.

    /**
     * Update an EndShipper object.
     *
     * @param id     The ID of endshipper.
     * @param params Map of parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper update(final String id, final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        String endpoint = "end_shippers/" + id;

        return this.client.request(RequestMethod.PUT, endpoint, wrappedParams, EndShipper.class);
    }
}
