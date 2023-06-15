package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.mocking.MockRequest;
import com.easypost.mocking.MockRequestMatchRules;
import com.easypost.mocking.MockResponse;
import com.easypost.model.StatelessRate;
import com.easypost.utils.Utilities;
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

    private final String retrieveRatesResponseJson = "{\n" + "  \"rates\": [\n" + "    {\n" +
            "      \"carrier_account_id\": \"ca_f09befdb2e9c410e95c7622ea912c18c\",\n" +
            "      \"list_rate\": \"7.75\",\n" + "      \"delivery_days\": 5.0,\n" +
            "      \"list_currency\": \"USD\",\n" + "      \"mode\": \"test\",\n" + "      \"carrier\": \"USPS\",\n" +
            "      \"delivery_date\": null,\n" + "      \"delivery_date_guaranteed\": false,\n" +
            "      \"retail_rate\": \"7.75\",\n" + "      \"retail_currency\": \"USD\",\n" +
            "      \"rate\": \"6.76\",\n" + "      \"service\": \"ParcelSelect\",\n" +
            "      \"billing_type\": \"easypost\",\n" + "      \"est_delivery_days\": 5.0,\n" +
            "      \"currency\": \"USD\",\n" + "      \"object\": \"Rate\"\n" + "    },\n" + "    {\n" +
            "      \"carrier_account_id\": \"ca_f09befdb2e9c410e95c7622ea912c18c\",\n" +
            "      \"list_rate\": \"6.07\",\n" + "      \"delivery_days\": 3.0,\n" +
            "      \"list_currency\": \"USD\",\n" + "      \"mode\": \"test\",\n" + "      \"carrier\": \"USPS\",\n" +
            "      \"delivery_date\": null,\n" + "      \"delivery_date_guaranteed\": false,\n" +
            "      \"retail_rate\": \"6.07\",\n" + "      \"retail_currency\": \"USD\",\n" +
            "      \"rate\": \"6.07\",\n" + "      \"service\": \"First\",\n" +
            "      \"billing_type\": \"easypost\",\n" + "      \"est_delivery_days\": 3.0,\n" +
            "      \"currency\": \"USD\",\n" + "      \"object\": \"Rate\"\n" + "    },\n" + "    {\n" +
            "      \"carrier_account_id\": \"ca_f09befdb2e9c410e95c7622ea912c18c\",\n" +
            "      \"list_rate\": \"31.25\",\n" + "      \"delivery_days\": null,\n" +
            "      \"list_currency\": \"USD\",\n" + "      \"mode\": \"test\",\n" + "      \"carrier\": \"USPS\",\n" +
            "      \"delivery_date\": null,\n" + "      \"delivery_date_guaranteed\": false,\n" +
            "      \"retail_rate\": \"35.80\",\n" + "      \"retail_currency\": \"USD\",\n" +
            "      \"rate\": \"31.25\",\n" + "      \"service\": \"Express\",\n" +
            "      \"billing_type\": \"easypost\",\n" + "      \"est_delivery_days\": null,\n" +
            "      \"currency\": \"USD\",\n" + "      \"object\": \"Rate\"\n" + "    },\n" + "    {\n" +
            "      \"carrier_account_id\": \"ca_f09befdb2e9c410e95c7622ea912c18c\",\n" +
            "      \"list_rate\": \"8.24\",\n" + "      \"delivery_days\": 2.0,\n" +
            "      \"list_currency\": \"USD\",\n" + "      \"mode\": \"test\",\n" + "      \"carrier\": \"USPS\",\n" +
            "      \"delivery_date\": null,\n" + "      \"delivery_date_guaranteed\": false,\n" +
            "      \"retail_rate\": \"10.20\",\n" + "      \"retail_currency\": \"USD\",\n" +
            "      \"rate\": \"7.15\",\n" + "      \"service\": \"Priority\",\n" +
            "      \"billing_type\": \"easypost\",\n" + "      \"est_delivery_days\": 2.0,\n" +
            "      \"currency\": \"USD\",\n" + "      \"object\": \"Rate\"\n" + "    }\n" + "  ],\n" + "}";

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
                new MockResponse(200, retrieveRatesResponseJson)));

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
                new MockResponse(200, retrieveRatesResponseJson)));

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
