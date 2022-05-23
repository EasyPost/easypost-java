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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefundTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        _vcr = new TestUtils.VCR("refund", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a list of refunds.
     */
    private static List<Refund> createBasicRefundList() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        Shipment retrievedShipment = Shipment.retrieve(shipment.getId()); // We need to retrieve the shipment so that the tracking_code has time to populate

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixture.usps());
        params.put("tracking_codes", retrievedShipment.getTrackingCode());

        return Refund.create(params);
    }

    /**
     * Test creating a refund.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        List<Refund> refunds = createBasicRefundList();

        assertInstanceOf(List.class, refunds);
        assertTrue(refunds.stream().allMatch(refund -> refund instanceof Refund));

        Refund refund = refunds.get(0);
        assertTrue(refund.getId().startsWith("rfnd_"));
        assertEquals("submitted", refund.getStatus());
    }

    /**
     * Test retrieving all refunds.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());

        RefundCollection refundCollection = Refund.all(params);

        List<Refund> refunds = refundCollection.getRefunds();

        assertTrue(refunds.size() <= Fixture.pageSize());
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
        _vcr.setUpTest("retrieve");

        List<Refund> refunds = createBasicRefundList();
        Refund refund = refunds.get(0);

        Refund retrievedRefund = Refund.retrieve(refund.getId());

        assertInstanceOf(Refund.class, retrievedRefund);
        assertThat(refund).usingRecursiveComparison().isEqualTo(retrievedRefund);
    }
}
