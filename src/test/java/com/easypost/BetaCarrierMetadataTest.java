package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CarrierMetadata;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BetaCarrierMetadataTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("beta_carrier_metadata", TestUtils.ApiKey.TEST);
    }

    /**
     * Test retrieving all carrier metadata.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveStatelessRates() throws EasyPostException {
        vcr.setUpTest("retrieve_carrier_metadata");

        CarrierMetadata carrierMetadata = vcr.client.betaCarrierMetadata.retrieveCarrierMetadata();

        assertTrue(carrierMetadata.getCarriers().stream().anyMatch(carrier -> carrier.getName().equals("usps")));
        assertTrue(carrierMetadata.getCarriers().stream().anyMatch(carrier -> carrier.getName().equals("fedex")));
    }

    /**
     * Test retrieving carrier metadata for a specific carrier.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveStatelessRatesWithFilter() throws EasyPostException {
        vcr.setUpTest("retrieve_carrier_metadata_with_filter");
        List<String> carriers = Arrays.asList("usps");
        List<String> types = Arrays.asList("service_levels", "predefined_packages");
        CarrierMetadata carrierMetadata = vcr.client.betaCarrierMetadata.retrieveCarrierMetadata(carriers, types);

        assertTrue(carrierMetadata.getCarriers().stream()
                .allMatch(carrier -> carrier.getName().equals("usps")));
        assertEquals(1, carrierMetadata.getCarriers().size());
        assertNotNull(carrierMetadata.getCarriers().get(0).getServiceLevels());
        assertNotNull(carrierMetadata.getCarriers().get(0).getPredefinedPackages());
        assertNull(carrierMetadata.getCarriers().get(0).getSupportedFeatures());
    }
}
