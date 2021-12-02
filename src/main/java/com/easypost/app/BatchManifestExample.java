import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Batch;
import com.easypost.model.Shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchManifestExample {

    public static void main(String[] args) throws InterruptedException {
        EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi";

        try {
            Map<String, Object> addressMap = new HashMap<String, Object>();
            addressMap.put("name", "Simpler Postage Inc");
            addressMap.put("street1", "164 Townsend St");
            addressMap.put("street2", "Unit 1");
            addressMap.put("city", "San Francisco");
            addressMap.put("state", "CA");
            addressMap.put("zip", "94107");
            addressMap.put("phone", "415-456-7890");

            Map<String, Object> parcelMap = new HashMap<String, Object>();
            parcelMap.put("weight", 22.9);
            parcelMap.put("height", 12.1);
            parcelMap.put("width", 8);
            parcelMap.put("length", 19.8);

            // create two shipments
            Map<String, Object> shipmentMap = new HashMap<String, Object>();
            shipmentMap.put("to_address", addressMap);
            shipmentMap.put("from_address", addressMap);
            shipmentMap.put("parcel", parcelMap);
            Shipment shipment1 = Shipment.create(shipmentMap);
            Shipment shipment2 = Shipment.create(shipmentMap);

            // create batch
            Batch batch = Batch.create();

            // wait until batch is created asynchronously
            while(true) {
                batch = batch.refresh();

                if (batch.getState() == "created") {
                    break;
                }

                Thread.sleep(3000);
            }

            // add shipments to batch
            List<Shipment> shipments = new ArrayList<Shipment>();
            shipments.add(shipment1);
            shipments.add(shipment2);
            batch.addShipments(shipments);

            // create manifest
            batch.createScanForm();

            // batch label creation is asyncronou
            while(true) {
                batch = batch.refresh();

                if (batch.getScanForm() != null) {
                    break;
                }

                Thread.sleep(8000);
            }

            System.out.println(batch.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}
