package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Address;
import com.easypost.model.AddressCollection;
import com.easypost.model.AddressVerifyResponse;

import java.util.HashMap;
import java.util.Map;

public class AddressService {
    private final EasyPostClient client;

    /**
     * AddressService constructor.
     *
     * @param client The client object.
     */
    AddressService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create Address object from parameter map.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        if (params.containsKey("verify")) {
            wrappedParams.put("verify", params.remove("verify"));
        }

        if (params.containsKey("verify_strict")) {
            wrappedParams.put("verify_strict", params.remove("verify_strict"));
        }

        wrappedParams.put("address", params);

        return Requestor.request(RequestMethod.POST, "addresses", wrappedParams, Address.class, client);
    }

    /**
     * Retrieve Address object from API.
     *
     * @param id ID of address to retrieve.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "addresses/" + id, null, Address.class, client);
    }

    /**
     * List all Address objects.
     *
     * @param params Map of parameters.
     * @return AddressCollection object.
     * @throws EasyPostException when the request fails.
     */
    public AddressCollection all(final Map<String, Object> params) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "addresses", params, AddressCollection.class, client);
    }

    /**
     * Create Address object from parameter map and immediately verify it.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address createAndVerify(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("address", params);

        AddressVerifyResponse response =
                Requestor.request(RequestMethod.POST, "addresses/create_and_verify", wrappedParams,
                        AddressVerifyResponse.class, client);

        return response.getAddress();
    }

    /**
     * Verify this Address object.
     *
     * @param id The ID of address.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verify(String id) throws EasyPostException {
        AddressVerifyResponse response =
                Requestor.request(RequestMethod.GET, "addresses/" + id + "/verify", null, AddressVerifyResponse.class,
                        client);

        return response.getAddress();
    }
}
