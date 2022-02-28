package com.easypost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.ScanForm;
import com.easypost.model.ScanFormCollection;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ScanFormTest {
    private static ScanForm globalScanForm;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        List<Shipment> shipments = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        shipments.add(shipment);
        params.put("shipments", shipments);

        globalScanForm = ScanForm.create(params);
    }

    /**
     * Test creating a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        List<Shipment> shipments = new ArrayList<>();

        shipments.add(shipment);

        Map<String, Object> params = new HashMap<>();

        params.put("shipments", shipments);

        ScanForm scanForm = ScanForm.create(params);

        assertTrue(scanForm instanceof ScanForm);
        assertTrue(scanForm.getId().startsWith("sf_"));
    }

    /**
     * Test retrieving a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        ScanForm retrievedScanForm = ScanForm.retrieve(globalScanForm.getId());

        assertTrue(retrievedScanForm instanceof ScanForm);
        assertThat(globalScanForm).usingRecursiveComparison().isEqualTo(retrievedScanForm);
    }

    /**
     * Test retrieving all scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        ScanFormCollection scanForms = ScanForm.all(params);

        List<ScanForm> scanFormsList = scanForms.getScanForms();
        assertTrue(scanFormsList.size() <= Fixture.pageSize());
        assertNotNull(scanForms.getHasMore());
        assertTrue(scanFormsList.stream().allMatch(scanForm -> scanForm instanceof ScanForm));
    }
}
