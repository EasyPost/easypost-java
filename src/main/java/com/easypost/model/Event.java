package com.easypost.model;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Event extends EasyPostResource {
	public String id;
	String description;
	String mode;
	EasyPostResource result;
	Map<String, Object> previousAttributes;


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

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public EasyPostResource getResult() {
		return result;
	}
	public void setResult(EasyPostResource result) {
		this.result = result;
	}

	public Map<String, Object> getPreviousAttributes() {
		return previousAttributes;
	}
	public void setPreviousAttributes(Map<String, Object> previousAttributes) {
		this.previousAttributes = previousAttributes;
	}


	public static Event retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}

	public static EventCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}

	public static Event retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Event.class, id), null, Event.class, apiKey);
	}

	public static EventCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Event.class), params, EventCollection.class, apiKey);
	}
}
