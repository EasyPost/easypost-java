package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.TrackerCollection;
import com.easypost.model.Tracker;
import com.easypost.utils.InternalUtilities;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

        String endpoint = "trackers";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Tracker.class, client);
    }

    /**
     * Retrieve a Tracker object from the API.
     *
     * @param id ID of the Tracker to retrieve.
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public Tracker retrieve(final String id) throws EasyPostException {
        String endpoint = "trackers/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Tracker.class, client);
    }

    /**
     * Get a list of all Tracker objects.
     *
     * @param params Map of parameters used to filter the list of Trackers.
     * @return TrackerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public TrackerCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "trackers";

        TrackerCollection trackerCollection =
                Requestor.request(RequestMethod.GET, endpoint, params, TrackerCollection.class, client);
        // we store the params in the collection so that we can use them to get the next page
        trackerCollection.setTrackingCode(InternalUtilities.getOrDefault(params, "tracking_code", null));
        trackerCollection.setCarrier(InternalUtilities.getOrDefault(params, "carrier", null));

        return trackerCollection;
    }

    /**
     * Get the next page of an TrackerCollection.
     *
     * @param collection TrackerCollection to get next page of.
     * @return TrackerCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public TrackerCollection getNextPage(TrackerCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an TrackerCollection.
     *
     * @param collection TrackerCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return TrackerCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public TrackerCollection getNextPage(TrackerCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, TrackerCollection>() {
            @Override @SneakyThrows
            public TrackerCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getTrackers(), pageSize);
    }

    /**
     * Create a list of Trackers.
     *
     * @param params Map of parameters used to create the Trackers.
     * @throws EasyPostException when the request fails.
     * @deprecated Use the create function instead. createList will be removed in a future release.
     */
    @Deprecated public void createList(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> newParams = new HashMap<String, Object>();
        newParams.put("trackers", params);

        String endpoint = "trackers/create_list";

        Requestor.request(RequestMethod.POST, endpoint, newParams, Object.class, client);
    }
}
