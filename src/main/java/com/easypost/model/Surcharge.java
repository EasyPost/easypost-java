package com.easypost.model;

import lombok.Getter;

@Getter
public final class Surcharge {
    private String object;
    private String type;
    private String amount;
    private String listAmount;
    private String retailAmount;
    private String currency;
}
