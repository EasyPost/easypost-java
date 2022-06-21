package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class TrackerCollection extends EasyPostResource {
    private List<Tracker> trackers;
    private Boolean hasMore;

    /**
     * Get whether there are more Trackers to retrieve.
     *
     * @return true if there are more Trackers to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more Trackers to retrieve.
     *
     * @param hasMore true if there are more Trackers to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get this TrackerCollection's Tracker objects.
     *
     * @return List of Tracker objects.
     */
    public List<Tracker> getTrackers() {
        return trackers;
    }

    /**
     * Set this TrackerCollection's Tracker objects.
     *
     * @param trackers List of Tracker objects.
     */
    public void setTrackers(final List<Tracker> trackers) {
        this.trackers = trackers;
    }
}
