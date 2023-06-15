package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.mocking.MockRequest;
import com.easypost.mocking.MockRequestMatchRules;
import com.easypost.mocking.MockResponse;
import com.easypost.mocking.classes.MockOrderResponse;
import com.easypost.mocking.classes.MockRate;
import com.easypost.mocking.classes.MockStatelessRateResponse;
import com.easypost.model.Order;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.google.common.collect.ImmutableList;
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
     * Test buying an Order with params.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyWithParams() throws EasyPostException, InterruptedException {
        vcr.setUpTest("buy_with_params");

        Order order = createBasicOrder();

        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        params.put("service", Fixtures.uspsService());

        Order boughtOrder = vcr.client.order.buy(order.getId(), params);

        List<Shipment> shipments = boughtOrder.getShipments();

        assertInstanceOf(Order.class, order);
        for (Shipment shipment : shipments) {
            assertNotNull(shipment.getPostageLabel());
        }
    }

    /**
     * Test buying an Order with rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyWithRate() throws EasyPostException, InterruptedException {
        vcr.setUpTest("buy_with_rate");

        Order order = createBasicOrder();

        Order boughtOrder = vcr.client.order.buy(order.getId(), order.lowestRate());

        List<Shipment> shipments = boughtOrder.getShipments();

        assertInstanceOf(Order.class, order);
        for (Shipment shipment : shipments) {
            assertNotNull(shipment.getPostageLabel());
        }
    }

    /**
     * Test getting new rates of a order.
     *
     * @throws EasyPostException if an exception is thrown.
     */
    @Test
    public void testNewRate() throws EasyPostException {
        vcr.setUpTest("new_rate");

        Order order = createBasicOrder();

        Order orderWithNewRate = vcr.client.order.newRates(order.getId());

        List<Rate> rates = orderWithNewRate.getRates();

        assertInstanceOf(List.class, rates);
        for (Rate rate : rates) {
            assertInstanceOf(Rate.class, rate);
        }
    }

    /**
     * Test getting the lowest rate of an Order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLowestRate() throws EasyPostException {

        final MockOrderResponse mockOrderResponse = new MockOrderResponse(
                ImmutableList.of(
                        new MockRate("6.07", "USPS", "First"),
                        new MockRate("31.25", "USPS", "Express"),
                        new MockRate("6.76", "USPS", "ParcelSelect"),
                        new MockRate("7.15", "USPS", "Priority")
                )
        );

        List<MockRequest> mockRequests = new ArrayList<>();
        mockRequests.add(new MockRequest(new MockRequestMatchRules(Requestor.RequestMethod.POST, ".*orders$"),
                new MockResponse(200, mockOrderResponse)));

        vcr.setUpTest("lowest_rate", mockRequests);

        Order order = createBasicOrder();

        // Test lowest rate with no filters
        Rate lowestRate = order.lowestRate();
        assertEquals("First", lowestRate.getService());
        assertEquals(6.07, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (this rate is higher than the lowest but should filter)
        List<String> services = new ArrayList<>(Arrays.asList("Priority"));
        Rate lowestRateService = order.lowestRate(null, services);
        assertEquals("Priority", lowestRateService.getService());
        assertEquals(7.15, lowestRateService.getRate(), 0.01);
        assertEquals("USPS", lowestRateService.getCarrier());

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carriers = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            order.lowestRate(carriers, null);
        });
    }
}
