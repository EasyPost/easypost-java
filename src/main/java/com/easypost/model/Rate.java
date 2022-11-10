package com.easypost.model;

import lombok.Getter;

@Getter
public class Rate extends EasyPostResource {
    private String carrier;
    private String service;
    private Float rate;
    private String currency;
    private Float listRate;
    private String listCurrency;
    private Float retailRate;
    private String retailCurrency;
    private Number deliveryDays;
    private String deliveryDate;
    private Boolean deliveryDateGuaranteed;
    private Number estDeliveryDays;
    private String shipmentId;
    private String carrierAccountId;
    private String billingType;
    private CarbonOffset carbonOffset;
}
