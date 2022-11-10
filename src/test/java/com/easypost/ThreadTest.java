package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Constant;
import com.easypost.model.Order;
import com.easypost.service.EasyPostClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ThreadTest {
    private static Map<String, Object> defaultFromAddress = new HashMap<String, Object>();
    private static Map<String, Object> defaultToAddress = new HashMap<String, Object>();
    private static Map<String, Object> defaultParcel = new HashMap<String, Object>();
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setUp() throws EasyPostException {
        vcr = new TestUtils.VCR("thread", TestUtils.ApiKey.TEST);

        defaultFromAddress.put("name", "EasyPost");
        defaultFromAddress.put("street1", "164 Townsend St");
        defaultFromAddress.put("street2", "Unit 1");
        defaultFromAddress.put("city", "San Francisco");
        defaultFromAddress.put("state", "CA");
        defaultFromAddress.put("zip", "94107");
        defaultFromAddress.put("phone", "415-456-7890");

        defaultToAddress.put("company", "Airport Shipping");
        defaultToAddress.put("street1", "601 Brasilia Avenue");
        defaultToAddress.put("city", "Kansas City");
        defaultToAddress.put("state", "MO");
        defaultToAddress.put("zip", "64153");
        defaultToAddress.put("phone", "314-924-0383");

        defaultParcel.put("length", 10.8);
        defaultParcel.put("width", 8.3);
        defaultParcel.put("height", 6);
        defaultParcel.put("weight", 10);
    }

    /**
     * Test thread to create order.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testThreadOrderCreation() throws EasyPostException, InterruptedException {
        vcr.setUpTest("thread_order_creation");

        // create a default set of shipment and order params to feed to the threads
        Map<String, Object> shipment1 = orderShipment();
        List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
        shipments.add(shipment1);
        Map<String, Object> orderParams = new HashMap<String, Object>();
        orderParams.put("shipments", shipments);
        orderParams.put("from_address", defaultFromAddress);
        orderParams.put("to_address", defaultToAddress);

        // divide up the orders among the threads
        List<Map<String, Object>> orderBatch1 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> orderBatch2 = new ArrayList<Map<String, Object>>();
        orderBatch1.add(orderParams);
        orderBatch1.add(orderParams);
        orderBatch2.add(orderParams);
        orderBatch2.add(orderParams);

        System.out.println("Starting CreateOrders threads.");
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(new CreateOrders(orderBatch1)));
        threads.add(new Thread(new CreateOrders(orderBatch2)));

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Done CreateOrders threads!");

        // for more information on threads including adding timeouts and interrupts see:
        // http://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
    }

    static Map<String, Object> orderShipment() throws EasyPostException {
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("parcel", defaultParcel);
        return shipmentMap;
    }

    /**
     * Test creating orders.
     *
     * @throws EasyPostException when the request fails.
     */
    private static class CreateOrders implements Runnable {
        private List<Map<String, Object>> orders;

        CreateOrders(List<Map<String, Object>> orders) {
            this.orders = orders;
        }

        public void run() {
            try {
                for (int i = 0; i < this.orders.size(); i++) {
                    System.out.format("Thread %s: starting order creation...%n", Thread.currentThread().getName());

                    EasyPostClient client = new EasyPostClient(System.getenv("EASYPOST_TEST_API_KEY"));
                    Order order = client.order.create(this.orders.get(i));
                    // save order id to database or buy now

                    System.out.format("Thread %s: created order %s%n", Thread.currentThread().getName(), order.getId());
                }
            } catch (EasyPostException e) {
                System.out.println("EasyPost Exception caught creating order.");
            }
        }
    }

    /**
     * Test create multiple EasyPostClient with different API keys.
     * @throws EasyPostException
     */
    @Test
    public void testMultipleClients() throws EasyPostException {
        EasyPostClient clientOne = new EasyPostClient("fake_api_key_1", 22222, 33333);
        EasyPostClient clientTwo = new EasyPostClient("fake_api_key_2", 55555);

        assertEquals("fake_api_key_1", clientOne.getApiKey());
        assertEquals(22222, clientOne.getConnectionTimeoutMilliseconds());
        assertEquals(33333, clientOne.getReadTimeoutMilliseconds());

        assertEquals("fake_api_key_2", clientTwo.getApiKey());
        assertEquals(55555, clientTwo.getConnectionTimeoutMilliseconds());
        assertEquals(Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS, clientTwo.getReadTimeoutMilliseconds());
    }
}

