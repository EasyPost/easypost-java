import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveAddressList{
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Map<String, Object> params = new HashMap<>();

        list_params.put("page_size", 5);

        AddressCollection addresses = Address.all(list_params);
    }
}
