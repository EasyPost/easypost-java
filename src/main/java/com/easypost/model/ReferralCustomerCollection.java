package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public final class ReferralCustomerCollection extends EasyPostResource {
    private List<ReferralCustomer> referralCustomers;
    private Boolean hasMore;
}
