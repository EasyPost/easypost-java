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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class OrderTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("order", TestUtils.ApiKey.TEST);
    }

    /**
     * Create an order.
     *
     * @return Order object
     */
    private static Order createBasicOrder() throws EasyPostException {
        return vcr.client.order.create(Fixtures.basicOrder());
    }

    /**
     * Test creating an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Order order = createBasicOrder();

        assertInstanceOf(Order.class, order);
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
        vcr.setUpTest("retrieve");

        Order order = createBasicOrder();

        Order retrievedOrder = vcr.client.order.retrieve(order.getId());

        assertInstanceOf(Order.class, retrievedOrder);
        // Must compare IDs since other elements of objects may be different
        assertEquals(order.getId(), retrievedOrder.getId());
    }

    /**
     * Test retrieving rates for an order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetRates() throws EasyPostException {
        vcr.setUpTest("get_rates");

        Order order = createBasicOrder();

        List<Rate> rates = order.getRates();

        assertNotNull(rates);
        for (Rate rate : rates) {
            assertInstanceOf(Rate.class, rate);
            assertTrue(rate.getId().startsWith("rate_"));
        }
    }

    /**
     * Test buying an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException, InterruptedException {
        vcr.setUpTest("buy");

        Order order = createBasicOrder();

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        params.put("service", Fixtures.uspsService());

        Order boughtOrder = vcr.client.order.buy(params, order.getId());

        List<Shipment> shipments = boughtOrder.getShipments();

        assertInstanceOf(Order.class, order);
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
        vcr.setUpTest("lowest_rate");

        Order order = createBasicOrder();

        // Test lowest rate with no filters
        Rate lowestRate = vcr.client.order.lowestRate(order);
        assertEquals("First", lowestRate.getService());
        assertEquals(5.82, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (this rate is higher than the lowest but should filter)
        List<String> services = new ArrayList<>(Arrays.asList("Priority"));
        Rate lowestRateService = vcr.client.order.lowestRate(null, services, order);
        assertEquals("Priority", lowestRateService.getService());
        assertEquals(8.15, lowestRateService.getRate(), 0.01);
        assertEquals("USPS", lowestRateService.getCarrier());

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carriers = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            vcr.client.order.lowestRate(carriers, null, order);
        });
    }
}
