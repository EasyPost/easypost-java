package com.easypost.model;

import lombok.Getter;

@Getter
public final class CarbonOffset extends EasyPostResource {
    private String currency;
    private int grams;
    private String price;
    private String object;
}
