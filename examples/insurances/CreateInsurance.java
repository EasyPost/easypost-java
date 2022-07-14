import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class CreateInsurance{
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

        Map<String, Object> insuranceMap = new HashMap<String, Object>();
        insuranceMap.put("to_address", toAddressMap);
        insuranceMap.put("from_address", fromAddressMap);
        insuranceMap.put("tracking_code","9400110898825022579493");
        insuranceMap.put("carrier", "USPS");
        insuranceMap.put("amount", "100.00");
        insuranceMap.put("reference", "insuranceRef1");

        Insurance insurance = Insurance.create(insuranceMap);
    }
}
