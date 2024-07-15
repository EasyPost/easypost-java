package com.easypost.model;

import lombok.Getter;

@Getter
public class RecommendShipDateForShipmentResult {
    private TimeInTransitDetailsForShipDateRecommendation easypostTimeInTransitData;
    private Rate rate;
}
