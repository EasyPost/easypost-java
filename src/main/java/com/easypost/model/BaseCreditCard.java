package com.easypost.model;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseCreditCard is a model class that represents the base of any credit card.
 *
 * @deprecated Use {@link com.easypost.model.PaymentMethodObject} instead.
 *             Deprecated: v5.5.0 - v7.0.0
 */
@Deprecated
@Getter @Setter // Setter is need for PaymentMethod class.
public class BaseCreditCard extends EasyPostResource {
    private String id;
    private String object;
    private String name;
    private String last4;
    private String expMonth;
    private String expYear;
    private String brand;

    /**
     * Set ID of this payment.
     * @param id ID of the payment
     */
    public void setId(final String id) {
        // This setId function is needed for PaymentMethod class.
        this.id = id;
    }
}
