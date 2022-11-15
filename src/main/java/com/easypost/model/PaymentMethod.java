package com.easypost.model;

public class PaymentMethod extends EasyPostResource {

    public enum Priority {
        PRIMARY,
        SECONDARY
    }

    private String object;
    private PaymentMethodObject primaryPaymentMethod;
    private PaymentMethodObject secondaryPaymentMethod;

    /**
     * Get the PrimaryPaymentMethod of this PaymentMethod object.
     *
     * @return the PrimaryPaymentMethod of this PaymentMethod.
     * @deprecated Use {@link #getPrimaryPaymentMethodObject()} instead. 
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public PrimaryPaymentMethod getPrimaryPaymentMethod() {
        PrimaryPaymentMethod primaryPaymentMethod = new PrimaryPaymentMethod();
        primaryPaymentMethod.setId(this.primaryPaymentMethod.getId());
        primaryPaymentMethod.setObject(this.primaryPaymentMethod.getObject());
        primaryPaymentMethod.setBrand(this.primaryPaymentMethod.getBrand());
        primaryPaymentMethod.setExpMonth(String.valueOf(this.primaryPaymentMethod.getExpMonth()));
        primaryPaymentMethod.setExpYear(String.valueOf(this.primaryPaymentMethod.getExpYear()));
        return primaryPaymentMethod;
    }

    /**
     * Get the SecondaryPaymentMethod of this PaymentMethod object.
     *
     * @return the SecondaryPaymentMethod of this PaymentMethod.
     * @deprecated Use {@link #getSecondaryPaymentMethodObject()} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public SecondaryPaymentMethod getSecondaryPaymentMethod() {
        SecondaryPaymentMethod secondaryPaymentMethod = new SecondaryPaymentMethod();
        secondaryPaymentMethod.setId(this.secondaryPaymentMethod.getId());
        secondaryPaymentMethod.setObject(this.secondaryPaymentMethod.getObject());
        secondaryPaymentMethod.setBrand(this.secondaryPaymentMethod.getBrand());
        secondaryPaymentMethod.setExpMonth(String.valueOf(this.secondaryPaymentMethod.getExpMonth()));
        secondaryPaymentMethod.setExpYear(String.valueOf(this.secondaryPaymentMethod.getExpYear()));
        return secondaryPaymentMethod;
    }

    /**
     * Get the object of this PaymentMethod object.
     *
     * @return the object of this PaymentMethod.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the primary payment method of this PaymentMethod object.
     *
     * @return a PaymentMethodObject representing the primary payment method.
     */
    public PaymentMethodObject getPrimaryPaymentMethodObject() {
        return primaryPaymentMethod;
    }

    /**
     * Get the secondary payment method of this PaymentMethod object.
     *
     * @return a PaymentMethodObject representing the secondary payment method.
     */
    public PaymentMethodObject getSecondaryPaymentMethodObject() {
        return secondaryPaymentMethod;
    }
}
