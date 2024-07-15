package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class TimeInTransitDetailsForDeliveryDate {
    private Date easypostEstimatedDeliveryDate;
    private TimeInTransit daysInTransit;
}
