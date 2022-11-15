package com.easypost.model;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseCreditCard is a model class that represents the base of any credit card.
 *
 * @deprecated Use {@link com.easypost.model.PaymentMethodObject} instead.
 * Deprecated: v5.5.0 - v7.0.0
 */
@Deprecated
@Getter
public class BaseCreditCard extends EasyPostResource {
    private @Setter String brand;
    private @Setter String expMonth;
    private @Setter String expYear;
    private @Setter String id;
    private @Setter String object;
    private String last4;
    private String name;
}
