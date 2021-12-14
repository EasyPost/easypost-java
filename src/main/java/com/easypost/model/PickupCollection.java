/**
 * PickupCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class PickupCollection extends EasyPostResource {
    private List<Pickup> pickups;
    private Boolean hasMore;

    /**
     * Get this PickupCollection's Pickup objects.
     *
     * @return List of Pickup objects.
     */
    public List<Pickup> getPickups() {
        return pickups;
    }

    /**
     * Set this PickupCollection's Pickup objects.
     *
     * @param pickups List of Pickup objects.
     */
    public void setPickups(final List<Pickup> pickups) {
        this.pickups = pickups;
    }

    /**
     * Get whether there are more Pickup objects to retrieve.
     *
     * @return whether there are more Pickup objects to retrieve.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more Pickup objects to retrieve.
     *
     * @param hasMore whether there are more Pickup objects to retrieve.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
