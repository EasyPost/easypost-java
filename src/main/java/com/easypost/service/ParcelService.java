package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Parcel;
import com.easypost.utils.Utilities;

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

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Parcel.class), wrappedParams, Parcel.class,
                client);
    }

    /**
     * Retrieve a Parcel from the API.
     *
     * @param id The ID of the Parcel to retrieve.
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public Parcel retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Parcel.class, id), null, Parcel.class,
                client);
    }
}
