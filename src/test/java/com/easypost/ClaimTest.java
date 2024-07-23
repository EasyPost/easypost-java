package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Claim;
import com.easypost.model.ClaimCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class ClaimTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("claim", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a insured shipment object.
     * @param amount the amount to insure the shipment.
     *
     * @return Shipment object
     */
    private static Shipment createInsuredShipment(String amount) throws EasyPostException {
        Shipment shipment = vcr.client.shipment.create(Fixtures.fullShipment());

        HashMap<String, Object> params = new HashMap<>();
        params.put("rate", shipment.lowestRate());
        params.put("insurance", amount);

        return vcr.client.shipment.buy(shipment.getId(), params);
    }

    /**
     * Test creating a claim object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");
        String amount = "100";
        Shipment shipment = ClaimTest.createInsuredShipment(amount);
        HashMap<String, Object> claimData = Fixtures.basicClaim();
        claimData.put("amount", amount);
        claimData.put("tracking_code", shipment.getTrackingCode());
        Claim claim = vcr.client.claim.create(claimData);

        assertInstanceOf(Claim.class, claim);
        assertTrue(claim.getId().startsWith("clm_"));
    }

    /**
     * Test retrieving an claim object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        String amount = "100";
        Shipment shipment = ClaimTest.createInsuredShipment(amount);
        HashMap<String, Object> claimData = Fixtures.basicClaim();
        claimData.put("amount", amount);
        claimData.put("tracking_code", shipment.getTrackingCode());
        Claim claim = vcr.client.claim.create(claimData);

        Claim retrievedClaim = vcr.client.claim.retrieve(claim.getId());

        assertInstanceOf(Claim.class, retrievedClaim);
        assertEquals(claim.getId(), retrievedClaim.getId());
    }

    /**
     * Test retrieving all claim objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        ClaimCollection claimCollection = vcr.client.claim.all(params);

        List<Claim> claims = claimCollection.getClaims();

        assertTrue(claims.size() <= Fixtures.pageSize());
        assertNotNull(claimCollection.getHasMore());
        assertTrue(claims.stream().allMatch(claim -> claim != null));
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
        ClaimCollection collection = vcr.client.claim.all(params);

        try {
            ClaimCollection nextPage = vcr.client.claim.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getClaims().get(0).getId();
            String firstIdOfSecondPage = nextPage.getClaims().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test cancelling a filed claim.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCancelClaim() throws EasyPostException {
        vcr.setUpTest("cancel");

        String amount = "100";
        Shipment shipment = ClaimTest.createInsuredShipment(amount);
        HashMap<String, Object> claimData = Fixtures.basicClaim();
        claimData.put("amount", amount);
        claimData.put("tracking_code", shipment.getTrackingCode());
        Claim claim = vcr.client.claim.create(claimData);
        Claim cancelledClaim = vcr.client.claim.cancel(claim.getId());

        assertInstanceOf(Claim.class, cancelledClaim);
        assertTrue(cancelledClaim.getId().startsWith("clm_"));
        assertEquals("cancelled", cancelledClaim.getStatus());
    }
}
