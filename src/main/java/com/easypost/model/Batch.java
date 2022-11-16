package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class Batch extends EasyPostResource {
    private String state;
    private BatchStatus status;
    private Number numShipments;
    private List<Shipment> shipments;
    private String labelUrl;
    private ScanForm scanForm;
    private String reference;
}
