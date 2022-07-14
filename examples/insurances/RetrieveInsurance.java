import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveInsurance {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Insurance insurance = Insurance.retrieve("ins_...");
    }
}
