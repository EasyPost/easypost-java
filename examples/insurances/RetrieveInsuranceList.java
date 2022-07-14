import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

public class RetrieveInsuranceList {
    public static void main(String [] args){
        EasyPost.apiKey = "<EASYPOST_API_KEY>";

        Map<String, Object> list_params = new HashMap<>();
        list_params.put("page_size", 2);
        list_params.put("start_datetime", "2016-01-02T08:50:00Z");

        InsuranceCollection insurances = Insurance.all(list_params);
    }
}
