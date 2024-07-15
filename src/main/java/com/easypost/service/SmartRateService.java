package com.easypost.service;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.EstimateDeliveryDateForZipPairResult;
import com.easypost.model.RecommendShipDateForZipPairResult;

public class SmartRateService {
    private final EasyPostClient client;

    /**
     * TrackerService constructor.
     *
     * @param client The client object.
     */
    SmartRateService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Retrieve a recommended ship date for an existing Shipment via the Precision Shipping API,
     * based on a specific desired delivery date.
     * 
     * @param  params Parameters to include on the API call.
     * @return EstimatedDeliveryDate object.
     * @throws EasyPostException When the request fails.
     */
    public RecommendShipDateForZipPairResult recommendShipDate(final Map<String, Object> params)
            throws EasyPostException {
        String endpoint = "smartrate/deliver_on";

        return Requestor.request(RequestMethod.POST, endpoint, params, RecommendShipDateForZipPairResult.class, client);
    }

    /**
     * Retrieve the estimated delivery date of each carrier-service level combination via the
     * Smart Deliver By API, based on a specific ship date and origin-destination postal code pair.
     *
     * @param  params Parameters to include on the API call.
     * @return EstimatedDeliveryDate object.
     * @throws EasyPostException When the request fails.
     */
    public EstimateDeliveryDateForZipPairResult estimateDeliveryDate(final Map<String, Object> params)
            throws EasyPostException {
        String endpoint = "smartrate/deliver_by";

        return Requestor.request(RequestMethod.POST, endpoint, params,
            EstimateDeliveryDateForZipPairResult.class, client);
    }
}
