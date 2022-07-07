package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class PaymentMethodObject extends EasyPostResource {

    public enum PaymentMethodType {
        CREDIT_CARD("credit_cards"),
        BANK_ACCOUNT("bank_accounts");

        private final String endpoint;

        /**
         * Constructor.
         *
         * @param endpoint the endpoint for this type of payment method.
         */
        PaymentMethodType(String endpoint) {
            this.endpoint = endpoint;
        }

        /**
         * Get the endpoint for this type of payment method.
         *
         * @return the endpoint for this type of payment method.
         */
        String getEndpoint() {
            return endpoint;
        }
    }

    // bank_account
    private String country;
    // bank_account
    private String bankName;
    // credit_card
    private String brand;
    // both
    private String disabledAt;
    // credit_card
    private String expMonth;
    // credit_card
    private String expYear;
    // both
    private String id;
    // both
    private String last4;
    // credit_card
    private String name;
    // both
    private String object;
    // bank_account
    private boolean verified;

    /**
     * Get the bank name of this PaymentMethodObject object.
     *
     * @return the bank name of this PaymentMethodObject.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Get the brand of this PaymentMethodObject object.
     *
     * @return the brand of this PaymentMethodObject.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get the country of this PaymentMethodObject object.
     *
     * @return the country of this PaymentMethodObject.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get when this PaymentMethodObject object was disabled.
     *
     * @return when this PaymentMethodObject was disabled.
     */
    public String getDisabledAt() {
        return disabledAt;
    }

    /**
     * Get the expMonth of this PaymentMethodObject object.
     *
     * @return the expMonth of this PaymentMethodObject.
     */
    public String getExpMonth() {
        return expMonth;
    }

    /**
     * Get the expYear of this PaymentMethodObject object.
     *
     * @return the expYear of this PaymentMethodObject.
     */
    public String getExpYear() {
        return expYear;
    }

    /**
     * Get ID of this PaymentMethodObject object.
     *
     * @return ID of this PaymentMethodObject.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the last 4 digits of this PaymentMethodObject object.
     *
     * @return the last 4 digits of this PaymentMethodObject.
     */
    public String getLast4() {
        return last4;
    }

    /**
     * Get the name of this PaymentMethodObject object.
     *
     * @return the name of this PaymentMethodObject.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the object of this PaymentMethodObject object.
     *
     * @return the object of this PaymentMethodObject.
     */
    public String getObject() {
        return object;
    }

    /**
     * Get whether this PaymentMethodObject object is verified.
     *
     * @return true if this PaymentMethodObject is verified, false otherwise.
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Get the type of this PaymentMethodObject object.
     *
     * @return the type of this PaymentMethodObject.
     */
    public PaymentMethodType getType() {
        PaymentMethodType type = null;
        if (getId() == null) {
            return null;
        }
        if (getId().startsWith("card_")) {
            type = PaymentMethodType.CREDIT_CARD;
        } else if (getId().startsWith("bank_")) {
            type = PaymentMethodType.BANK_ACCOUNT;
        }
        return type;
    }

    /**
     * Get the endpoint for this PaymentMethodObject object.
     *
     * @return the endpoint for this PaymentMethodObject.
     */
    String getEndpoint() {
        PaymentMethodType type = getType();
        if (type == null) {
            return null;
        }
        return type.getEndpoint();
    }
}
