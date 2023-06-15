package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.mocking.MockRequest;
import com.easypost.mocking.MockRequestMatchRules;
import com.easypost.mocking.MockResponse;
import com.easypost.mocking.classes.MockRate;
import com.easypost.mocking.classes.MockStatelessRateResponse;
import com.easypost.model.StatelessRate;
import com.easypost.utils.Utilities;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BetaRateTest {
    private static TestUtils.VCR vcr;

    private static final MockStatelessRateResponse MOCK_STATELESS_RATE_RESPONSE = new MockStatelessRateResponse(
            ImmutableList.of(
                    new MockRate("6.07", "USPS", "First"),
                    new MockRate("31.25", "USPS", "Express"),
                    new MockRate("7.75", "USPS", "ParcelSelect"),
                    new MockRate("7.15", "USPS", "Priority")
            )
    );

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("beta_stateless_rate", TestUtils.ApiKey.TEST);
    }

    /**
     * Test retrieving stateless rates.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveStatelessRates() throws EasyPostException {
        List<MockRequest> mockRequests = new ArrayList<>();
        mockRequests.add(new MockRequest(new MockRequestMatchRules(Requestor.RequestMethod.POST, ".*\\/rates.*"),
                new MockResponse(200, MOCK_STATELESS_RATE_RESPONSE)));

        vcr.setUpTest("retrieve_stateless_rates", mockRequests);

        HashMap<String, Object> shipment = Fixtures.basicShipment();

        List<StatelessRate> rates = vcr.client.betaRate.retrieveStatelessRates(shipment);

        assertTrue(rates.stream().allMatch(rate -> rate instanceof StatelessRate));
    }

    /**
     * Test retrieving the lowest rate of stateless rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveLowestStatelessRate() throws EasyPostException {
        List<MockRequest> mockRequests = new ArrayList<>();
        mockRequests.add(new MockRequest(new MockRequestMatchRules(Requestor.RequestMethod.POST, ".*\\/rates.*"),
                new MockResponse(200, MOCK_STATELESS_RATE_RESPONSE)));

        vcr.setUpTest("retrieve_lowest_stateless_rate", mockRequests);

        HashMap<String, Object> shipment = Fixtures.basicShipment();

        List<StatelessRate> rates = vcr.client.betaRate.retrieveStatelessRates(shipment);
        StatelessRate lowestRate = Utilities.getLowestStatelessRate(rates, null, null);

        assertEquals("First", lowestRate.getService());

        List<String> carriers = Arrays.asList("invalidCarrierName");
        EasyPostException exception =
                assertThrows(EasyPostException.class, () -> Utilities.getLowestStatelessRate(rates, carriers, null));

        assertEquals("No rates found.", exception.getMessage());
    }
}
