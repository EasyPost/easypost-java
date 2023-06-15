package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CustomsInfo;

import java.util.HashMap;
import java.util.Map;

public class CustomsInfoService {
    private final EasyPostClient client;

    /**
     * CustomsInfoService constructor.
     *
     * @param client The client object.
     */
    CustomsInfoService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a CustomsInfo from a map of parameters.
     *
     * @param params Map of parameters.
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public CustomsInfo create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_info", params);

        String endpoint = "customs_infos";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, CustomsInfo.class);
    }

    /**
     * Retrieve a CustomsInfo from the API.
     *
     * @param id The ID of the CustomsInfo to retrieve.
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public CustomsInfo retrieve(final String id) throws EasyPostException {
        String endpoint = "customs_infos/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, CustomsInfo.class);
    }
}
