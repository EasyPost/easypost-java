package com.easypost.model;

import lombok.Getter;

@Getter
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
    private int expMonth;
    // credit_card
    private int expYear;
    // both
    private String last4;
    // credit_card
    private String name;
    // bank_account
    private boolean verified;

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
        String objectType = getObject();
        if (getId().startsWith("card_") || (objectType != null && objectType.equals("CreditCard"))) {
            type = PaymentMethodType.CREDIT_CARD;
        } else if (getId().startsWith("bank_") || (objectType != null && objectType.equals("BankAccount"))) {
            type = PaymentMethodType.BANK_ACCOUNT;
        }
        return type;
    }

    /**
     * Get the endpoint for this PaymentMethodObject object.
     *
     * @return the endpoint for this PaymentMethodObject.
     */
    public String getEndpoint() {
        PaymentMethodType type = getType();
        if (type == null) {
            return null;
        }
        return type.getEndpoint();
    }
}
