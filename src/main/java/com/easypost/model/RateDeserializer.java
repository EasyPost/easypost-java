package com.easypost.model;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import com.easypost.model.Rate;

public class RateDeserializer implements JsonDeserializer<Rate> {
	@Override
	public Rate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jo = (JsonObject) json;

		return new Rate(
			jo.get("id").getAsString(),
			jo.get("carrier").getAsString(),
			jo.get("service").getAsString(),
			jo.get("rate").getAsFloat(),
			jo.get("shipment_id").getAsString()
		);
	}
}