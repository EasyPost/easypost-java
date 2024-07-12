package com.easypost.model;

import lombok.Getter;

@Getter
public class DeliveryDateForZipPairEstimate {
    private String carrier;
    private String service;
    private TimeInTransitDetailsForDeliveryDate easypostTimeInTransitData;
}
