// java -cp "target/easypost-java-2.0.2.jar:target/gson-2.2.2.jar" SmartPostExample
package com.easypost.app;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Parcel;
import com.easypost.model.Shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartPostExample {

    public static void main(String[] args) {
        EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi";
        
        Map<String, Object> fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("name", "Simpler Postage Inc");
        fromAddressMap.put("street1", "388 Townsend St");
        fromAddressMap.put("street2", "Apt 20");
        fromAddressMap.put("city", "San Francisco");
        fromAddressMap.put("state", "CA");
        fromAddressMap.put("zip", "94107");
        fromAddressMap.put("phone", "415-456-7890");

        Map<String, Object> toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("name", "Sawyer Bateman");
        toAddressMap.put("street1", "63 Ellis Street");
        toAddressMap.put("street2", "");
        toAddressMap.put("city", "San Francisco");
        toAddressMap.put("state", "CA");
        toAddressMap.put("zip", "94102");
        toAddressMap.put("phone", "415-482-2937");
        toAddressMap.put("country", "US");

        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("weight", 20);
        parcelMap.put("length", 6);
        parcelMap.put("width", 8);
        parcelMap.put("height", 1);

        try {
            Address fromAddress = Address.create(fromAddressMap);
            Address toAddress = Address.create(toAddressMap);
            Parcel parcel = Parcel.create(parcelMap);

            // Address verified = to_address.verify();

            // create shipment
            Map<String, Object> shipmentMap = new HashMap<String, Object>();
            shipmentMap.put("to_address", toAddress);
            shipmentMap.put("from_address", fromAddress);
            shipmentMap.put("parcel", parcel);

            Map<String, Object> shipmentOptions = new HashMap<String, Object>();
            shipmentOptions.put("smartpost_hub", 5552);
            shipmentOptions.put("smartpost_manifest", "123456789");
            shipmentMap.put("options", shipmentOptions);
            
            Shipment shipment = Shipment.create(shipmentMap);

            // buy postage
            List<String> buyServiceCodes = new ArrayList<String>();
            buyServiceCodes.add("fedex.smart_post");

            Map<String, Object> buyMap = new HashMap<String, Object>();
            buyMap.put("rate", shipment.lowestRate(buyServiceCodes));
            buyMap.put("insurance", 249.99);

            // shipment = shipment.buy(shipment.lowestRate(buyCarriers, buyServices));
            shipment = shipment.buy(buyMap);

            Map<String, Object> labelMap = new HashMap<String, Object>();
            labelMap.put("file_format", "pdf");
            shipment = shipment.label(labelMap);

            System.out.println(shipment.prettyPrint());
            
        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}