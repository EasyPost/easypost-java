package com.easypost.model;

import lombok.Getter;

@Getter
public final class ShipmentMessage {
    private String carrier;
    private String carrierAccountId;
    private String type;
    private Object message;
}
