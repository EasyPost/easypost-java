package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class TimeInTransitDetailsForDeliveryDate {
    private Date shipOnDate;
    private Float deliveryDateConfidence;
    private int estimatedTransitDays;
    private TimeInTransit daysInTransit;
}
