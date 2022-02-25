package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Refund;
import com.easypost.model.RefundCollection;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class RefundTest {

    private static List<Refund> globalRefunds;
    private static Map<String, Object> params = new HashMap<>();

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        Map<String, Object> shipmentData = Fixture.oneCallBuyShipment();

        Shipment shipment = Shipment.create(shipmentData);

        Shipment retrievedShipment = Shipment.retrieve(shipment.getId()); // We need to retrieve the shipment so that the tracking_code has time to populate
        
        params.put("carrier", Fixture.usps());
        params.put("tracking_codes", retrievedShipment.getTrackingCode());

        globalRefunds = Refund.create(params);
    }

    /**
     * Test creating a refund.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        List<Refund> refunds = Refund.create(params);

        assertTrue(refunds instanceof List);
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
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        RefundCollection refunds = Refund.all(params);

        assertTrue(refunds.getRefunds().size() <= Fixture.pageSize());
        assertNotNull(refunds.getHasMore());
        for(Refund refund: refunds.getRefunds()) {
            assertTrue(refund instanceof Refund);
        }
    }

    /**
     * Test retrieving a refunds.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Refund retrievedRefund = Refund.retrieve(globalRefunds.get(0).getId());

        assertTrue(retrievedRefund instanceof Refund);
        assertThat(globalRefunds.get(0)).usingRecursiveComparison().isEqualTo(retrievedRefund);
    }
}
