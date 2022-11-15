package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class RateTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("rate", TestUtils.ApiKey.TEST);
    }

    /**
     * Test retrieving a rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Shipment shipment = vcr.client.shipment.create(Fixtures.basicShipment());
        Rate rate = shipment.getRates().get(0);

        Rate retrievedRate = vcr.client.rate.retrieve(rate.getId());

        assertInstanceOf(Rate.class, rate);
        assertTrue(rate.equals(retrievedRate));
    }
}
