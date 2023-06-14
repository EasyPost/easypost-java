package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CustomsItem;

import java.util.HashMap;
import java.util.Map;

public class CustomsItemService {
    private final EasyPostClient client;

    /**
     * CustomsItemService constructor.
     *
     * @param client The client object.
     */
    CustomsItemService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a CustomsItem from a map of parameters.
     *
     * @param params Map of parameters.
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public CustomsItem create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_item", params);

        String endpoint = "customs_items";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, CustomsItem.class);
    }

    /**
     * Retrieve a CustomsItem from the API.
     *
     * @param id The ID of the CustomsItem to retrieve.
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public CustomsItem retrieve(final String id) throws EasyPostException {
        String endpoint = "customs_items/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, CustomsItem.class);
    }
}
