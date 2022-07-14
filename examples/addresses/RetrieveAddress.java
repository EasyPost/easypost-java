import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveAddress{
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Address address = Address.retrieve("adr_...");
    }
}
