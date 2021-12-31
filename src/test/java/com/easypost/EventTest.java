package com.easypost;

import com.easypost.model.Event;
import com.easypost.net.EasyPostResource;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class EventTest {

    @Test
    public void testEventWithNullPreviousAttributes() {
        String eventJson =
                "{\"result\": {\"id\": \"rfnd_b550a6f968be4c61a2306e3c10c12345\", \"object\": \"Refund\", \"created_at\": \"2019-12-12T00:10:49Z\", \"updated_at\": \"2019-12-12T00:10:49Z\", \"tracking_code\": \"778827012345\", \"confirmation_number\": null, \"status\": \"refunded\", \"carrier\": \"FedEx\", \"shipment_id\": \"shp_b4fba1b233d94ecabfbb455ff9112345\"}, \"description\": \"refund.successful\", \"mode\": \"production\", \"previous_attributes\": null, \"pending_urls\": [\"hook_4ed7ff31c6974ff180e2463bb2912345\", \"hook_b43bc6d4eb3848bfaaee8e131f712345\"], \"completed_urls\": null, \"id\": \"evt_5786b354576049bb91e845b6d5712345\", \"user_id\": \"user_7b4c654836a743a492e59573a0512345\", \"status\": \"pending\", \"object\": \"Event\"}";
        Event event = EasyPostResource.GSON.fromJson(eventJson, Event.class);
        assertNull(event.getPreviousAttributes());
    }

}
