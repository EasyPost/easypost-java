/**
 * RateDeserializer.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class RateDeserializer implements JsonDeserializer<Rate> {
    @Override
    public Rate deserialize(final JsonElement json, final Type typeOfT,
                            final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jo = (JsonObject) json;

        String currency;
        JsonElement currencyJsonElement = jo.get("currency");
        if (currencyJsonElement == null || currencyJsonElement.isJsonNull()) {
            currency = null;
        } else {
            currency = jo.get("currency").getAsString();
        }

        Float listRate;
        JsonElement listRateJsonElement = jo.get("list_rate");
        if (listRateJsonElement == null || listRateJsonElement.isJsonNull()) {
            listRate = null;
        } else {
            listRate = jo.get("list_rate").getAsFloat();
        }

        String listCurrency;
        JsonElement listCurrencyJsonElement = jo.get("list_currency");
        if (listCurrencyJsonElement == null ||
                listCurrencyJsonElement.isJsonNull()) {
            listCurrency = null;
        } else {
            listCurrency = jo.get("list_currency").getAsString();
        }

        Float retailRate;
        JsonElement retailRateJsonElement = jo.get("retail_rate");
        if (retailRateJsonElement == null ||
                retailRateJsonElement.isJsonNull()) {
            retailRate = null;
        } else {
            retailRate = jo.get("retail_rate").getAsFloat();
        }

        String retailCurrency;
        JsonElement retailCurrencyJsonElement = jo.get("retail_currency");
        if (retailCurrencyJsonElement == null ||
                retailCurrencyJsonElement.isJsonNull()) {
            retailCurrency = null;
        } else {
            retailCurrency = jo.get("retail_currency").getAsString();
        }

        Number deliveryDays;
        JsonElement deliveryDaysJsonElement = jo.get("delivery_days");
        if (deliveryDaysJsonElement == null ||
                deliveryDaysJsonElement.isJsonNull()) {
            deliveryDays = null;
        } else {
            deliveryDays = jo.get("delivery_days").getAsNumber();
        }

        String deliveryDate;
        JsonElement deliveryDateJsonElement = jo.get("delivery_date");
        if (deliveryDateJsonElement == null ||
                deliveryDateJsonElement.isJsonNull()) {
            deliveryDate = null;
        } else {
            deliveryDate = jo.get("delivery_date").getAsString();
        }

        Boolean deliveryDateGuaranteed;
        JsonElement deliveryDateGuaranteedJsonElement =
                jo.get("delivery_date_guaranteed");
        if (deliveryDateGuaranteedJsonElement == null ||
                deliveryDateGuaranteedJsonElement.isJsonNull()) {
            deliveryDateGuaranteed = false;
        } else {
            deliveryDateGuaranteed = new Boolean(
                    jo.get("delivery_date_guaranteed").getAsString());
        }

        Number estDeliveryDays;
        JsonElement estDeliveryDaysJsonElement = jo.get("est_delivery_days");
        if (estDeliveryDaysJsonElement == null ||
                estDeliveryDaysJsonElement.isJsonNull()) {
            estDeliveryDays = null;
        } else {
            estDeliveryDays = jo.get("est_delivery_days").getAsNumber();
        }

        String shipmentID;
        JsonElement shipmentIdJsonElement = jo.get("shipment_id");
        if (shipmentIdJsonElement == null ||
                shipmentIdJsonElement.isJsonNull()) {
            shipmentID = null;
        } else {
            shipmentID = jo.get("shipment_id").getAsString();
        }

        TimeInTransit timeInTransit;
        JsonElement timeInTransitJsonElement = jo.get("time_in_transit");
        if (timeInTransitJsonElement == null ||
                timeInTransitJsonElement.isJsonNull()) {
            timeInTransit = null;
        } else {
            timeInTransit = (TimeInTransit) EasyPostResource.gson.fromJson(
                    timeInTransitJsonElement, TimeInTransit.class);
        }

        return new Rate(jo.get("id").getAsString(),
                jo.get("carrier").getAsString(),
                jo.get("service").getAsString(), jo.get("rate").getAsFloat(),
                currency, listRate, listCurrency, retailRate, retailCurrency,
                deliveryDays, deliveryDate, deliveryDateGuaranteed,
                estDeliveryDays, shipmentID,
                jo.get("carrier_account_id").getAsString(), timeInTransit);
    }
}
