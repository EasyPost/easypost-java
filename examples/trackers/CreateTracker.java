import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class CreateTracker {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        
        Map<String, Object> trackerMap = new HashMap<String, Object>();
        trackerMap.put("tracking_code","9400110898825022579493");
        trackerMap.put("carrier", "USPS");

        Tracker tracker = Tracker.create(trackerMap);
    }
}
