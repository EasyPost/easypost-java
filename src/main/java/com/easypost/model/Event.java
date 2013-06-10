package com.easypost.model;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Event extends EasyPostResource {
	public String id;
	String type;
	Boolean mode;
	Long created;
	EventData data;
	Integer pendingWebhooks;

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

	public EventData getData() {
		return data;
	}

	public Long getCreated() {
		return created;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Boolean getMode() {
		return mode;
	}

	public Integer getPendingWebhooks() {
		return pendingWebhooks;
	}

}
