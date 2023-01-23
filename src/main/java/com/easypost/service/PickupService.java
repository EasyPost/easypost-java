package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Pickup;
import com.easypost.model.PickupCollection;
import com.easypost.model.PickupRate;

import java.util.HashMap;
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
     * Get a list of all Pickup objects.
     *
     * @param params The options for the query.
     * @return PickupCollection object
     * @throws EasyPostException when the request fails.
     */
    public PickupCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "pickups";

        return Requestor.request(RequestMethod.GET, endpoint, params, PickupCollection.class, client);
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

        String endpoint = "pickups";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Pickup.class, client);
    }

    /**
     * Retrieve a Pickup from the API.
     *
     * @param id ID of Pickup to retrieve.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup retrieve(final String id) throws EasyPostException {
        String endpoint = "pickups/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Pickup.class, client);
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
        return this.buy(id, new HashMap<String, Object>());
    }

    /**
     * Buy this Pickup.
     *
     * @param id     The ID of pickup.
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "pickups/" + id + "/buy";

        return Requestor.request(RequestMethod.POST, endpoint, params, Pickup.class, client);
    }

    /**
     * Buy this Pickup.
     *
     * @param id         The ID of pickup.
     * @param pickupRate PickupRate to buy.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final String id, final PickupRate pickupRate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", pickupRate);

        return this.buy(id, params);
    }

    /**
     * Cancel this Pickup.
     *
     * @param id The ID of pickup.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final String id) throws EasyPostException {
        return this.cancel(id, null);
    }

    /**
     * Cancel this Pickup.
     *
     * @param id     The ID of pickup.
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "pickups/" + id + "/cancel";

        return Requestor.request(RequestMethod.POST, endpoint, params, Pickup.class, client);
    }
}
