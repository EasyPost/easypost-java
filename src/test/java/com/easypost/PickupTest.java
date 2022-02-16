package com.easypost;

import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Pickup;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

public class PickupTest {
    private static Pickup globalPickup;
    private static Map<String, Object> pickupData = Fixture.basicPickup();

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        pickupData = Fixture.basicPickup();
        pickupData.put("shipment", shipment);

        globalPickup = Pickup.create(pickupData);
    }

    /**
     * Test creating a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Pickup pickup = Pickup.create(pickupData);

        assertTrue(pickup instanceof Pickup);
        assertTrue(pickup.getId().startsWith("pickup_"));
        assertNotNull(pickup.getPickupRates());
    }

    /**
     * Test retrieving a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Pickup retrievedPickup = Pickup.retrieve(globalPickup.getId());

        assertTrue(retrievedPickup instanceof Pickup);
        assertTrue(retrievedPickup.getId().startsWith("pickup_"));
    }

    /**
     * Test buying a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // This test is ignored for now because it relies on the pickup min and max date
    public void testBuy() throws EasyPostException {
        // TODO We will revisit this once we have VCR.
        Map<String, Object> params = new HashMap<>();

        params.put("carrier", Fixture.usps());
        params.put("service", "NextDay");

        Pickup boughtPickup = globalPickup.buy(params);

        assertTrue(boughtPickup instanceof Pickup);
        assertTrue(boughtPickup.getId().startsWith("pickup_"));
        assertNotNull(boughtPickup.getConfirmation());
        assertEquals("scheduled", boughtPickup.getStatus());
    }

    /**
     * Test cancelling a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // This test is ignored for now because it relies on the pickup min and max date
    public void testCancel() throws EasyPostException {
        // TODO We will revisit this once we have VCR.
        Pickup cancelledPickup = globalPickup.cancel();

        assertTrue(cancelledPickup instanceof Pickup);
        assertTrue(cancelledPickup.getId().startsWith("pickup_"));
        assertEquals("canceled", cancelledPickup.getStatus());
    }
}
