import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveShipment {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Shipment shipment = Shipment.retrieve("shp_...");
    }
}
