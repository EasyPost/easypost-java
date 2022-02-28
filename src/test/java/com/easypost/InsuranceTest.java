package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Insurance;
import com.easypost.model.InsuranceCollection;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class InsuranceTest {
    private static Insurance globalInsurance;
    private static Map<String, Object> params = new HashMap<>();

    /** 
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */    
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        params.put("to_address", Fixture.basicAddress());
        params.put("from_address", Fixture.basicAddress());
        params.put("tracking_code", shipment.getTrackingCode());
        params.put("carrier", Fixture.usps());
        params.put("amount", "100");

        globalInsurance = Insurance.create(params);
    }

    /**
     * Test creating an insurance object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Insurance insurance = Insurance.create(params);

        assertTrue(insurance instanceof Insurance);
        assertTrue(insurance.getId().startsWith("ins_"));
        assertEquals(100.0, insurance.getAmount(), 0.01);
    }

    /**
     * Test retrieving an insurance object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Insurance retrievedInsurance = Insurance.retrieve(globalInsurance.getId());

        assertTrue(retrievedInsurance instanceof Insurance);
        assertTrue(retrievedInsurance.getId().startsWith("ins_"));
        assertEquals(globalInsurance.getId(), retrievedInsurance.getId());
    }

    /**
     * Test retrieving all insurance objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        InsuranceCollection insurances = Insurance.all(params);

        List<Insurance> insurancesList = insurances.getInsurances();

        assertTrue(insurancesList.size() <= Fixture.pageSize());
        assertNotNull(insurances.getHasMore());
        assertTrue(insurancesList.stream().allMatch(insurance -> insurance instanceof Insurance));
    }
}
