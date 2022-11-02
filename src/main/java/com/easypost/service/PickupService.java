package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Pickup;
import com.easypost.model.PickupRate;
import com.easypost.model.Rate;
import com.easypost.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickupService {
    private final EasyPostClient client;

    /**
     * PickupService constructor.
     * 
     * @param client The client object.
     */
    PickupService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a new Pickup object from a map of parameters.
     *
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("pickup", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Pickup.class), wrappedParams, Pickup.class,
                client);
    }

    /**
     * Retrieve a Pickup from the API.
     *
     * @param id ID of Pickup to retrieve.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Pickup.class, id), null, Pickup.class,
                client);
    }

    /**
     * Refresh this Pickup.
     *
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh(final String id) throws EasyPostException {
        return this.refresh(null, id);
    }

    /**
     * Refresh this Pickup.
     *
     * @param params Map of parameters.
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, String.format("%s",
                Utilities.instanceURL(Pickup.class, id)), params, Pickup.class, client);
    }

    /**
     * Buy this Pickup.
     *
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final String id) throws EasyPostException {
        // Pass in empty map to avoid method ambiguous.
        return this.buy(new HashMap<String, Object>(), id);
    }

    /**
     * Buy this Pickup.
     *
     * @param params Map of parameters.
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/buy",
                Utilities.instanceURL(Pickup.class, id)), params, Pickup.class, client);
    }

    /**
     * Buy this Pickup.
     *
     * @param pickupRate PickupRate to buy.
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final PickupRate pickupRate, final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", pickupRate);

        return this.buy(params, id);
    }

    /**
     * Cancel this Pickup.
     *
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final String id) throws EasyPostException {
        return this.cancel(null, id);
    }

    /**
     * Cancel this Pickup.
     *
     * @param params Map of parameters.
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/cancel",
                Utilities.instanceURL(Pickup.class, id)), params, Pickup.class, client);
    }

    /**
     * Get the lowest rate for this Pickup.
     *
     * @param pickup The pickup object.
     * @return lowest PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate(final Pickup pickup) throws EasyPostException {
        return this.lowestRate(null, null, pickup);
    }

    /**
     * Get the lowest rate for this Pickup.
     *
     * @param carriers The carriers to use in the filter.
     * @param services The services to use in the filter.
     * @param pickup   The pickup object.
     * @return lowest PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate(final List<String> carriers, final List<String> services, final Pickup pickup)
            throws EasyPostException {
        List<Rate> rates = new ArrayList<Rate>();

        for (PickupRate rate : pickup.getPickoutRates()) {
            rates.add((Rate) rate);
        }

        return (PickupRate) Utilities.getLowestObjectRate(rates, carriers, services);
    }

    /**
     * Get the lowest rate for this pickup.
     *
     * @param carriers The carriers to use in the query.
     * @param pickup   The pickup object.
     * @return PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate(final List<String> carriers, final Pickup pickup) throws EasyPostException {
        return this.lowestRate(carriers, null, pickup);
    }
}
