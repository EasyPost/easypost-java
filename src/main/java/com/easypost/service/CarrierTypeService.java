package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CarrierType;

import java.util.Arrays;
import java.util.List;

public class CarrierTypeService {
    private final EasyPostClient client;

    /**
     * CarrierTypeService constructor.
     *
     * @param client The client object.
     */
    CarrierTypeService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Retrieve a list of available carriers for the given account.
     *
     * @return list of carrier types that are available for the given account.
     * @throws EasyPostException when the request fails.
     */
    public List<CarrierType> all() throws EasyPostException {
        String endpoint = "carrier_types";

        CarrierType[] response = Requestor.request(RequestMethod.GET, endpoint, null, CarrierType[].class, client);
        return Arrays.asList(response);
    }
}
