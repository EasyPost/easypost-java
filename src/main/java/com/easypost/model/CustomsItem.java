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
    public String id;
    private String description;
    private String hsTariffNumber;
    private String originCountry;
    private int quantity;
    private Float value;
    private Float weight;
    private String code;
    private String currency;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getHsTariffNumber() {
        return hsTariffNumber;
    }

    public void setHsTariffNumber(final String hsTariffNumber) {
        this.hsTariffNumber = hsTariffNumber;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(final String originCountry) {
        this.originCountry = originCountry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(final Float value) {
        this.value = value;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(final Float weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }


    // create
    public static CustomsItem create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static CustomsItem create(final Map<String, Object> params,
                                     final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_item", params);

        return request(RequestMethod.POST, classURL(CustomsItem.class),
                wrappedParams, CustomsItem.class, apiKey);
    }

    // retrieve
    public static CustomsItem retrieve(final String id)
            throws EasyPostException {
        return retrieve(id, null);
    }

    public static CustomsItem retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CustomsItem.class, id),
                null, CustomsItem.class, apiKey);
    }

}
