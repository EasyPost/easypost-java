package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class ShipmentCollection extends EasyPostResource {
    private List<Shipment> shipments;
    private Boolean hasMore;
}
