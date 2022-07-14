import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class CreateShipment {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Map<String, Object> toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("name", "Maggie Simpson");
        toAddressMap.put("street1", "742 Evergreen Terrace");
        toAddressMap.put("street2", "");
        toAddressMap.put("city", "Springfield");
        toAddressMap.put("state", "KY");
        toAddressMap.put("country", "US");
        toAddressMap.put("zip", "40069");

        Map<String, Object> fromAddressMap = new HashMap<String, Object>();

        Map<String, Object> parcelMap = new HashMap<String, Object>();

        Map<String, Object> customsInfoMap = new HashMap<String, Object>();

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", toAddressMap);
        shipmentMap.put("from_address", fromAddressMap);
        shipmentMap.put("parcel", parcelMap);
        shipmentMap.put("customs_info", customsInfoMap);

        Shipment shipment = Shipment.create(shipmentMap);
    }
}
