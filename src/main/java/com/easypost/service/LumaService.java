package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.LumaPromiseResponse;
import com.easypost.model.LumaInfo;

import java.util.HashMap;
import java.util.Map;

public class LumaService {
    private final EasyPostClient client;

    /**
     * LumaService constructor.
     *
     * @param client The client object.
     */
    LumaService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Get service recommendations from Luma that meet the criteria of your ruleset.
     *
     * @param params The map of parameters.
     * @return LumaInfo object.
     * @throws EasyPostException When the request fails.
     */
    public LumaInfo getPromise(final Map<String, Object> params)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);
        String endpoint = "luma/promise";

        LumaPromiseResponse response = Requestor.request(RequestMethod.POST, endpoint, wrappedParams,
                LumaPromiseResponse.class, client);
        return response.getLumaInfo();
    }
}
