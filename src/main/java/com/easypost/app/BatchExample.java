package com.easypost.app;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Batch;
import com.easypost.model.CustomsInfo;
import com.easypost.model.CustomsItem;
import com.easypost.model.Parcel;
import com.easypost.model.Shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchExample {

    public static void main(final String[] args) throws InterruptedException {
        EasyPost.apiKey = System.getenv("EASYPOST_API_KEY");

        try {
            Map<String, Object> fromAddressMap = new HashMap<String, Object>();
            fromAddressMap.put("name", "Simpler Postage Inc");
            fromAddressMap.put("street1", "388 Townsend St");
            fromAddressMap.put("street2", "Apt 20");
            fromAddressMap.put("city", "San Francisco");
            fromAddressMap.put("state", "CA");
            fromAddressMap.put("zip", "94107");
            fromAddressMap.put("phone", "415-456-7890");
            Address fromAddress = Address.create(fromAddressMap);

            Map<String, Object> parcelMap = new HashMap<String, Object>();
            parcelMap.put("weight", 22.9);
            parcelMap.put("height", 12.1);
            parcelMap.put("width", 8);
            parcelMap.put("length", 19.8);
            Parcel parcel = Parcel.create(parcelMap);

            // Customs info - this is required for international destinations
            Map<String, Object> customsItem1Map = new HashMap<String, Object>();
            customsItem1Map.put("description", "EasyPost T-shirts");
            customsItem1Map.put("quantity", 2);
            customsItem1Map.put("value", 23.56);
            customsItem1Map.put("weight", 18.8);
            customsItem1Map.put("origin_country", "us");
            customsItem1Map.put("hs_tariff_number", "610910");

            Map<String, Object> customsItem2Map = new HashMap<String, Object>();
            customsItem2Map.put("description", "EasyPost Stickers");
            customsItem2Map.put("quantity", 11);
            customsItem2Map.put("value", 8.98);
            customsItem2Map.put("weight", 3.2);
            customsItem2Map.put("origin_country", "us");
            customsItem2Map.put("hs_tariff_number", "654321");

            Map<String, Object> customsInfoMap = new HashMap<String, Object>();
            customsInfoMap.put("customs_certify", true);
            customsInfoMap.put("customs_signer", "Dr. Pepper");
            customsInfoMap.put("contents_type", "gift");
            customsInfoMap.put("eel_pfc", "NOEEI 30.37(a)");
            customsInfoMap.put("non_delivery_option", "return");
            customsInfoMap.put("restriction_type", "none");
            CustomsItem customsItem1 = CustomsItem.create(customsItem1Map);
            CustomsItem customsItem2 = CustomsItem.create(customsItem2Map);
            List<CustomsItem> customsItemsList = new ArrayList<CustomsItem>();
            customsItemsList.add(customsItem1);
            customsItemsList.add(customsItem2);
            customsInfoMap.put("customs_items", customsItemsList);
            CustomsInfo customsInfo = CustomsInfo.create(customsInfoMap);

            // this will be coming from your database or other input source
            // hard coding it here for demonstration purposes only
            List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
            Map<String, Object> sampleOrder = new HashMap<String, Object>();
            sampleOrder.put("name", "Sawyer Bateman");
            sampleOrder.put("street1", "1A Larkspur Cres");
            sampleOrder.put("street2", "");
            sampleOrder.put("city", "St. Albert");
            sampleOrder.put("state", "AB");
            sampleOrder.put("zip", "T8N2M4");
            sampleOrder.put("phone", "780-483-2746");
            sampleOrder.put("country", "CA");
            sampleOrder.put("reference", "reference_number_123456");
            orders.add(sampleOrder);

            // loop over your orders and add a shipment for each
            List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
            Map<String, Object> shipment = new HashMap<String, Object>();
            Map<String, Object> toAddressMap = new HashMap<String, Object>();
            for (Map<String, Object> order : orders) {
                toAddressMap.put("name", order.get("name"));
                toAddressMap.put("street1", order.get("street1"));
                toAddressMap.put("street2", order.get("street2"));
                toAddressMap.put("city", order.get("city"));
                toAddressMap.put("state", order.get("state"));
                toAddressMap.put("zip", order.get("zip"));
                toAddressMap.put("phone", order.get("phone"));
                toAddressMap.put("country", order.get("country"));

                shipment.put("to_address", toAddressMap);
                shipment.put("from_address", fromAddress);
                shipment.put("parcel", parcel);
                shipment.put("customs_info", customsInfo);
                shipment.put("reference", order.get("reference"));

                shipments.add(shipment);
            }

            // create batch
            Map<String, Object> batchMap = new HashMap<String, Object>();
            batchMap.put("shipment", shipments);
            Batch batch = Batch.create(batchMap);

            // ** below this point should be a seperate script so that batch creation isn't
            // duplicated
            // ** store batch.id and use it in a new script with
            // Batch.retrieve("{BATCH_ID}");

            // loop through each shipment in the batch, purchasing the lowest rate for each
            for (Shipment createdShipment : batch.getShipments()) {
                // shipments in a new batch do not yet have rates, fetch them before purchasing
                createdShipment = createdShipment.newRates();

                List<String> buyCarriers = new ArrayList<String>();
                buyCarriers.add("USPS");
                // List<String> buyServices = new ArrayList<String>();
                // buyServices.add("FirstClassPackageServiceInternational");
                createdShipment.buy(createdShipment.lowestRate(buyCarriers));
            }

            // request a batch label of type pdf (other options are epl2 or zpl)
            batch = batch.refresh();
            if (batch.getBatchStatus().getPostagePurchased() == batch.getShipments().size()) {
                Map<String, Object> labelMap = new HashMap<String, Object>();
                labelMap.put("file_format", "pdf");

                batch.label(labelMap);
            } else {
                // something went wrong, one of the shipments wasn't purchased successfully in
                // the above loop
                // probably wouldn't ever happen, shipment.buy above would throw an error
                System.out.println("Uh oh");
            }

            // batch label creation is asyncronous; wait for it to be done before continuing
            while (true) {
                batch = batch.refresh();

                if (batch.getLabelUrl() != null) {
                    break;
                }

                Thread.sleep(5000);
            }

            System.out.println(batch.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}
