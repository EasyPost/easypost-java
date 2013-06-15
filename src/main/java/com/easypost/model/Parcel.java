package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Parcel extends EasyPostResource {
	public String id;
	String predefinedPackage;
	Float weight;
	Float length;
	Float width;
	Float height;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPredefinedPackage() {
		return predefinedPackage;
	}
	public void setPredefinedPackage(String predefinedPackage) {
		this.predefinedPackage = predefinedPackage;
	}

	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}


	// create
	public static Parcel create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Parcel create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("parcel", params);
		
		return request(RequestMethod.POST, classURL(Parcel.class), wrappedParams, Parcel.class, apiKey);
	}

	// retrieve
	public static Parcel retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Parcel retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Parcel.class, id), null, Parcel.class, apiKey);
	}

	// all
	public static ParcelCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static ParcelCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Parcel.class), params, ParcelCollection.class, apiKey);
	}

}