package com.easypost.model;

import lombok.Getter;

@Getter
public class EasyPostTimeInTransitData {
    private TimeInTransit daysInTransit;
    private String easypostEstimatedDeliveryDate;
    private String plannedShipDate;
}
