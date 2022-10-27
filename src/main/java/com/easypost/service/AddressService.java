package com.easypost.service;

import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Address;
import com.easypost.model.AddressThreadSafe;
import com.easypost.model.AddressVerifyResponse;
import com.easypost.model.EasyPostResource;

public class AddressService extends EasyPostResource {
    private EasyPostClient client;

    AddressService(EasyPostClient client) {
        this.client = client;
    }

    public AddressThreadSafe create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        if (params.containsKey("verify")) {
            wrappedParams.put("verify", params.remove("verify"));
        }

        if (params.containsKey("verify_strict")) {
            wrappedParams.put("verify_strict", params.remove("verify_strict"));
        }

        wrappedParams.put("address", params);

        return Requestor.requestThreadSafe(RequestMethod.POST, classURL(Address.class), wrappedParams,
                AddressThreadSafe.class, client);
    }

    public AddressThreadSafe retrieve(final String id) throws EasyPostException {
        return Requestor.requestThreadSafe(RequestMethod.GET, instanceURL(Address.class, id), null, AddressThreadSafe.class,
                client);
    }

    public Address verify(AddressThreadSafe address) throws EasyPostException {
        AddressVerifyResponse response;
        response = Requestor.requestThreadSafe(RequestMethod.GET, String.format("%s/verify",
                instanceURL(Address.class, address.getId())), null, AddressVerifyResponse.class, client);

        return response.getAddress();
    }
}
