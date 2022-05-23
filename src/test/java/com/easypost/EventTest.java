package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.model.EventCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        _vcr = new TestUtils.VCR("event", TestUtils.ApiKey.TEST);
    }

    /**
     * Get an event collection.
     */
    private static EventCollection getBasicEventCollection() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());
        return Event.all(params);
    }

    /**
     * Test retrieving all events.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

        EventCollection events = getBasicEventCollection();

        List<Event> eventsList = events.getEvents();

        assertTrue(eventsList.size() <= Fixture.pageSize());
        assertNotNull(events.getHasMore());
        assertTrue(eventsList.stream().allMatch(event -> event instanceof Event));
    }

    /**
     * Test retrieving an event.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        _vcr.setUpTest("retrieve");

        EventCollection eventCollection = getBasicEventCollection();
        List<Event> events = eventCollection.getEvents();
        Event event = events.get(0);

        Event retrievedEvent = Event.retrieve(event.getId());

        assertInstanceOf(Event.class, retrievedEvent);
        // Must compare IDs because other elements of objects may be different
        assertEquals(event.getId(), retrievedEvent.getId());
    }
}
