/**
 * CarrierDetail.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
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

    public String getService() {
        return service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(final String containerType) {
        this.containerType = containerType;
    }

    public String getEstDeliveryDateLocal() {
        return estDeliveryDateLocal;
    }

    public void setEstDeliveryDateLocal(final String estDeliveryDateLocal) {
        this.estDeliveryDateLocal = estDeliveryDateLocal;
    }

    public String getEstDeliveryTimeLocal() {
        return estDeliveryTimeLocal;
    }

    public void setEstDeliveryTimeLocal(final String estDeliveryTimeLocal) {
        this.estDeliveryTimeLocal = estDeliveryTimeLocal;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(final String originLocation) {
        this.originLocation = originLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(final String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getGuaranteedDeliveryDate() {
        return guaranteedDeliveryDate;
    }

    public void setGuaranteedDeliveryDate(final String guaranteedDeliveryDate) {
        this.guaranteedDeliveryDate = guaranteedDeliveryDate;
    }

    public String getAlternateIdentifier() {
        return alternateIdentifier;
    }

    public void setAlternateIdentifier(final String alternateIdentifier) {
        this.alternateIdentifier = alternateIdentifier;
    }

    public String getInitialDeliveryAttempt() {
        return initialDeliveryAttempt;
    }

    public void setInitialDeliveryAttempt(final String initialDeliveryAttempt) {
        this.initialDeliveryAttempt = initialDeliveryAttempt;
    }
}
