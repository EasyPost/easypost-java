package com.easypost.model;

import lombok.Getter;

@Getter
public final class CustomsItem extends EasyPostResource {
    private String description;
    private String hsTariffNumber;
    private String originCountry;
    private int quantity;
    private Float value;
    private Float weight;
    private String code;
    private String currency;
}
