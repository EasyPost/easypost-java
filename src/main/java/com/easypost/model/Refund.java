package com.easypost.model;

import lombok.Getter;

@Getter
public final class Refund extends EasyPostResource {
    private String trackingCode;
    private String confirmationNumber;
    private String status;
    private String carrier;
    private String shipmentId;
}
