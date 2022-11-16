package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class Insurance extends EasyPostResource {
    private String reference;
    private Address toAddress;
    private Address fromAddress;
    private Tracker tracker;
    private String provider;
    private String providerId;
    private String trackingCode;
    private String status;
    private String shipmentId;
    private String amount;
    private List<String> messages;
}
