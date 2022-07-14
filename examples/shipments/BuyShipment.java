import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class BuyShipment {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Shipment shipment = Shipment.retrieve("shp_...");
        Map<String, Object> buyMap = new HashMap<String, Object>();
        buyMap.put("rate", shipment.lowestRate());
        buyMap.put("insurance", 249.99);

        shipment.buy(buyMap);
    }
}
