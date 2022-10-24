package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;

import java.util.List;
import java.util.Map;

public final class Event extends EasyPostResource {
    private String description;
    private Map<String, Object> result;
    private Map<String, Object> previousAttributes;
    private List<String> pendingUrls;
    private List<String> completedUrls;

    /**
     * Get the description of this Event.
     *
     * @return Event's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this Event.
     *
     * @param description Event's description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the previous attributes of this Event.
     *
     * @return Event's previous attributes
     */
    public Map<String, Object> getPreviousAttributes() {
        return previousAttributes;
    }

    /**
     * Set the previous attributes of this Event.
     *
     * @param previousAttributes Event's previous attributes
     */
    public void setPreviousAttributes(final Map<String, Object> previousAttributes) {
        this.previousAttributes = previousAttributes;
    }

    /**
     * Get the result of this Event.
     *
     * @return Event's result
     */
    public Map<String, Object> getResult() {
        return result;
    }

    /**
     * Set the result of this Event.
     *
     * @param result Event's result
     */
    public void setResult(final Map<String, Object> result) {
        this.result = result;
    }

    /**
     * Get the pendingUrls of this Event.
     *
     * @return Event's pendingUrls
     */
    public List<String> getPendingUrls() {
        return this.pendingUrls;
    }

    /**
     * 
     * @param pendingUrls pendingUrls of this Event
     */
    public void setPendingUrls(List<String> pendingUrls) {
        this.pendingUrls = pendingUrls;
    }

    /**
     * Get the completedUrls of this Event.
     *
     * @return Event's completedUrls
     */
    public List<String> getCompletedUrls() {
        return this.completedUrls;
    }

    /**
     *
     * @param completedUrls pendingUrls of this Event
     */
    public void setCompletedUrls(List<String> completedUrls) {
        this.completedUrls = completedUrls;
    }

    /**
     * Retrieve a Event from the API.
     *
     * @param id ID of the Event to retrieve
     * @return Event object
     * @throws EasyPostException when the request fails.
     */
    public static Event retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Event from the API.
     *
     * @param id     ID of the Event to retrieve
     * @param apiKey API key to use in request (overrides default API key).
     * @return Event object
     * @throws EasyPostException when the request fails.
     */
    public static Event retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(Event.class, id), null, Event.class, apiKey);
    }

    /**
     * Get a list of Events.
     *
     * @param params A map of parameters to pass to the API.
     * @return EventCollection object
     * @throws EasyPostException when the request fails.
     */
    public static EventCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of Events.
     *
     * @param params A map of parameters to pass to the API.
     * @param apiKey API key to use in request (overrides default API key).
     * @return EventCollection object
     * @throws EasyPostException when the request fails.
     */
    public static EventCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, classURL(Event.class), params, EventCollection.class, apiKey);
    }
}
