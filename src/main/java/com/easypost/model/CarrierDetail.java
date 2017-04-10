package com.easypost.model;

public class CarrierDetail {
	String service;
	String containerType;
	String estDeliveryDateLocal;
	String estDeliveryTimeLocal;
	String originLocation;
	String destinationLocation;
	String guaranteedDeliveryDate;
	String alternateIdentifier;
	String initialDeliveryAttempt;

	public String getService() { return service; }
	public void setService(String service) { this.service = service; }

	public String getContainerType() { return containerType; }
	public void setContainerType(String containerType) { this.containerType = containerType; }

	public String getEstDeliveryDateLocal() { return estDeliveryDateLocal; }
	public void setEstDeliveryDateLocal(String estDeliveryDateLocal) { this.estDeliveryDateLocal = estDeliveryDateLocal; }

	public String getEstDeliveryTimeLocal() { return estDeliveryTimeLocal; }
	public void setEstDeliveryTimeLocal(String estDeliveryTimeLocal) { this.estDeliveryTimeLocal = estDeliveryTimeLocal; }

	public String getOriginLocation() {
		return originLocation;
	}
	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public String getGuaranteedDeliveryDate() {
		return guaranteedDeliveryDate;
	}
	public void setGuaranteedDeliveryDate(String guaranteedDeliveryDate) {
		this.guaranteedDeliveryDate = guaranteedDeliveryDate;
	}

	public String getAlternateIdentifier() {
		return alternateIdentifier;
	}
	public void setAlternateIdentifier(String alternateIdentifier) {
		this.alternateIdentifier = alternateIdentifier;
	}

	public String getInitialDeliveryAttempt() {
		return initialDeliveryAttempt;
	}
	public void setInitialDeliveryAttempt(String initialDeliveryAttempt) {
		this.initialDeliveryAttempt = initialDeliveryAttempt;
	}
}
