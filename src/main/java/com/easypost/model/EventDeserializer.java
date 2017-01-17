package com.easypost.model;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.easypost.net.EasyPostResource;
import com.easypost.model.Event;

public class EventDeserializer implements JsonDeserializer<Event> {

	@SuppressWarnings("rawtypes")
	static Map<String, Class> objectMap = new HashMap<String, Class>();
    static {
        objectMap.put("Address", Address.class);
        objectMap.put("Batch", Batch.class);
        objectMap.put("CustomsInfo", CustomsInfo.class);
        objectMap.put("CustomsItem", CustomsItem.class);
        objectMap.put("Event", Event.class);
        objectMap.put("Parcel", Parcel.class);
        objectMap.put("PostageLabel", PostageLabel.class);
        objectMap.put("Rate", Rate.class);
        objectMap.put("Refund", Refund.class);
        objectMap.put("ScanForm", ScanForm.class);
        objectMap.put("Shipment", Shipment.class);
        objectMap.put("Tracker", Tracker.class);
        objectMap.put("TrackingDetail", TrackingDetail.class);
		objectMap.put("Webhook", Webhook.class);
    }

    private Object deserializeJsonPrimitive(JsonPrimitive element) {
    	if (element.isBoolean()) {
    		return element.getAsBoolean();
    	} else if (element.isNumber()) {
    		return element.getAsNumber();
    	} else {
    		return element.getAsString();
    	}
    }

    private Object[] deserializeJsonArray(JsonArray arr) {
    	Object[] elems = new Object[arr.size()];
    	Iterator<JsonElement> elemIter = arr.iterator();
    	int i = 0;
    	while (elemIter.hasNext()) {
    		JsonElement elem = elemIter.next();
    		elems[i++] = deserializeJsonElement(elem);
    	}
    	return elems;
    }

    private Object deserializeJsonElement(JsonElement element) {
    	if (element.isJsonNull()) {
    		return null;
    	} else if (element.isJsonObject()) {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			populateMapFromJSONObject(valueMap, element.getAsJsonObject());
			return valueMap;
		} else if (element.isJsonPrimitive()) {
			return deserializeJsonPrimitive(element.getAsJsonPrimitive());
		} else if (element.isJsonArray()) {
			return deserializeJsonArray(element.getAsJsonArray());
		} else {
			System.err.println("Unknown JSON element type for element " + element + ". " +
					"Please email us at support@easypost.com.");
			return null;
		}
	}

    private void populateMapFromJSONObject(Map<String, Object> objMap, JsonObject jsonObject) {
		for(Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
			String key = entry.getKey();
			JsonElement element = entry.getValue();
			objMap.put(key, deserializeJsonElement(element));
		}
    }

    @SuppressWarnings("unchecked")
	public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Event event = new Event();

		JsonObject jsonObject = json.getAsJsonObject();
		for(Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
			String key = entry.getKey();
			JsonElement element = entry.getValue();
			if("previous_attributes".equals(key)) {
				Map<String, Object> previousAttributes = new HashMap<String, Object>();
				populateMapFromJSONObject(previousAttributes, element.getAsJsonObject());
				event.setPreviousAttributes(previousAttributes);
        	} else if ("result".equals(key)) {
				String type = element.getAsJsonObject().get("object").getAsString();
				Class<EasyPostResource> cl = objectMap.get(type);
				EasyPostResource result = EasyPostResource.gson.fromJson(entry.getValue(), cl);
				event.setResult(result);
			}
		}
        event.setId(jsonObject.get("id").getAsString());
        event.setDescription(jsonObject.get("description").getAsString());
        event.setMode(jsonObject.get("mode").getAsString());

		return event;
	}
}
