package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class CustomsItem extends EasyPostResource {
	public String id;
	String description;
	String hsTariffNumber;
	String originCountry;
	int quantity;
	Float value;
	Float weight;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getHsTariffNumber() {
		return hsTariffNumber;
	}
	public void setHsTariffNumber(String hsTariffNumber) {
		this.hsTariffNumber = hsTariffNumber;
	}

	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}

	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}


	// create
	public static CustomsItem create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static CustomsItem create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("customs_item", params);

		return request(RequestMethod.POST, classURL(CustomsItem.class), wrappedParams, CustomsItem.class, apiKey);
	}

	// retrieve
	public static CustomsItem retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static CustomsItem retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(CustomsItem.class, id), null, CustomsItem.class, apiKey);
	}

}
