package com.easypost.model;

import com.easypost.net.EasyPostResource;

public final class PickupRate extends EasyPostResource {
    private String id;
    private String mode;
    private String carrier;
    private String service;
    private Float rate;
    private String currency;

    /**
     * Get the ID of this PickupRate.
     *
     * @return ID of the PickupRate.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this PickupRate.
     *
     * @param id ID of the PickupRate.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of this PickupRate.
     *
     * @return mode of the PickupRate.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this PickupRate.
     *
     * @param mode mode of the PickupRate.
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the carrier of this PickupRate.
     *
     * @return carrier of the PickupRate.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set the carrier of this PickupRate.
     *
     * @param carrier carrier of the PickupRate.
     */
    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get the service of this PickupRate.
     *
     * @return service of the PickupRate.
     */
    public String getService() {
        return service;
    }

    /**
     * Set the service of this PickupRate.
     *
     * @param service service of the PickupRate.
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Get the rate of this PickupRate.
     *
     * @return rate of the PickupRate.
     */
    public Float getRate() {
        return rate;
    }

    /**
     * Set the rate of this PickupRate.
     *
     * @param rate rate of the PickupRate.
     */
    public void setRate(final Float rate) {
        this.rate = rate;
    }

    /**
     * Get the currency of this PickupRate.
     *
     * @return currency of the PickupRate.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set the currency of this PickupRate.
     *
     * @param currency currency of the PickupRate.
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
}
