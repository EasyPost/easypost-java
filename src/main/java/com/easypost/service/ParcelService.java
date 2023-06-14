package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Parcel;

import java.util.HashMap;
import java.util.Map;

public class ParcelService {
    private final EasyPostClient client;

    /**
     * ParcelService constructor.
     *
     * @param client The client object.
     */
    ParcelService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Parcel from a map of parameters.
     *
     * @param params The map of the parameters to create a Parcel from.
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public Parcel create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("parcel", params);

        String endpoint = "parcels";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, Parcel.class);
    }

    /**
     * Retrieve a Parcel from the API.
     *
     * @param id The ID of the Parcel to retrieve.
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public Parcel retrieve(final String id) throws EasyPostException {
        String endpoint = "parcels/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, Parcel.class);
    }
}
