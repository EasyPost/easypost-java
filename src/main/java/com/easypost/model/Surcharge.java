package com.easypost.model;

import lombok.Getter;

@Getter
public final class Surcharge extends EasyPostResource {
    private String object;
    private String type;
    private String amount;
    private String list_amount;
    private String retail_amount;
    private String currency;
}
