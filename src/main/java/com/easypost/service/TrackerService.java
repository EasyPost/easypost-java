package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;
import com.easypost.utils.Utilities;

import java.util.HashMap;
import java.util.Map;

public class TrackerService {
    private final EasyPostClient client;

    /**
     * TrackerService constructor.
     * 
     * @param client The client object.
     */
    TrackerService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a new Tracker object using a map of parameters.
     *
     * @param params Map of parameters used to create the Tracker.
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public Tracker create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("tracker", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Tracker.class),
                wrappedParams, Tracker.class, client);
    }

    /**
     * Retrieve a Tracker object from the API.
     *
     * @param id ID of the Tracker to retrieve.
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public Tracker retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Tracker.class, id),
                null, Tracker.class, client);
    }

    /**
     * Get a list of all Tracker objects.
     *
     * @param params Map of parameters used to filter the list of Trackers.
     * @return TrackerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public TrackerCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Tracker.class),
                params, TrackerCollection.class, client);
    }

    /**
     * Create a list of Trackers.
     *
     * @param params Map of parameters used to create the Trackers.
     * @throws EasyPostException when the request fails.
     */
    public void createList(final Map<String, Object> params) throws EasyPostException {
        String createListUrl = String.format("%s/create_list", Utilities.classURL(Tracker.class));

        Map<String, Object> newParams = new HashMap<String, Object>();
        newParams.put("trackers", params);

        Requestor.request(RequestMethod.POST, createListUrl, newParams, Object.class, client);
    }
}
