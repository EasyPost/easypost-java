package com.easypost.model;

public class CarrierDetail {
	String service;
	String containerType;
	String estDeliveryDateLocal;
	String estDeliveryTimeLocal;

	public String getService() { return service; }
	public void setService(String service) { this.service = service; }

	public String getContainerType() { return containerType; }
	public void setContainerType(String containerType) { this.containerType = containerType; }

	public String getEstDeliveryDateLocal() { return estDeliveryDateLocal; }
	public void setEstDeliveryDateLocal(String estDeliveryDateLocal) { this.estDeliveryDateLocal = estDeliveryDateLocal; }

	public String getEstDeliveryTimeLocal() { return estDeliveryTimeLocal; }
	public void setEstDeliveryTimeLocal(String estDeliveryTimeLocal) { this.estDeliveryTimeLocal = estDeliveryTimeLocal; }
}
