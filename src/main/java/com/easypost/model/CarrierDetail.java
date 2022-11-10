package com.easypost.model;

import lombok.Getter;

@Getter
public final class CarrierDetail {
    private String service;
    private String containerType;
    private String estDeliveryDateLocal;
    private String estDeliveryTimeLocal;
    private String originLocation;
    private String destinationLocation;
    private String guaranteedDeliveryDate;
    private String alternateIdentifier;
    private String initialDeliveryAttempt;
}
