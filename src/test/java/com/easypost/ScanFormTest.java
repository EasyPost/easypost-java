package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.ScanForm;
import com.easypost.model.ScanFormCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class ScanFormTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("scan_form", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        ScanForm scanForm = getBasicScanForm();

        assertInstanceOf(ScanForm.class, scanForm);
        assertTrue(scanForm.getId().startsWith("sf_"));
    }

    /**
     * Create a ScanForm.
     *
     * @return ScanForm object
     */
    private static ScanForm getBasicScanForm() throws EasyPostException {
        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment());

        List<Shipment> shipments = new ArrayList<>();
        shipments.add(shipment);

        Map<String, Object> params = new HashMap<>();
        params.put("shipments", shipments);

        ScanForm scanForm = vcr.client.scanForm.create(params);
        return scanForm;
    }

    /**
     * Test retrieving a scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        ScanForm scanForm = getBasicScanForm();

        ScanForm retrievedScanForm = vcr.client.scanForm.retrieve(scanForm.getId());

        assertInstanceOf(ScanForm.class, retrievedScanForm);
        assertTrue(scanForm.equals(retrievedScanForm));
    }

    /**
     * Test retrieving all scan form.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        ScanFormCollection scanForms = vcr.client.scanForm.all(params);
        List<ScanForm> scanFormsList = scanForms.getScanForms();

        assertTrue(scanFormsList.size() <= Fixtures.pageSize());
        assertNotNull(scanForms.getHasMore());
        assertTrue(scanFormsList.stream().allMatch(scanForm -> scanForm instanceof ScanForm));
    }

    /**
     * Test retrieving the next page.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        ScanFormCollection collection = vcr.client.scanForm.all(params);

        try {
            ScanFormCollection nextPage = vcr.client.scanForm.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getScanForms().get(0).getId();
            String firstIdOfSecondPage = nextPage.getScanForms().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }
}
