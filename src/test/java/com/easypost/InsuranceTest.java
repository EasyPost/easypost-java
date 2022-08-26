package com.easypost;

import com.easypost.exception.EasyPostException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(100.0, insurance.getAmount(), 0.01);
    }

    /**
     * Create insurance object.
     *
     * @return Insurance object
     */
    private static Insurance createBasicInsurance() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        HashMap<String, Object> params = Fixture.basicInsurance();
        params.put("tracking_code", shipment.getTrackingCode());

        return Insurance.create(params);
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

        Insurance retrievedInsurance = Insurance.retrieve(insurance.getId());

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
        params.put("page_size", Fixture.pageSize());

        InsuranceCollection insuranceCollection = Insurance.all(params);

        List<Insurance> insurances = insuranceCollection.getInsurances();

        assertTrue(insurances.size() <= Fixture.pageSize());
        assertNotNull(insuranceCollection.getHasMore());
        assertTrue(insurances.stream().allMatch(insurance -> insurance instanceof Insurance));
    }
}
