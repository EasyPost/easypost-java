package com.easypost.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class RateDeserializer implements JsonDeserializer<Rate> {
  @Override
  public Rate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jo = (JsonObject) json;

    String currency;
    if (jo.get("currency").isJsonNull()) {
      currency = null;
    } else {
      currency = jo.get("currency").getAsString();
    }

    Float listRate;
    if (jo.get("list_rate").isJsonNull()) {
      listRate = null;
    } else {
      listRate = jo.get("list_rate").getAsFloat();
    }

    String listCurrency;
    if (jo.get("list_currency").isJsonNull()) {
      listCurrency = null;
    } else {
      listCurrency = jo.get("list_currency").getAsString();
    }

    Float retailRate;
    if (jo.get("retail_rate").isJsonNull()) {
      retailRate = null;
    } else {
      retailRate = jo.get("retail_rate").getAsFloat();
    }

    String retailCurrency;
    if (jo.get("retail_currency").isJsonNull()) {
      retailCurrency = null;
    } else {
      retailCurrency = jo.get("retail_currency").getAsString();
    }

    Number deliveryDays;
    if (jo.get("delivery_days").isJsonNull()) {
      deliveryDays = null;
    } else {
      deliveryDays = jo.get("delivery_days").getAsNumber();
    }

    String deliveryDate;
    if (jo.get("delivery_date").isJsonNull()) {
      deliveryDate = null;
    } else {
      deliveryDate = jo.get("delivery_date").getAsString();
    }

    Boolean deliveryDateGuaranteed;
    if (jo.get("delivery_date_guaranteed").isJsonNull()) {
      deliveryDateGuaranteed = false;
    } else {
      deliveryDateGuaranteed = Boolean.parseBoolean(jo.get("delivery_date_guaranteed").getAsString());
    }

    Number estDeliveryDays;
    if (jo.get("est_delivery_days").isJsonNull()) {
      estDeliveryDays = null;
    } else {
      estDeliveryDays = jo.get("est_delivery_days").getAsNumber();
    }

    String shipmentID;
    if (jo.get("shipment_id").isJsonNull()) {
      shipmentID = null;
    } else {
      shipmentID = jo.get("shipment_id").getAsString();
    }

    // TODO: Unpack this list so each item is on its own line even with formatting
    Rate rate = new Rate(jo.get("id").getAsString(), jo.get("carrier").getAsString(), jo.get("service").getAsString(),
        jo.get("rate").getAsFloat(), currency, listRate, listCurrency, retailRate, retailCurrency, deliveryDays,
        deliveryDate, deliveryDateGuaranteed, estDeliveryDays, shipmentID, jo.get("carrier_account_id").getAsString());

    return rate;
  }
}
