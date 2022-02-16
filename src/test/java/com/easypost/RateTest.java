package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;
import com.easypost.model.Rate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class RateTest {

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");
    }

    /**
     * Test retrieving a rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.basicShipment());
        Rate rate = Rate.retrieve(shipment.getRates().get(0).getId());

        assertTrue(rate instanceof Rate);
        assertTrue(rate.getId().startsWith("rate_"));
    }
}
