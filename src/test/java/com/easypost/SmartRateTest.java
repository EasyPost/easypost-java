package com.easypost;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.easypost.exception.EasyPostException;
import com.easypost.model.DeliveryDateForZipPairEstimate;
import com.easypost.model.EstimateDeliveryDateForZipPairResult;
import com.easypost.model.RecommendShipDateForZipPairResult;
import com.easypost.model.ShipDateForZipPairRecommendation;

public class SmartRateTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("smart_rate", TestUtils.ApiKey.TEST);
    }

    /**
     * Test that we retrieve SmartRates when provided a from/to zip and desired delivery date.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveRecommendShipDate() throws EasyPostException {
        vcr.setUpTest("retrieve_recommend_ship_date");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("from_zip", Fixtures.caAddress1().get("zip"));
        params.put("to_zip", Fixtures.caAddress2().get("zip"));
        params.put("desired_delivery_date", Fixtures.desiredDeliveryDate());
        params.put("carriers", Collections.singletonList(Fixtures.usps()));

        RecommendShipDateForZipPairResult results = vcr.client.smartRate.recommendShipDate(params);
        for (ShipDateForZipPairRecommendation entry : results.getResults()) {
            assertNotNull(entry.getEasypostTimeInTransitData());
        }
    }

    /**
     * Test that we retrieve SmartRates when provided a from/to zip and planned ship date.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveEstimatedDeliveryDate() throws EasyPostException {
        vcr.setUpTest("retrieve_estimated_delivery_date");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("from_zip", Fixtures.caAddress1().get("zip"));
        params.put("to_zip", Fixtures.caAddress2().get("zip"));
        params.put("planned_ship_date", Fixtures.plannedShipDate());
        params.put("carriers", Collections.singletonList(Fixtures.usps()));

        EstimateDeliveryDateForZipPairResult results = vcr.client.smartRate.estimateDeliveryDate(params);
        for (DeliveryDateForZipPairEstimate entry : results.getResults()) {
            assertNotNull(entry.getEasypostTimeInTransitData());
        }
    }
}
