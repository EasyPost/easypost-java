// java -cp "target/easypost-java-2.0.4.jar:target/gson-2.2.2.jar" Webhook

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Parcel;
import com.easypost.model.CustomsItem;
import com.easypost.model.CustomsInfo;
import com.easypost.model.Shipment;
import com.easypost.model.Event;
import com.easypost.model.Tracker;
import com.easypost.net.EasyPostResource;

public class Webhook {

    public static void main(String[] args) {
        EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi";

        try {
            Event event = Event.retrieve("evt_ZzMEqURE");
            EasyPostResource tracker = event.getResult();

            System.out.println(event.getDescription());
            System.out.println(tracker.getStatus());
            System.out.println(tracker.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}
