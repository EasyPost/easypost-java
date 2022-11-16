package com.easypost.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public final class Tracker extends EasyPostResource {
    private String trackingCode;
    private String status;
    private String shipmentId;
    private String carrier;
    private List<TrackingDetail> trackingDetails;
    private float weight;
    private Date estDeliveryDate;
    private String signedBy;
    private CarrierDetail carrierDetail;
    private String publicUrl;
    private String statusDetail;
}
