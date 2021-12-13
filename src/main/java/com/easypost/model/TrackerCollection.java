/**
 * TrackerCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class TrackerCollection extends EasyPostResource {
    private List<Tracker> trackers;
    private Boolean hasMore;

    public List<Tracker> getTrackers() {
        return trackers;
    }

    public void setTrackers(final List<Tracker> trackers) {
        this.trackers = trackers;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
