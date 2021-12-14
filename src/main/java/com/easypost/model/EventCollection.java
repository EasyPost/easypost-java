/**
 * EventCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class EventCollection extends EasyPostResource {
    private List<Event> events;
    private Boolean hasMore;

    /**
     * Get a list of events.
     *
     * @return List of Event objects.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Set a list of events.
     *
     * @param events List of Event objects.
     */
    public void setEvents(final List<Event> events) {
        this.events = events;
    }

    /**
     * Get whether there are more events to retrieve.
     *
     * @return whether there are more events to retrieve.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more events to retrieve.
     *
     * @param hasMore whether there are more events to retrieve.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
