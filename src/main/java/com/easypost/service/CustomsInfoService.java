package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
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

        return Requestor.request(RequestMethod.POST, "customs_infos", wrappedParams, CustomsInfo.class, client);
    }

    /**
     * Retrieve a CustomsInfo from the API.
     *
     * @param id The ID of the CustomsInfo to retrieve.
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public CustomsInfo retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "customs_infos/" + id, null, CustomsInfo.class, client);
    }
}
