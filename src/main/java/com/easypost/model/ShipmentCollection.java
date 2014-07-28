package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ShipmentCollection extends EasyPostResource {
  List<Shipment> shipments;
  Boolean hasMore;

  public List<Shipment> getShipments() {
    return shipments;
  }
  public void setShipments(List<Shipment> shipments) {
    this.shipments = shipments;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
