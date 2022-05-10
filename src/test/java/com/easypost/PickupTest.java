package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Pickup;
import com.easypost.model.PickupRate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickupTest {
    private static Pickup globalPickup;
    private static Map<String, Object> pickupData = Fixture.basicPickup();

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

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
        Map<String, Object> params = new HashMap<>();

        params.put("carrier", Fixture.usps());
        params.put("service", Fixture.pickupService());

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
        Pickup cancelledPickup = globalPickup.cancel();

        assertTrue(cancelledPickup instanceof Pickup);
        assertTrue(cancelledPickup.getId().startsWith("pickup_"));
        assertEquals("canceled", cancelledPickup.getStatus());
    }

    /**
     * Test getting the lowest rate of a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLowestRate() throws EasyPostException {
        Map<String, Object> pickupData = Fixture.basicPickup();
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        pickupData.put("shipment", shipment);

        Pickup pickup = Pickup.create(pickupData);

        // Test lowest rate with no filters
        PickupRate lowestRate = pickup.lowestRate();
        assertEquals("NextDay", lowestRate.getService());
        assertEquals(0.00, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (should error due to bad service)
        List<String> services = new ArrayList<>(Arrays.asList("BAD SERVICE"));
        assertThrows(EasyPostException.class, () -> {
            pickup.lowestRate(null, services);
        });

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carriers = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            pickup.lowestRate(carriers, null);
        });
    }
}
