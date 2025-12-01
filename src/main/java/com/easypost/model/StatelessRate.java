package com.easypost.model;

import lombok.Getter;

@Getter
public class StatelessRate {
    private Boolean deliveryDateGuaranteed;
    private Integer deliveryDays;
    private Integer estDeliveryDays;
    private String billingType;
    private String carrier;
    private String carrierAccountId;
    private String currency;
    private String deliveryDate;
    private String listCurrency;
    private String listRate;
    private String mode;
    private String object;
    private String rate;
    private String retailCurrency;
    private String retailRate;
    private String service;
}
