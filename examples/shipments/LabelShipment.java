import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class LabelShipment {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Shipment shipment = Shipment.retrieve("shp_...");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("file_format", "ZPL");

        shipment.label(params);
    }
}
