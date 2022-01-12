package com.easypost;

import com.easypost.model.Event;
import com.easypost.net.EasyPostResource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class EventTest {

    @Test
    public void testEventWithNullPreviousAttributes() {
        Map<String, String> eventData = new HashMap<String, String>();
        eventData.put("id", "rfnd_b550a6f968be4c61a2306e3c10c12345");
        eventData.put("object", "Refund");
        eventData.put("created_at", "2019-12-12T00:10:49Z");
        eventData.put("updated_at", "2019-12-12T00:10:49Z");
        eventData.put("tracking_code", "778827012345");
        eventData.put("confirmation_number", null);
        eventData.put("status", "refunded");
        eventData.put("carrier", "FedEx");
        eventData.put("shipment_id", "shp_b4fba1b233d94ecabfbb455ff9112345");

        Map<String, Object> eventWrapper = new HashMap<>();
        eventWrapper.put("result", eventData);
        eventWrapper.put("description", "refund.successful");
        eventWrapper.put("mode", "production");
        eventWrapper.put("previous_attributes", null);
        eventWrapper.put("pending_urls", new ArrayList<String>(
                Arrays.asList(
                        "hook_4ed7ff31c6974ff180e2463bb2912345",
                        "hook_b43bc6d4eb3848bfaaee8e131f712345")
                )
        );
        eventWrapper.put("completed_urls", null);
        eventWrapper.put("id", "evt_5786b354576049bb91e845b6d5712345");
        eventWrapper.put("user_id", "user_7b4c654836a743a492e59573a0512345");
        eventWrapper.put("status", "pending");
        eventWrapper.put("object", "Event");

        String eventJson = EasyPostResource.GSON.toJson(eventWrapper);

        Event event = EasyPostResource.GSON.fromJson(eventJson, Event.class);
        assertNull(event.getPreviousAttributes());
    }
}
