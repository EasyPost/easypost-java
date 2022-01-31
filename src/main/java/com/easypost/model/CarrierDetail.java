package com.easypost.model;

public final class CarrierDetail {
    private String service;
    private String containerType;
    private String estDeliveryDateLocal;
    private String estDeliveryTimeLocal;
    private String originLocation;
    private String destinationLocation;
    private String guaranteedDeliveryDate;
    private String alternateIdentifier;
    private String initialDeliveryAttempt;

    /**
     * Get carrier service.
     *
     * @return carrier service
     */
    public String getService() {
        return service;
    }

    /**
     * Set carrier service.
     *
     * @param service carrier service
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Get carrier container type.
     *
     * @return carrier container type
     */
    public String getContainerType() {
        return containerType;
    }

    /**
     * Set carrier container type.
     *
     * @param containerType carrier container type
     */
    public void setContainerType(final String containerType) {
        this.containerType = containerType;
    }

    /**
     * Get the estimated delivery date in local time.
     *
     * @return estimated delivery date in local time
     */
    public String getEstDeliveryDateLocal() {
        return estDeliveryDateLocal;
    }

    /**
     * Set the estimated delivery date in local time.
     *
     * @param estDeliveryDateLocal estimated delivery date in local time
     */
    public void setEstDeliveryDateLocal(final String estDeliveryDateLocal) {
        this.estDeliveryDateLocal = estDeliveryDateLocal;
    }

    /**
     * Get the estimated delivery time in local time.
     *
     * @return estimated delivery time in local time
     */
    public String getEstDeliveryTimeLocal() {
        return estDeliveryTimeLocal;
    }

    /**
     * Set the estimated delivery time in local time.
     *
     * @param estDeliveryTimeLocal estimated delivery time in local time
     */
    public void setEstDeliveryTimeLocal(final String estDeliveryTimeLocal) {
        this.estDeliveryTimeLocal = estDeliveryTimeLocal;
    }

    /**
     * Get origin location.
     *
     * @return origin location
     */
    public String getOriginLocation() {
        return originLocation;
    }

    /**
     * Set origin location.
     *
     * @param originLocation origin location
     */
    public void setOriginLocation(final String originLocation) {
        this.originLocation = originLocation;
    }

    /**
     * Get destination location.
     *
     * @return destination location
     */
    public String getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * Set destination location.
     *
     * @param destinationLocation destination location
     */
    public void setDestinationLocation(final String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    /**
     * Get the guaranteed delivery date.
     *
     * @return guaranteed delivery date
     */
    public String getGuaranteedDeliveryDate() {
        return guaranteedDeliveryDate;
    }

    /**
     * Set the guaranteed delivery date.
     *
     * @param guaranteedDeliveryDate guaranteed delivery date
     */
    public void setGuaranteedDeliveryDate(final String guaranteedDeliveryDate) {
        this.guaranteedDeliveryDate = guaranteedDeliveryDate;
    }

    /**
     * Get alternate identifier.
     *
     * @return alternate identifier
     */
    public String getAlternateIdentifier() {
        return alternateIdentifier;
    }

    /**
     * Set alternate identifier.
     *
     * @param alternateIdentifier alternate identifier
     */
    public void setAlternateIdentifier(final String alternateIdentifier) {
        this.alternateIdentifier = alternateIdentifier;
    }

    /**
     * Get initial delivery attempt.
     *
     * @return initial delivery attempt
     */
    public String getInitialDeliveryAttempt() {
        return initialDeliveryAttempt;
    }

    /**
     * Set initial delivery attempt.
     *
     * @param initialDeliveryAttempt initial delivery attempt
     */
    public void setInitialDeliveryAttempt(final String initialDeliveryAttempt) {
        this.initialDeliveryAttempt = initialDeliveryAttempt;
    }
}
