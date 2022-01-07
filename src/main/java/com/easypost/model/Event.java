/**
 * Event.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Map;

public final class Event extends EasyPostResource {
    private String id;
    private String description;
    private String mode;
    private EasyPostResource result;
    private Map<String, Object> previousAttributes;


    /**
     * Get this Event's ID.
     *
     * @return Event's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set this Event's ID.
     *
     * @param id Event's ID
     */
    public void setId(final String id) {
        this.id = id;
    }

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
     * Get the mode of this Event.
     *
     * @return Event's mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this Event.
     *
     * @param mode Event's mode
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the result of this Event.
     *
     * @return Event's result
     */
    public EasyPostResource getResult() {
        return result;
    }

    /**
     * Set the result of this Event.
     *
     * @param result Event's result
     */
    public void setResult(final EasyPostResource result) {
        this.result = result;
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Event object
     * @throws EasyPostException when the request fails.
     */
    public static Event retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Event.class, id), null, Event.class, apiKey);
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
     * @param apiKey API key to use in request (ovverides default API key).
     * @return EventCollection object
     * @throws EasyPostException when the request fails.
     */
    public static EventCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(Event.class), params, EventCollection.class, apiKey);
    }
}
