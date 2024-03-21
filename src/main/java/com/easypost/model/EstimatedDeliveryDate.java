package com.easypost.model;

import lombok.Getter;

@Getter
public class EstimatedDeliveryDate {
    private EasyPostTimeInTransitData easypostTimeInTransitData;
    private Rate rate;
}

