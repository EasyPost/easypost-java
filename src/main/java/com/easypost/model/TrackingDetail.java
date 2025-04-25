package com.easypost.model;

import java.util.Date;

import lombok.Getter;

@Getter
public final class TrackingDetail {
    private String message;
    private String description;
    private String status;
    private String statusDetail;
    private Date datetime;
    private String source;
    private String carrierCode;
    private TrackingLocation trackingLocation;
    private String estDeliveryDate;
}
