package com.easypost.service;

public class PaymentMethodService {
    private final EasyPostClient client;

    /**
     * PaymentMethodService constructor.
     *
     * @param client The client object.
     */
    PaymentMethodService(EasyPostClient client) {
        this.client = client;
    }
}
