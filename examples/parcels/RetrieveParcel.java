import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveParcel {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Parcel parcel = Parcel.retrieve("prcl_...");
    }
}
