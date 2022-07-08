package com.easypost.model;

import com.easypost.net.EasyPostResource;

/**
 * BaseCreditCard is a model class that represents the base of any credit card.
 *
 * @deprecated Use {@link com.easypost.model.PaymentMethodObject} instead.
 * Last working version: v4.0.5. Removal: v6.0.0.
 */
@Deprecated
public class BaseCreditCard extends EasyPostResource {
    private String id;
    private String object;
    private String name;
    private String last4;
    private String expMonth;
    private String expYear;
    private String brand;

    /**
     * Get the brand of this CreditCard object.
     *
     * @return the brand of this CreditCard.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set the brand of this CreditCard object.
     *
     * @param brand the brand of this CreditCard.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get the expMonth of this CreditCard object.
     *
     * @return the expMonth of this CreditCard.
     */
    public String getExpMonth() {
        return expMonth;
    }

    /**
     * Set the expMonth of this CreditCard object.
     *
     * @param expMonth the expMonth of this CreditCard.
     */
    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    /**
     * Get the expYear of this CreditCard object.
     *
     * @return the expYear of this CreditCard.
     */
    public String getExpYear() {
        return expYear;
    }

    /**
     * Set the expYear of this CreditCard object.
     *
     * @param expYear the expYear of this CreditCard.
     */
    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    /**
     * Get ID of this CreditCard object.
     *
     * @return ID of this CreditCard.
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID of this CreditCard object.
     *
     * @param id ID of this CreditCard.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the last 4 digits of this CreditCard object.
     *
     * @return the last 4 digits of this CreditCard.
     */
    public String getLast4() {
        return last4;
    }

    /**
     * Set the last 4 digits of this CreditCard object.
     *
     * @param last4 the last 4 digits of this CreditCard.
     */
    public void setLast4(String last4) {
        this.last4 = last4;
    }

    /**
     * Get the name of this CreditCard object.
     *
     * @return the name of this CreditCard.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this CreditCard object.
     *
     * @param name the name of this CreditCard.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the object of this CreditCard object.
     *
     * @return the object of this CreditCard.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set the object of this CreditCard object.
     *
     * @param object the object of this CreditCard.
     */
    public void setObject(String object) {
        this.object = object;
    }
}
