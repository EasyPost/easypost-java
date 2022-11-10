package com.easypost.model;

import java.util.Date;

import lombok.Getter;

@Getter
public final class TrackingDetail {
    private String status;
    private String message;
    private Date datetime;
    private TrackingLocation trackingLocation;
    private String statusDetail;
}
