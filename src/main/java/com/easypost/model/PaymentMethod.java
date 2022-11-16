package com.easypost.model;

import lombok.Getter;

@Getter
public class PaymentMethod extends EasyPostResource {

    public enum Priority {
        PRIMARY,
        SECONDARY
    }

    private String object;
    private PaymentMethodObject primaryPaymentMethod;
    private PaymentMethodObject secondaryPaymentMethod;
}
