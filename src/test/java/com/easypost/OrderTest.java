package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Order;
import com.easypost.model.Shipment;
import com.easypost.model.Rate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

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
}
