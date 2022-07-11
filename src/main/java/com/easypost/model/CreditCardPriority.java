package com.easypost.model;

/**
 * CreditCardPriority is an enum class that represents the payment method priority levels.
 *
 * @deprecated Use {@link com.easypost.model.PaymentMethod.Priority} instead.
 * Last working version: v5.5.0. Removal: v7.0.0.
 */
@Deprecated
public enum CreditCardPriority {
    PRIMARY,
    SECONDARY
}
