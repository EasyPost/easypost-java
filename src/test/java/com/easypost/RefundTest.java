package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Refund;
import com.easypost.model.RefundCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class RefundTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("refund", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a refund.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment());

        // We need to retrieve the shipment so that the tracking_code has time to
        // populate
        Shipment retrievedShipment = vcr.client.shipment.retrieve(shipment.getId());

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        List<String> trackingCodes = new ArrayList<>();
        trackingCodes.add(retrievedShipment.getTrackingCode());
        params.put("tracking_codes", trackingCodes);

        List<Refund> refunds = vcr.client.refund.create(params);

        assertTrue(refunds.get(0).getId().startsWith("rfnd_"));
        assertEquals("submitted", refunds.get(0).getStatus());
    }

    /**
     * Test retrieving all refunds.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        RefundCollection refundCollection = vcr.client.refund.all(params);

        List<Refund> refunds = refundCollection.getRefunds();

        assertTrue(refunds.size() <= Fixtures.pageSize());
        assertNotNull(refundCollection.getHasMore());
        assertTrue(refunds.stream().allMatch(refund -> refund instanceof Refund));
    }

    /**
     * Test retrieving a refund.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        RefundCollection refundCollection = vcr.client.refund.all(params);
        List<Refund> refunds = refundCollection.getRefunds();

        Refund retrievedRefund = vcr.client.refund.retrieve(refunds.get(0).getId());

        assertInstanceOf(Refund.class, retrievedRefund);
        assertTrue(refunds.get(0).equals(retrievedRefund));
    }
}
