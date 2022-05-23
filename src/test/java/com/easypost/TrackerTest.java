package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackerTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        _vcr = new TestUtils.VCR("tracker", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a tracker.
     */
    private static Tracker createBasicTracker() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixture.usps());
        params.put("tracking_code", "EZ1000000001");

        // TODO: We shouldn't require end-users to wrap these parameters in a dictionary.
        return Tracker.create(params);
    }

    /**
     * Test creating a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        Tracker tracker = createBasicTracker();

        assertInstanceOf(Tracker.class, tracker);
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
        _vcr.setUpTest("retrieve");

        Tracker tracker = createBasicTracker();

        Tracker retrievedTracker = Tracker.retrieve(tracker.getId());

        assertInstanceOf(Tracker.class, tracker);
        assertTrue(retrievedTracker.getId().startsWith("trk_"));
        assertEquals(tracker.getId(), retrievedTracker.getId());
    }

    /**
     * Test retrieving all trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

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
        _vcr.setUpTest("create_list");

        Map<String, Object> params = new HashMap<>();
        String[] trackingCodes = new String[] { "EZ1000000001", "EZ1000000002", "EZ1000000003" };

        for (int i = 0; i < trackingCodes.length; i++) {
            Map<String, Object> tracker = new HashMap<>();

            tracker.put("tracking_code", trackingCodes[i]);
            params.put(String.valueOf(i), tracker);
        }

        boolean response = Tracker.createList(params);

        // This endpoint returns nothing so we assert the function returns true
        assertTrue(response);
    }
}
