package com.easypost.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public final class Order extends EasyPostResource {
    private String service;
    private String reference;
    private Boolean isReturn;
    private Address toAddress;
    private Address buyerAddress;
    private Address fromAddress;
    private Address returnAddress;
    private CustomsInfo customsInfo;
    private List<Shipment> shipments;
    private List<Rate> rates;
    private Map<String, Object> options;
    private List<ShipmentMessage> messages;
    private List<CarrierAccount> carrierAccounts;
}
