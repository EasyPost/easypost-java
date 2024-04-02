package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Insurance;
import com.easypost.model.InsuranceCollection;
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

public final class InsuranceTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("insurance", TestUtils.ApiKey.TEST);
    }

    /**
     * Create insurance object.
     *
     * @return Insurance object
     */
    private static Insurance createBasicInsurance() throws EasyPostException {
        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment());

        HashMap<String, Object> params = Fixtures.basicInsurance();
        params.put("tracking_code", shipment.getTrackingCode());

        return vcr.client.insurance.create(params);
    }

    /**
     * Test creating an insurance object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Insurance insurance = createBasicInsurance();

        assertInstanceOf(Insurance.class, insurance);
        assertTrue(insurance.getId().startsWith("ins_"));
        assertEquals("100.00000", insurance.getAmount());
    }

    /**
     * Test retrieving an insurance object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Insurance insurance = createBasicInsurance();

        Insurance retrievedInsurance = vcr.client.insurance.retrieve(insurance.getId());

        assertInstanceOf(Insurance.class, insurance);
        // Must compare IDs since can't do whole-object comparisons currently
        assertEquals(insurance.getId(), retrievedInsurance.getId());
    }

    /**
     * Test retrieving all insurance objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        InsuranceCollection insuranceCollection = vcr.client.insurance.all(params);

        List<Insurance> insurances = insuranceCollection.getInsurances();

        assertTrue(insurances.size() <= Fixtures.pageSize());
        assertNotNull(insuranceCollection.getHasMore());
        assertTrue(insurances.stream().allMatch(insurance -> insurance != null));
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
        InsuranceCollection collection = vcr.client.insurance.all(params);

        try {
            InsuranceCollection nextPage = vcr.client.insurance.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getInsurances().get(0).getId();
            String firstIdOfSecondPage = nextPage.getInsurances().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test refunding an insurance.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRefundInsurance() throws EasyPostException {
        vcr.setUpTest("refund");

        HashMap<String, Object> params = Fixtures.basicInsurance();
        params.put("tracking_code", "EZ1000000001");
        Insurance insurance = vcr.client.insurance.create(params);
        Insurance cancelledInsurance = vcr.client.insurance.refund(insurance.getId());

        assertInstanceOf(Insurance.class, cancelledInsurance);
        assertTrue(insurance.getId().startsWith("ins_"));
        assertEquals("cancelled", cancelledInsurance.getStatus());
    }
}
