package com.easypost.service;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CustomerPortalAccountLink;

public class CustomerPortalService {
    private final EasyPostClient client;

    /**
     * CustomerPortalService constructor.
     *
     * @param client The client object.
     */
    CustomerPortalService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a CustomerPortalAccountLink from the API.
     *
     * @param params Map of parameters.
     * @return CustomerPortalAccountLink object
     * @throws EasyPostException when the request fails.
     */
    public CustomerPortalAccountLink createAccountLink(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "customer_portal/account_link";

        return Requestor.request(RequestMethod.POST, endpoint, params, CustomerPortalAccountLink.class, client);
    }
}
