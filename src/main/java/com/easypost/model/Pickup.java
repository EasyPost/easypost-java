package com.easypost.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public final class Pickup extends EasyPostResource {
    private String status;
    private String reference;
    private Date minDatetime;
    private Date maxDatetime;
    private Boolean isAccountAddress;
    private String instructions;
    private List<ShipmentMessage> messages;
    private String confirmation;
    private Address address;
    private List<CarrierAccount> carrierAccounts;
    private List<PickupRate> pickupRates;
}
