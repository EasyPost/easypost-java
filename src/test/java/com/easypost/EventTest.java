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

public final class EventTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("event", TestUtils.ApiKey.TEST);
    }

    /**
     * Test retrieving all events.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        EventCollection events = getBasicEventCollection();

        List<Event> eventsList = events.getEvents();

        assertTrue(eventsList.size() <= Fixtures.pageSize());
        assertNotNull(events.getHasMore());
        assertTrue(eventsList.stream().allMatch(event -> event instanceof Event));
    }

    /**
     * Get an event collection.
     *
     * @return EventCollection object.
     */
    private static EventCollection getBasicEventCollection() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        return Event.all(params);
    }

    /**
     * Test retrieving an event.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        EventCollection eventCollection = getBasicEventCollection();
        List<Event> events = eventCollection.getEvents();
        Event event = events.get(0);

        Event retrievedEvent = Event.retrieve(event.getId());

        assertInstanceOf(Event.class, retrievedEvent);
        // Must compare IDs since can't do whole-object comparisons currently
        assertEquals(event.getId(), retrievedEvent.getId());
    }
}
