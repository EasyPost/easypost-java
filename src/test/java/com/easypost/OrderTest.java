package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Order;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
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

public class OrderTest {
    private static Order globalOrder;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalOrder = Order.create(Fixture.basicOrder());
    }

    /**
     * Test creating an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Order order = Order.create(Fixture.basicOrder());

        assertTrue(order instanceof Order);
        assertTrue(order.getId().startsWith("order_"));
        assertNotNull(order.getRates());
    }

    /**
     * Test retrieving an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Order retrievedOrder = Order.retrieve(globalOrder.getId());

        assertTrue(retrievedOrder instanceof Order);
        assertEquals(globalOrder.getId(), retrievedOrder.getId());
    }

    /**
     * Test retrieving rates for an order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetRates() throws EasyPostException {
        List<Rate> rates = globalOrder.getRates();

        assertNotNull(rates);
        for (Rate rate : rates) {
            assertTrue(rate instanceof Rate);
            assertTrue(rate.getId().startsWith("rate_"));
        }
    }

    /**
     * Test buying an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("carrier", Fixture.usps());
        params.put("service", Fixture.uspsService());

        globalOrder.buy(params);

        List<Shipment> shipments = globalOrder.getShipments();

        assertTrue(globalOrder instanceof Order);
        for (Shipment shipment : shipments) {
            assertNotNull(shipment.getPostageLabel());
        }
    }

    /**
     * Test getting the lowest rate of an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLowestRate() throws EasyPostException {
        Order order = Order.create(Fixture.basicOrder());

        // Test lowest rate with no filters
        Rate lowestRate = order.lowestRate();
        assertEquals("First", lowestRate.getService());
        assertEquals(5.49, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (this rate is higher than the lowest but should filter)
        List<String> services = new ArrayList<>(Arrays.asList("Priority"));
        Rate lowestRateService = order.lowestRate(null, services);
        assertEquals("Priority", lowestRateService.getService());
        assertEquals(7.37, lowestRateService.getRate(), 0.01);
        assertEquals("USPS", lowestRateService.getCarrier());

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carriers = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            order.lowestRate(carriers, null);
        });
    }
}
