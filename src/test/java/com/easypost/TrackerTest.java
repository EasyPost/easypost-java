package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class TrackerTest {
    private static Map<String, Object> params = new HashMap<>();
    private static Tracker globalTracker;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        params.put("tracking_code", "EZ1000000001");

        globalTracker = Tracker.create(params);
    }

    /**
     * Test creating a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Tracker tracker = Tracker.create(params);

        assertTrue(tracker instanceof Tracker);
        assertTrue(tracker.getId().startsWith("trk_"));
        assertEquals("pre_transit", tracker.getStatus());
    }

    /**
     * Test retrieving a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Tracker retrievedTracker = Tracker.retrieve(globalTracker.getId());

        assertTrue(retrievedTracker instanceof Tracker);
        assertTrue(retrievedTracker.getId().startsWith("trk_"));
        assertEquals(globalTracker.getId(), retrievedTracker.getId());
    }

    /**
     * Test retrieving all trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        TrackerCollection trackers = Tracker.all(params);

        List<Tracker> trackersList = trackers.getTrackers();

        assertTrue(trackersList.size() <= Fixture.pageSize());
        assertNotNull(trackers.getHasMore());
        assertTrue(trackersList.stream().allMatch(tracker -> tracker instanceof Tracker));
    }

    /**
     * Test creating a list of trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateList() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        String[] trackingCodes = new String[] { "EZ1000000001", "EZ1000000002", "EZ1000000003" };

        for (int i = 0; i < trackingCodes.length; i++) {
            Map<String, Object> tracker = new HashMap<>();

            tracker.put("tracking_code", trackingCodes[i]);
            params.put(String.valueOf(i), tracker);
        }

        Boolean response = Tracker.createList(params);

        assertTrue(response);
    }
}
