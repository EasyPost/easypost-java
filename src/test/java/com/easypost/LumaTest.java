package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.LumaInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class LumaTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("shipment", TestUtils.ApiKey.TEST);
    }

    /**
     * Test that we get service recommendations from Luma based on your ruleset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetPromise() throws EasyPostException {
        vcr.setUpTest("get_promise");

        Map<String, Object> shipmentData = Fixtures.basicShipment();
        shipmentData.put("ruleset_name", Fixtures.lumaRulesetName());
        shipmentData.put("planned_ship_date", Fixtures.lumaPlannedShipDate());

        LumaInfo response = vcr.client.luma.getPromise(shipmentData);

        assertNotNull(response.getRulesetDescription());
    }
}
