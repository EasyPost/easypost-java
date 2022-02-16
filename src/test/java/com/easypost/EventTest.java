package com.easypost;

import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.model.EventCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private static EventCollection globalEvents;
    private static Map<String, Object> params = new HashMap<>();

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        params.put("page_size", Fixture.pageSize());

        globalEvents = Event.all(params);
    }

    /**
     * Test retrieving all events.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        EventCollection events = Event.all(params);

        assertTrue(events.getEvents().size() <= Fixture.pageSize());
        assertNotNull(events.getHasMore());
        for(Event event: events.getEvents()) {
            assertTrue(event instanceof Event);
        }
    }

    /**
     * Test retrieving an event.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Event retrievedEvent = Event.retrieve(globalEvents.getEvents().get(0).getId());

        assertTrue(retrievedEvent instanceof Event);
        assertTrue(retrievedEvent.getId().startsWith("evt_"));
        assertEquals(globalEvents.getEvents().get(0).getId(), retrievedEvent.getId());
    }
}
