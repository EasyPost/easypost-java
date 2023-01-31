package com.easypost.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.StatelessRate;

public class BetaRateService {
    private final EasyPostClient client;

    /**
     * BetaRateService constructor.
     * 
     * @param client The client object.
     */
    BetaRateService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * List all StatelessRate objects.
     *
     * @param params Map of parameters.
     * @return List of StatelessRate objects.
     * @throws EasyPostException when the request fails.
     */
    public List<StatelessRate> retrieveStatelessRates(HashMap<String, Object> params) throws EasyPostException {
        HashMap<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("shipment", params);

        StatelessRate[] statelessRates = Requestor.request(RequestMethod.POST, "rates", wrappedParams,
                StatelessRate[].class, client, "beta");

        return Arrays.asList(statelessRates);
    }
}
