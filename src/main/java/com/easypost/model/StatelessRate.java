package com.easypost.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class StatelessRate {
    @SerializedName("delivery_date_guaranteed")
    private Boolean deliveryDateGuaranteed;
    @SerializedName("delivery_days")
    private Integer deliveryDays;
    @SerializedName("est_delivery_days")
    private Integer estDeliveryDays;
    @SerializedName("billing_type")
    private String billingType;
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("carrier_account_id")
    private String carrierAccountId;
    @SerializedName("currency")
    private String currency;
    @SerializedName("delivery_date")
    private String deliveryDate;
    @SerializedName("list_currency")
    private String listCurrency;
    @SerializedName("list_rate")
    private String listRate;
    @SerializedName("mode")
    private String mode;
    @SerializedName("object")
    private String object;
    @SerializedName("rate")
    private String rate;
    @SerializedName("retail_currency")
    private String retailCurrency;
    @SerializedName("retail_rate")
    private String retailRate;
    @SerializedName("service")
    private String service;
}
