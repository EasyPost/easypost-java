package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.Map;

public class EventData extends EasyPostResource {
	Map<String, Object> previousAttributes;
	EasyPostResource object;
	
	public Map<String, Object> getPreviousAttributes() {
		return previousAttributes;
	}
	public void setPreviousAttributes(Map<String, Object> previousAttributes) {
		this.previousAttributes = previousAttributes;
	}

	public EasyPostResource getObject() {
		return object;
	}
	public void setObject(EasyPostResource object) {
		this.object = object;
	}
}
