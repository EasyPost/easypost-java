package com.easypost.model;

import lombok.Getter;

@Getter
public class ShipDateForZipPairRecommendation {
    private String carrier;
    private String service;
    private TimeInTransitDetailsForShipDate easypostTimeInTransitData;
}
