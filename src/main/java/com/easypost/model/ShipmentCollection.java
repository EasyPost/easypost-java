package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ShipmentCollection extends EasyPostResource {
    private List<Shipment> shipments;
    private Boolean hasMore;

    /**
     * Get whether there are more Shipment objects to retrieve.
     *
     * @return true if there are more Shipment objects to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more Shipment objects to retrieve.
     *
     * @param hasMore true if there are more Shipment objects to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get this ShipmentCollection's Shipment objects.
     *
     * @return List of Shipment objects.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Set this ShipmentCollection's Shipment objects.
     *
     * @param shipments List of Shipment objects.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }
}
