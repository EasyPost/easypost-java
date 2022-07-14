import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveTracker {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Tracker tracker = Tracker.retrieve("trk_...");
    }
}
