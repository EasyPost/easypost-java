import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class CreateParcel {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("height", 5);
        parcelMap.put("width", 10.9);
        parcelMap.put("length", 20.2);
        parcelMap.put("weight", 65.9);

        Parcel parcel = Parcel.create(parcelMap);
    }
}
