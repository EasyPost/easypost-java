package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class TrackerTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("tracker", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Tracker tracker = createBasicTracker();

        assertInstanceOf(Tracker.class, tracker);
        assertTrue(tracker.getId().startsWith("trk_"));
        assertEquals("pre_transit", tracker.getStatus());
    }

    /**
     * Create a tracker.
     *
     * @return Tracker object
     */
    private static Tracker createBasicTracker() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("carrier", Fixtures.usps());
        params.put("tracking_code", "EZ1000000001");

        return vcr.client.tracker.create(params);
    }

    /**
     * Test retrieving a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Tracker tracker = createBasicTracker();

        Tracker retrievedTracker = vcr.client.tracker.retrieve(tracker.getId());

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
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        TrackerCollection trackers = vcr.client.tracker.all(params);

        List<Tracker> trackersList = trackers.getTrackers();

        assertTrue(trackersList.size() <= Fixtures.pageSize());
        assertNotNull(trackers.getHasMore());
        assertTrue(trackersList.stream().allMatch(tracker -> tracker instanceof Tracker));
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
        TrackerCollection collection = vcr.client.tracker.all(params);

        try {
            TrackerCollection nextPage = vcr.client.tracker.getNextPage(collection);

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getTrackers().get(0).getId();
            String firstIdOfSecondPage = nextPage.getTrackers().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test creating a list of trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateList() throws EasyPostException {
        vcr.setUpTest("create_list");

        Map<String, Object> params = new HashMap<>();
        String[] trackingCodes = new String[] {
                "EZ1000000001",
                "EZ1000000002",
                "EZ1000000003"
        };

        for (int i = 0; i < trackingCodes.length; i++) {
            Map<String, Object> tracker = new HashMap<>();

            tracker.put("tracking_code", trackingCodes[i]);
            params.put(String.valueOf(i), tracker);
        }

        assertDoesNotThrow(() -> vcr.client.tracker.createList(params));
    }
}
