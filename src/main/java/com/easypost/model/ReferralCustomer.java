package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public class ReferralCustomer extends BaseUser {
    private List<ApiKey> apiKeys;
}
