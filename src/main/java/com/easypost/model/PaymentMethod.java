package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class PaymentMethod extends EasyPostResource {
    private String id;
    private String object;
    private PrimaryPaymentMethod primaryPaymentMethod;
    private SecondaryPaymentMethod secondaryPaymentMethod;

    /**
     * List all payment methods.
     *
     * @return Billing object.
     * @throws EasyPostException when the request fails.
     */
    public static PaymentMethod all() throws EasyPostException {
        return all(null);
    }

    /**
     * List all payment methods.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Billing object.
     * @throws EasyPostException when the request fails.
     */
    public static PaymentMethod all(String apiKey) throws EasyPostException {
        PaymentMethod response = request(RequestMethod.GET, String.format("%s/%s", EasyPost.API_BASE,
        "payment_methods"), null, PaymentMethod.class, apiKey);

        if (response.getId() == null) {
            throw new EasyPostException("Billing has not been setup for this user. Please add a payment method.");
        }

        return response;
    }

    /**
     * Get ID of this PaymentMethod object.
     *
     * @return ID of this PaymentMethod.
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID of this PaymentMethod object.
     *
     * @param id ID of this PaymentMethod.
     */
    public void setId(String id) {
        this.id = id;
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
     * Set the object of this PaymentMethod object.
     *
     * @param object the object of this PaymentMethod.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get the PrimaryPaymentMethod of this PaymentMethod object.
     *
     * @return the PrimaryPaymentMethod of this PaymentMethod.
     */
    public PrimaryPaymentMethod getPrimaryPaymentMethod() {
        return primaryPaymentMethod;
    }

    /**
     * Set the PrimaryPaymentMethod of this PaymentMethod object.
     *
     * @param primaryPaymentMethod the PrimaryPaymentMethod of this PaymentMethod.
     */
    public void setPrimaryPaymentMethod(PrimaryPaymentMethod primaryPaymentMethod) {
        this.primaryPaymentMethod = primaryPaymentMethod;
    }

    /**
     * Get the SecondaryPaymentMethod of this PaymentMethod object.
     *
     * @return the SecondaryPaymentMethod of this PaymentMethod.
     */
    public SecondaryPaymentMethod getSecondaryPaymentMethod() {
        return secondaryPaymentMethod;
    }

    /**
     * Set the SecondaryPaymentMethod of this PaymentMethod object.
     *
     * @param secondaryPaymentMethod the SecondaryPaymentMethod of this PaymentMethod.
     */
    public void setSecondaryPaymentMethod(SecondaryPaymentMethod secondaryPaymentMethod) {
        this.secondaryPaymentMethod = secondaryPaymentMethod;
    }
}
