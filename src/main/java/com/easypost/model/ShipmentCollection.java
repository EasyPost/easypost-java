/**
 * ShipmentCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ShipmentCollection extends EasyPostResource {
    private List<Shipment> shipments;
    private Boolean hasMore;

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
