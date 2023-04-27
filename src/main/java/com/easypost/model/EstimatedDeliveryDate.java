package com.easypost.model;

import lombok.Getter;

@Getter
public class EstimatedDeliveryDate {
    private EasyPostTimeInTransitData easypostTimeInTransitData;
    private Rate rate;
}

@Getter
class EasyPostTimeInTransitData {
    private TimeInTransit daysInTransit;
    private String easypostEstimatedDeliveryDate;
    private String plannedShipDate;
}
