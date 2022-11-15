package com.easypost.service;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Event;
import com.easypost.model.EventCollection;
import com.easypost.utils.Utilities;

public class EventService {
    private final EasyPostClient client;

    /**
     * EventService constructor.
     * 
     * @param client The client object.
     */
    EventService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Retrieve a Event from the API.
     *
     * @param id ID of the Event to retrieve
     * @return Event object
     * @throws EasyPostException when the request fails.
     */
    public Event retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Event.class, id), null, Event.class, client);
    }

    /**
     * Get a list of Events.
     *
     * @param params A map of parameters to pass to the API.
     * @return EventCollection object
     * @throws EasyPostException when the request fails.
     */
    public EventCollection all(final Map<String, Object> params) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Event.class), params, EventCollection.class,
                client);
    }
}
