package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Refund;
import com.easypost.model.RefundCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        List<Refund> refunds = createBasicRefundList();

        assertInstanceOf(List.class, refunds);
        assertTrue(refunds.stream().allMatch(refund -> refund instanceof Refund));

        Refund refund = refunds.get(0);
        assertTrue(refund.getId().startsWith("rfnd_"));
        assertEquals("submitted", refund.getStatus());
    }

    /**
     * Create a list of refunds.
     *
     * @return List of Refund objects
     */
    private static List<Refund> createBasicRefundList() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixtures.oneCallBuyShipment());

        // We need to retrieve the shipment so that the tracking_code has time to populate
        Shipment retrievedShipment = Shipment.retrieve(shipment.getId());

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        params.put("tracking_codes", retrievedShipment.getTrackingCode());

        return Refund.create(params);
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

        RefundCollection refundCollection = Refund.all(params);

        List<Refund> refunds = refundCollection.getRefunds();

        assertTrue(refunds.size() <= Fixtures.pageSize());
        assertNotNull(refundCollection.getHasMore());
        assertTrue(refunds.stream().allMatch(refund -> refund instanceof Refund));
    }

    /**
     * Test retrieving a refunds.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        List<Refund> refunds = createBasicRefundList();
        Refund refund = refunds.get(0);

        Refund retrievedRefund = Refund.retrieve(refund.getId());

        assertInstanceOf(Refund.class, retrievedRefund);
        assertTrue(refund.equals(retrievedRefund));
    }
}
