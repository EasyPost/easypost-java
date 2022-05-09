package com.easypost.model;

public final class PickupRate extends Rate {
    private String pickupId;

    /**
     * Get the ID of this Pickup.
     *
     * @return the ID of this Pickup.
     */
    public String getPickupID() {
        return pickupId;
    }

    /**
     * Set the ID of this Pickup.
     *
     * @param pickupId the ID of this Pickup.
     */
    public void setPickupID(String pickupId) {
        this.pickupId = pickupId;
    }
}
