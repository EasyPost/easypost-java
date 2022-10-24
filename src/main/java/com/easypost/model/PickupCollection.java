package com.easypost.model;

import java.util.List;

public final class PickupCollection extends EasyPostResource {
    private List<Pickup> pickups;
    private Boolean hasMore;

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
}
