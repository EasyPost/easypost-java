package com.easypost.model;

import lombok.Getter;

@Getter
public final class Fee {
    private String type;
    private float amount;
    private Boolean charged;
    private Boolean refunded;
}
