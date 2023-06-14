package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Order;
import com.easypost.model.Rate;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    private final EasyPostClient client;

    /**
     * OrderService constructor.
     *
     * @param client The client object.
     */
    OrderService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create an Order object from a map of parameters.
     *
     * @param params Map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("order", params);

        String endpoint = "orders";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, Order.class);
    }

    /**
     * Retrieve an Order object from the API.
     *
     * @param id ID of the Order to retrieve.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order retrieve(final String id) throws EasyPostException {
        String endpoint = "orders/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, Order.class);
    }

    /**
     * Get new rates for this Order.
     *
     * @param id The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final String id) throws EasyPostException {
        return this.newRates(id, null);
    }

    /**
     * Get new rates for this Order.
     *
     * @param id     The ID of order.
     * @param params Map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "orders/" + id + "/rates";

        return this.client.request(RequestMethod.GET, endpoint, params, Order.class);
    }

    /**
     * Buy this Order.
     *
     * @param id     The ID of order.
     * @param params Map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "orders/" + id + "/buy";

        return this.client.request(RequestMethod.POST, endpoint, params, Order.class);
    }

    /**
     * Buy this Order.
     *
     * @param id   The ID of order.
     * @param rate Rate to buy.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final String id, final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carrier", rate.getCarrier());
        params.put("service", rate.getService());

        return this.buy(id, params);
    }
}
