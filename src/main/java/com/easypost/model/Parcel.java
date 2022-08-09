package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.Map;

public final class Parcel extends EasyPostResource {
    private String id;
    private String predefinedPackage;
    private Float weight;
    private Float length;
    private Float width;
    private Float height;

    /**
     * Get the height of this Parcel.
     *
     * @return the height of this Parcel.
     */
    public Float getHeight() {
        return height;
    }

    /**
     * Set the height of this Parcel.
     *
     * @param height the height of this Parcel.
     */
    public void setHeight(final Float height) {
        this.height = height;
    }

    /**
     * Get the ID of this Parcel.
     *
     * @return the ID of this Parcel.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Parcel.
     *
     * @param id the ID of this Parcel.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the length of this Parcel.
     *
     * @return the length of this Parcel.
     */
    public Float getLength() {
        return length;
    }

    /**
     * Set the length of this Parcel.
     *
     * @param length the length of this Parcel.
     */
    public void setLength(final Float length) {
        this.length = length;
    }

    /**
     * Get the predefined package of this Parcel.
     *
     * @return the predefined package of this Parcel.
     */
    public String getPredefinedPackage() {
        return predefinedPackage;
    }

    /**
     * Set the predefined package of this Parcel.
     *
     * @param predefinedPackage the predefined package of this Parcel.
     */
    public void setPredefinedPackage(final String predefinedPackage) {
        this.predefinedPackage = predefinedPackage;
    }

    /**
     * Get the weight of this Parcel.
     *
     * @return the weight of this Parcel.
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * Set the weight of this Parcel.
     *
     * @param weight the weight of this Parcel.
     */
    public void setWeight(final Float weight) {
        this.weight = weight;
    }

    /**
     * Get the width of this Parcel.
     *
     * @return the width of this Parcel.
     */
    public Float getWidth() {
        return width;
    }

    /**
     * Set the width of this Parcel.
     *
     * @param width the width of this Parcel.
     */
    public void setWidth(final Float width) {
        this.width = width;
    }

    /**
     * Create a Parcel from a map of parameters.
     *
     * @param params the map of the parameters to create a Parcel from.
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public static Parcel create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Parcel from a map of parameters.
     *
     * @param params the map of the parameters to create a Parcel from.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public static Parcel create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("parcel", params);

        return request(RequestMethod.POST, classURL(Parcel.class), wrappedParams, Parcel.class, apiKey);
    }

    /**
     * Retrieve a Parcel from the API.
     *
     * @param id the ID of the Parcel to retrieve.
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public static Parcel retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Parcel from the API.
     *
     * @param id     the ID of the Parcel to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Parcel object.
     * @throws EasyPostException when the request fails.
     */
    public static Parcel retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Parcel.class, id), null, Parcel.class, apiKey);
    }
}
