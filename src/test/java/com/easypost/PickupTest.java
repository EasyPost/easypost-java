package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Pickup;
import com.easypost.model.PickupRate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickupTest {
    // Remember to bump the pickup date if you need to re-record the cassette
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        _vcr = new TestUtils.VCR("pickup", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a pickup.
     */
    private static Pickup createBasicPickup() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        Map<String, Object> pickupData = Fixture.basicPickup();
        pickupData.put("shipment", shipment);

        Pickup pickup = Pickup.create(pickupData);
        return pickup;
    }

    /**
     * Test creating a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        Pickup pickup = createBasicPickup();

        assertInstanceOf(Pickup.class, pickup);
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
        _vcr.setUpTest("retrieve");

        Pickup pickup = createBasicPickup();

        Pickup retrievedPickup = Pickup.retrieve(pickup.getId());

        assertInstanceOf(Pickup.class, retrievedPickup);
        assertThat(pickup).usingRecursiveComparison().isEqualTo(retrievedPickup);
    }

    /**
     * Test buying a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException, InterruptedException {
        _vcr.setUpTest("buy");

        Pickup pickup = createBasicPickup();

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixture.usps());
        params.put("service", Fixture.pickupService());

        // TODO: We shouldn't be requiring the end user to wrap these parameters in a dictionary
        Pickup boughtPickup = pickup.buy(params);

        assertInstanceOf(Pickup.class, boughtPickup);
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
    public void testCancel() throws EasyPostException, InterruptedException {
        _vcr.setUpTest("cancel");

        Pickup pickup = createBasicPickup();

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixture.usps());
        params.put("service", Fixture.pickupService());
        Pickup boughtPickup = pickup.buy(params);

        Pickup cancelledPickup = boughtPickup.cancel();

        assertInstanceOf(Pickup.class, cancelledPickup);
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
        _vcr.setUpTest("lowest_rate");

        Pickup pickup = createBasicPickup();

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
