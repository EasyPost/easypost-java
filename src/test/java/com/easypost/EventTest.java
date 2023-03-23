package com.easypost;

import com.easypost.exception.APIException;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.AddressCollection;
import com.easypost.model.Event;
import com.easypost.model.EventCollection;
import com.easypost.model.InsuranceCollection;
import com.easypost.model.Payload;
import com.easypost.model.Webhook;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
     * Test retrieving the next page.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        EventCollection collection = vcr.client.event.all(params);

        try {
            EventCollection nextPage = vcr.client.event.getNextPage(collection);

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getEvents().get(0).getId();
            String firstIdOfSecondPage = nextPage.getEvents().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Get an event collection.
     *
     * @return EventCollection object.
     */
    private static EventCollection getBasicEventCollection() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        return vcr.client.event.all(params);
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

        Event retrievedEvent = vcr.client.event.retrieve(event.getId());

        assertInstanceOf(Event.class, retrievedEvent);
        // Must compare IDs since can't do whole-object comparisons currently
        assertEquals(event.getId(), retrievedEvent.getId());
    }

    /**
     * Test retrieving all payloads.
     *
     * @throws EasyPostException when the request fails.
     * @throws InterruptedException when vcr recording fails.
     */
    @Test
    public void testEventRetrieveAllPayloads() throws EasyPostException, InterruptedException {
        vcr.setUpTest("retrieve_all_payloads");

        Map<String, Object> params = new HashMap<>();
        params.put("url", Fixtures.webhookUrl());
        Webhook webhook = vcr.client.webhook.create(params);

        List<Object> shipments = new ArrayList<>();
        shipments.add(Fixtures.basicShipment());

        Map<String, Object> batchParams = new HashMap<>();
        batchParams.put("shipments", shipments);
        vcr.client.batch.create(batchParams);

        if (vcr.isRecording()) {
            Thread.sleep(5000); // Wait enough time for the batch to process before buying the shipment
        }

        EventCollection events = getBasicEventCollection();

        List<Event> eventsList = events.getEvents();
        List<Payload> payloads = vcr.client.event.retrieveAllPayloads(eventsList.get(0).getId());
        assertTrue(payloads.stream().allMatch(payload -> payload instanceof Payload));
        vcr.client.webhook.delete(webhook.getId());
    }

    /**
     * Test retrieving a payload.
     *
     * @throws EasyPostException when the request fails.
     * @throws InterruptedException when vcr recording fails.
     */
    @Test
    public void testEventRetrievePayload() throws EasyPostException, InterruptedException {
        vcr.setUpTest("retrieve_payload");

        Map<String, Object> params = new HashMap<>();
        params.put("url", Fixtures.webhookUrl());
        Webhook webhook = vcr.client.webhook.create(params);

        List<Object> shipments = new ArrayList<>();
        shipments.add(Fixtures.basicShipment());

        Map<String, Object> batchParams = new HashMap<>();
        batchParams.put("shipments", shipments);
        vcr.client.batch.create(batchParams);

        if (vcr.isRecording()) {
            Thread.sleep(5000); // Wait enough time for the batch to process before buying the shipment
        }

        EventCollection events = getBasicEventCollection();

        APIException exception = assertThrows(APIException.class,
                () -> vcr.client.event.retrievePayload(events.getEvents().get(0).getId(),
                        "payload_11111111111111111111111111111111")); // Need a valid-length, invalid payload ID here
        
        assertEquals(404, exception.getStatusCode());

        vcr.client.webhook.delete(webhook.getId());
    }
}
