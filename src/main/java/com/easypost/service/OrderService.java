package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Order;
import com.easypost.model.Rate;
import com.easypost.utils.Utilities;

import java.util.HashMap;
import java.util.List;
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
     * Create an Order object from a map of paramters.
     *
     * @param params Map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("order", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Order.class), wrappedParams, Order.class,
                client);
    }

    /**
     * Retrieve an Order object from the API.
     *
     * @param id ID of the Order to retrieve.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Order.class, id), null, Order.class, client);
    }

    /**
     * Refresh this Order object.
     *
     * @param id The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh(final String id) throws EasyPostException {
        return this.refresh(null, id);
    }

    /**
     * Refresh this Order object.
     *
     * @param params Map of parameters.
     * @param id     The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET,
                String.format("%s", Utilities.instanceURL(Order.class, id)), params,
                Order.class, client);
    }

    /**
     * Get new rates for this Order.
     *
     * @param id The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final String id) throws EasyPostException {
        return this.newRates(null, id);
    }

    /**
     * Get new rates for this Order.
     *
     * @param params Map of parameters.
     * @param id     The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, String.format("%s/rates",
                Utilities.instanceURL(Order.class, id)), params, Order.class, client);
    }

    /**
     * Buy this Order.
     *
     * @param params Map of parameters.
     * @param id     The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/buy",
                Utilities.instanceURL(Order.class, id)), params, Order.class, client);
    }

    /**
     * Buy this Order.
     *
     * @param rate Rate to buy.
     * @param id   The ID of order.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final Rate rate, final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carrier", rate.getCarrier());
        params.put("service", rate.getService());

        return this.buy(params, id);
    }

    /**
     * Get the lowest rate for this Order.
     *
     * @param order The order object.
     * @return Lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final Order order) throws EasyPostException {
        return this.lowestRate(null, null, order);
    }

    /**
     * Get the lowest rate for this Order.
     *
     * @param carriers The carriers to use in the filter.
     * @param services The services to use in the filter.
     * @param order    The order object.
     * @return Lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final List<String> services, final Order order)
            throws EasyPostException {
        return Utilities.getLowestObjectRate(order.getRates(), carriers, services);
    }

    /**
     * Get the lowest rate for this order.
     *
     * @param carriers The carriers to use in the query.
     * @param order    The order object.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final Order order) throws EasyPostException {
        return this.lowestRate(carriers, null, order);
    }
}
