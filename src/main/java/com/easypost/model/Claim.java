package com.easypost.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public class Claim extends EasyPostResource {
    private Date statusTimestamp;
    private List<ClaimHistoryEntry> history;
    private String approvedAmount;
    private String checkDeliveryAddress;
    private String contactEmail;
    private String description;
    private String insuranceAmount;
    private String insuranceId;
    private String paymentMethod;
    private String recipientName;
    private String requestedAmount;
    private String salvageValue;
    private String shipmentId;
    private String status;
    private String statusDetail;
    private String trackingCode;
    private String type;
}
