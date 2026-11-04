package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;
import com.google.common.collect.ImmutableList;
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
        assertTrue(trackersList.stream().allMatch(tracker -> tracker != null));
    }

    /**
     * Test the parameter handoff when retrieving all trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllParameterHandOff() throws EasyPostException {
        vcr.setUpTest("all_parameter_hand_off");

        String trackingCode = "something";
        String carrier = "something else";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page_size", Fixtures.pageSize());

        params.put("tracking_code", trackingCode);
        params.put("carrier", carrier);

        TrackerCollection trackerCollection = vcr.client.tracker.all(params);

        assertEquals(trackingCode, trackerCollection.getTrackingCode());
        assertEquals(carrier, trackerCollection.getCarrier());
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
            TrackerCollection nextPage = vcr.client.tracker.getNextPage(collection, Fixtures.pageSize());

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
     * Test the parameter handoff when constructing the next page parameter map.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPageParameterHandOff() throws EasyPostException {
        vcr.setUpTest("get_next_page_parameter_hand_off");

        String trackingCode = "something";
        String carrier = "something else";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page_size", Fixtures.pageSize());

        params.put("tracking_code", trackingCode);
        params.put("carrier", carrier);

        TrackerCollection trackerCollection = vcr.client.tracker.all(params);

        // Can't access protected method directly, need to make a temporary extended class, yay
        // Downside, TrackerCollection and Tracker are no longer final because they need to be extended
        final class ExtendedTrackerCollection extends TrackerCollection {

            ExtendedTrackerCollection(TrackerCollection trackerCollection) {
                setTrackingCode(trackerCollection.getTrackingCode());
                setCarrier(trackerCollection.getCarrier());
            }

            @Override
            public List<Tracker> getTrackers() {

                final class ExtendedTracker extends Tracker {
                    @Override
                    public String getId() {
                        return "trk_123";
                    }
                }

                return new ArrayList<Tracker>(ImmutableList.of(new ExtendedTracker()));
            }

            public Map<String, Object> getNextPageParams() throws EndOfPaginationError {
                return super.buildNextPageParameters(getTrackers(), null);
            }
        }

        ExtendedTrackerCollection extendedShipmentCollection = new ExtendedTrackerCollection(trackerCollection);
        Map<String, Object> nextPageParams = extendedShipmentCollection.getNextPageParams();

        assertEquals(trackingCode, nextPageParams.get("tracking_code"));
        assertEquals(carrier, nextPageParams.get("carrier"));
    }

    /**
     * Test retrieving a batch of trackers.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveBatch() throws EasyPostException {
        vcr.setUpTest("retrieve_batch");

        Tracker tracker = createBasicTracker();

        Map<String, Object> params = new HashMap<>();
        params.put("tracking_codes", ImmutableList.of(tracker.getTrackingCode()));

        TrackerCollection trackers = vcr.client.tracker.retrieveBatch(params);

        List<Tracker> trackersList = trackers.getTrackers();

        assertTrue(trackersList.stream().allMatch(singleTracker -> singleTracker != null));
    }

    /**
     * Test deleting a tracker.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");

        Tracker tracker = createBasicTracker();

        // Nothing gets returned here, simply ensure no error gets raised
        vcr.client.tracker.delete(tracker.getId());
    }
}
