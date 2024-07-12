package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class TimeInTransitDetailsForShipDateRecommendation {
    private Date desiredDeliveryDate;
    private Float deliveryDateConfidence;
    private String estimatedTransitDays;
    private String shipOnDate;
    private TimeInTransit daysInTransit;
}
