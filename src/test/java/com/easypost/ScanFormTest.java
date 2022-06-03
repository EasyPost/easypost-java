package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.ScanForm;
import com.easypost.model.ScanFormCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScanFormTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        _vcr = new TestUtils.VCR("scan_form", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a ScanForm.
     */
    private static ScanForm getBasicScanForm() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        List<Shipment> shipments = new ArrayList<>();
        shipments.add(shipment);

        Map<String, Object> params = new HashMap<>();
        params.put("shipments", shipments);

        ScanForm scanForm = ScanForm.create(params);
        return scanForm;
    }

    /**
     * Test creating a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        ScanForm scanForm = getBasicScanForm();

        assertInstanceOf(ScanForm.class, scanForm);
        assertTrue(scanForm.getId().startsWith("sf_"));
    }

    /**
     * Test retrieving a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        _vcr.setUpTest("retrieve");

        ScanForm scanForm = getBasicScanForm();

        ScanForm retrievedScanForm = ScanForm.retrieve(scanForm.getId());

        assertInstanceOf(ScanForm.class, retrievedScanForm);
        assertThat(scanForm).usingRecursiveComparison().isEqualTo(retrievedScanForm);
    }

    /**
     * Test retrieving all scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());

        ScanFormCollection scanForms = ScanForm.all(params);
        List<ScanForm> scanFormsList = scanForms.getScanForms();

        assertTrue(scanFormsList.size() <= Fixture.pageSize());
        assertNotNull(scanForms.getHasMore());
        assertTrue(scanFormsList.stream().allMatch(scanForm -> scanForm instanceof ScanForm));
    }
}
