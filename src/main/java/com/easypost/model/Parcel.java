/**
 * Parcel.java
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

public final class Parcel extends EasyPostResource {
    public String id;
    private String predefinedPackage;
    private Float weight;
    private Float length;
    private Float width;
    private Float height;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPredefinedPackage() {
        return predefinedPackage;
    }

    public void setPredefinedPackage(final String predefinedPackage) {
        this.predefinedPackage = predefinedPackage;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(final Float weight) {
        this.weight = weight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(final Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(final Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(final Float height) {
        this.height = height;
    }


    // create
    public static Parcel create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Parcel create(final Map<String, Object> params,
                                final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("parcel", params);

        return request(RequestMethod.POST, classURL(Parcel.class),
                wrappedParams, Parcel.class, apiKey);
    }

    // retrieve
    public static Parcel retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Parcel retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Parcel.class, id), null,
                Parcel.class, apiKey);
    }

    // all
    public static ParcelCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static ParcelCollection all(final Map<String, Object> params,
                                       final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Parcel.class), params,
                ParcelCollection.class, apiKey);
    }

}
