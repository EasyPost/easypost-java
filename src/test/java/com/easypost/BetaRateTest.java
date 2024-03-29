package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.StatelessRate;
import com.easypost.utils.Utilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BetaRateTest {
    private static TestUtils.VCR vcr;

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
        vcr.setUpTest("retrieve_stateless_rates");

        HashMap<String, Object> shipment = Fixtures.basicShipment();

        List<StatelessRate> rates = vcr.client.betaRate.retrieveStatelessRates(shipment);

        assertTrue(rates.stream().allMatch(rate -> rate != null));
    }

    /**
     * Test retrieving the lowest rate of stateless rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveLowestStatelessRate() throws EasyPostException {
        vcr.setUpTest("retrieve_lowest_stateless_rate");

        HashMap<String, Object> shipment = Fixtures.basicShipment();

        List<StatelessRate> rates = vcr.client.betaRate.retrieveStatelessRates(shipment);
        StatelessRate lowestRate = Utilities.getLowestStatelessRate(rates, null, null);

        assertEquals("GroundAdvantage", lowestRate.getService());

        List<String> carriers = Arrays.asList("invalidCarrierName");
        EasyPostException exception =
                assertThrows(EasyPostException.class, () -> Utilities.getLowestStatelessRate(rates, carriers, null));

        assertEquals("No rates found.", exception.getMessage());
    }
}
