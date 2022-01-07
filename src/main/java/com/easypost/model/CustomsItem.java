/**
 * CustomsItem.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.Map;

public final class CustomsItem extends EasyPostResource {
    private String id;
    private String description;
    private String hsTariffNumber;
    private String originCountry;
    private int quantity;
    private Float value;
    private Float weight;
    private String code;
    private String currency;

    /**
     * Get this CustomsItem's ID.
     *
     * @return the ID of this CustomsItem.
     */
    public String getId() {
        return id;
    }

    /**
     * Set this CustomsItem's ID.
     *
     * @param id the ID of this CustomsItem.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get this CustomsItem's description.
     *
     * @return the description of this CustomsItem.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set this CustomsItem's description.
     *
     * @param description the description of this CustomsItem.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get this CustomsItem's HS Tariff Number.
     *
     * @return the HS Tariff Number of this CustomsItem.
     */
    public String getHsTariffNumber() {
        return hsTariffNumber;
    }

    /**
     * Set this CustomsItem's HS Tariff Number.
     *
     * @param hsTariffNumber the HS Tariff Number of this CustomsItem.
     */
    public void setHsTariffNumber(final String hsTariffNumber) {
        this.hsTariffNumber = hsTariffNumber;
    }

    /**
     * Get this CustomsItem's origin country.
     *
     * @return the origin country of this CustomsItem.
     */
    public String getOriginCountry() {
        return originCountry;
    }

    /**
     * Set this CustomsItem's origin country.
     *
     * @param originCountry the origin country of this CustomsItem.
     */
    public void setOriginCountry(final String originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * Get this CustomsItem's quantity.
     *
     * @return the quantity of this CustomsItem.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set this CustomsItem's quantity.
     *
     * @param quantity the quantity of this CustomsItem.
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get this CustomsItem's value.
     *
     * @return the value of this CustomsItem.
     */
    public Float getValue() {
        return value;
    }

    /**
     * Set this CustomsItem's value.
     *
     * @param value the value of this CustomsItem.
     */
    public void setValue(final Float value) {
        this.value = value;
    }

    /**
     * Get this CustomsItem's weight.
     *
     * @return the weight of this CustomsItem.
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * Set this CustomsItem's weight.
     *
     * @param weight the weight of this CustomsItem.
     */
    public void setWeight(final Float weight) {
        this.weight = weight;
    }

    /**
     * Get this CustomsItem's code.
     *
     * @return the code of this CustomsItem.
     */
    public String getCode() {
        return code;
    }

    /**
     * Set this CustomsItem's code.
     *
     * @param code the code of this CustomsItem.
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Get this CustomsItem's currency.
     *
     * @return the currency of this CustomsItem.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set this CustomsItem's currency.
     *
     * @param currency the currency of this CustomsItem.
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * Create a CustomsItem from a map of parameters.
     *
     * @param params the map of parameters.
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsItem create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a CustomsItem from a map of parameters.
     *
     * @param params the map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsItem create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_item", params);

        return request(RequestMethod.POST, classURL(CustomsItem.class), wrappedParams, CustomsItem.class, apiKey);
    }

    /**
     * Retrieve a CustomsItem from the API.
     *
     * @param id the ID of the CustomsItem to retrieve.
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsItem retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a CustomsItem from the API.
     *
     * @param id     the ID of the CustomsItem to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return CustomsItem object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsItem retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CustomsItem.class, id), null, CustomsItem.class, apiKey);
    }
}
