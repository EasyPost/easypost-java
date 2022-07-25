package com.easypost.model;

import com.easypost.net.EasyPostResource;

public final class CarbonOffset extends EasyPostResource {

    private String currency;
    private int grams;
    private String price;
    private String object;

    /**
     * Get the currency of the provided price. Currently always USD.
     *
     * @return the currency of the provided price. Currently always USD.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Get the amount of carbon grams emitted by the shipment.
     *
     * @return the amount of carbon grams emitted by the shipment.
     */
    public int getGrams() {
        return grams;
    }

    /**
     * Get the price to offset the number of carbon grams.
     *
     * @return the price to offset the number of carbon grams.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Get the type of this object.
     *
     * @return the type of this object.
     */
    public String getObject() {
        return object;
    }
}
