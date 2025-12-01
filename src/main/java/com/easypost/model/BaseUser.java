package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class BaseUser extends EasyPostResource {
    private String name;
    private String email;
    private String phoneNumber;
    private String balance;
    private String rechargeAmount;
    private String secondaryRechargeAmount;
    private String rechargeThreshold;
    private String parentId;
    private boolean verified;
    private String pricePerShipment;
    private boolean hasBillingMethod;
    private String ccFeeRate;
    private String defaultInsuranceAmount;
    private String insuranceFeeRate;
    private String insuranceFeeMinimum;
    private List<User> children;
    private List<ApiKey> apiKeys;
}
