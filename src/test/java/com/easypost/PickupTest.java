package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Pickup;
import com.easypost.model.PickupCollection;
import com.easypost.model.PickupRate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class PickupTest {
    // Remember to bump the pickup date if you need to re-record the cassette
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("pickup", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a pickup.
     *
     * @return Pickup object
     */
    private static Pickup createBasicPickup() throws EasyPostException {
        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment());

        Map<String, Object> pickupData = Fixtures.basicPickup();
        pickupData.put("shipment", shipment);

        return vcr.client.pickup.create(pickupData);
    }

    /**
     * Test retrieving all shipments.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page_size", Fixtures.pageSize());

        PickupCollection pickupCollection = vcr.client.pickup.all(params);

        List<Pickup> pickups = pickupCollection.getPickups();

        assertTrue(pickups.size() <= Fixtures.pageSize());
        assertNotNull(pickupCollection.getHasMore());
        assertTrue(pickups.stream().allMatch(pickup -> pickup != null));
    }

    /**
     * Test retrieving the next page.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        PickupCollection collection = vcr.client.pickup.all(params);

        try {
            PickupCollection nextPage = vcr.client.pickup.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getPickups().get(0).getId();
            String firstIdOfSecondPage = nextPage.getPickups().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test creating a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

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
        vcr.setUpTest("retrieve");

        Pickup pickup = createBasicPickup();

        Pickup retrievedPickup = vcr.client.pickup.retrieve(pickup.getId());

        assertInstanceOf(Pickup.class, retrievedPickup);
        assertTrue(pickup.equals(retrievedPickup));
    }

    /**
     * Test buying a pickup.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException, InterruptedException {
        vcr.setUpTest("buy");

        Pickup pickup = createBasicPickup();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carrier", Fixtures.usps());
        params.put("service", Fixtures.pickupService());

        Pickup boughtPickup = vcr.client.pickup.buy(pickup.getId(), params);

        assertInstanceOf(Pickup.class, boughtPickup);
        assertTrue(boughtPickup.getId().startsWith("pickup_"));
        assertNotNull(boughtPickup.getConfirmation());
        assertEquals("scheduled", boughtPickup.getStatus());
    }

    /**
     * Test buying a pickup by specifying a pickup rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyWithRate() throws EasyPostException {
        vcr.setUpTest("buy_with_rate");

        Pickup pickup = createBasicPickup();

        Pickup boughtPickup = vcr.client.pickup.buy(pickup.getId(), pickup.lowestRate());

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
        vcr.setUpTest("cancel");

        Pickup pickup = createBasicPickup();

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        params.put("service", Fixtures.pickupService());
        Pickup boughtPickup = vcr.client.pickup.buy(pickup.getId(), params);

        Pickup cancelledPickup = vcr.client.pickup.cancel(boughtPickup.getId());

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
        vcr.setUpTest("lowest_rate");

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
