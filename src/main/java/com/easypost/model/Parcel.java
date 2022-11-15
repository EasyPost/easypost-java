package com.easypost.model;

public final class Parcel extends EasyPostResource {
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
}
