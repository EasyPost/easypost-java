package com.easypost;

import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {
    private static Map<String, Object> paymentLogReportParams = new HashMap<>();
    private static Map<String, Object> refundParams = new HashMap<>();
    private static Map<String, Object> shipmentParams = new HashMap<>();
    private static Map<String, Object> shipmentInvoiceParams = new HashMap<>();
    private static Map<String, Object> trackerParams = new HashMap<>();
    private static Report globalPaymentLogReport;
    private static Report globalRefundReport;
    private static Report globalShipmentReport;
    private static Report globalShipmentInvoiceReport;
    private static Report globalTrackerReport;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        paymentLogReportParams.put("start_date", Fixture.reportStartDate());
        paymentLogReportParams.put("end_date", Fixture.reportEndDate());
        paymentLogReportParams.put("type", "payment_log");

        refundParams.put("start_date", Fixture.reportStartDate());
        refundParams.put("end_date", Fixture.reportEndDate());
        refundParams.put("type", "refund");

        shipmentParams.put("start_date", Fixture.reportStartDate());
        shipmentParams.put("end_date", Fixture.reportEndDate());
        shipmentParams.put("type", "shipment");

        shipmentInvoiceParams.put("start_date", Fixture.reportStartDate());
        shipmentInvoiceParams.put("end_date", Fixture.reportEndDate());
        shipmentInvoiceParams.put("type", "shipment_invoice");

        trackerParams.put("start_date", Fixture.reportStartDate());
        trackerParams.put("end_date", Fixture.reportEndDate());
        trackerParams.put("type", "tracker");

        globalPaymentLogReport = Report.create(paymentLogReportParams);

        globalRefundReport = Report.create(refundParams);

        globalShipmentReport = Report.create(shipmentParams);

        globalShipmentInvoiceReport = Report.create(shipmentInvoiceParams);

        globalTrackerReport = Report.create(trackerParams);
    }

    /**
     * Test creating a Payment Log report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndRetrievePaymentLogReport() throws EasyPostException {
        Report paymentLogReport = Report.create(paymentLogReportParams);

        assertNotNull(paymentLogReport);
        assertTrue(paymentLogReport instanceof Report);
        assertTrue(paymentLogReport.getId().startsWith("plrep_"));
    }

    /**
     * Test creating a Refund report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateRefundReport() throws EasyPostException {
        Report refundReport = Report.create(refundParams);

        assertNotNull(refundReport);
        assertTrue(refundReport instanceof Report);
        assertTrue(refundReport.getId().startsWith("refrep_"));
    }

    /**
     * Test creating a Shipment report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateShipmentReport() throws EasyPostException {
        Report shipmentReport = Report.create(shipmentParams);

        assertNotNull(shipmentReport);
        assertTrue(shipmentReport instanceof Report);
        assertTrue(shipmentReport.getId().startsWith("shprep_"));
    }

    /**
     * Test creating a Shipment Invoice report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateShipmentInvoiceReport() throws EasyPostException {
        Report shipmentInvoiceReport = Report.create(shipmentInvoiceParams);

        assertNotNull(shipmentInvoiceReport);
        assertTrue(shipmentInvoiceReport instanceof Report);
        assertTrue(shipmentInvoiceReport.getId().startsWith("shpinvrep_"));
    }

    /**
     * Test creating a Tracker report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateTrackerReport() throws EasyPostException {
        Report trackerReport = Report.create(trackerParams);

        assertNotNull(trackerReport);
        assertTrue(trackerReport instanceof Report);
        assertTrue(trackerReport.getId().startsWith("trkrep_"));
    }

    /**
     * Test retrieving payment log report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrievePaymentLogReport() throws EasyPostException {
        Report retrievePaymentLogReport = Report.retrieve(globalPaymentLogReport.getId());

        assertTrue(retrievePaymentLogReport instanceof Report);
        assertEquals(globalPaymentLogReport.getStartDate(), retrievePaymentLogReport.getStartDate());
        assertEquals(globalPaymentLogReport.getEndDate(), retrievePaymentLogReport.getEndDate());
    }

    /**
     * Test retrieving refund report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveRefundReport() throws EasyPostException {
        Report retrieveRefundReport = Report.retrieve(globalRefundReport.getId());

        assertTrue(retrieveRefundReport instanceof Report);
        assertEquals(globalRefundReport.getStartDate(), retrieveRefundReport.getStartDate());
        assertEquals(globalRefundReport.getEndDate(), retrieveRefundReport.getEndDate());
    }

    /**
     * Test retrieving shipment report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveShipmentReport() throws EasyPostException {
        Report retrieveShipmentReport = Report.retrieve(globalShipmentReport.getId());

        assertTrue(retrieveShipmentReport instanceof Report);
        assertEquals(globalShipmentReport.getStartDate(), retrieveShipmentReport.getStartDate());
        assertEquals(globalShipmentReport.getEndDate(), retrieveShipmentReport.getEndDate());
    }

    /**
     * Test retrieving shipment invoice report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveShipmentInvoiceReport() throws EasyPostException {
        Report retrieveShipmentInvoiceReport = Report.retrieve(globalShipmentInvoiceReport.getId());

        assertTrue(retrieveShipmentInvoiceReport instanceof Report);
        assertEquals(globalShipmentInvoiceReport.getStartDate(), retrieveShipmentInvoiceReport.getStartDate());
        assertEquals(globalShipmentInvoiceReport.getEndDate(), retrieveShipmentInvoiceReport.getEndDate());
    }

    /**
     * Test retrieving shipment invoice report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveTrackerReport() throws EasyPostException {
        Report retrieveTrackerReport = Report.retrieve(globalTrackerReport.getId());

        assertTrue(retrieveTrackerReport instanceof Report);
        assertEquals(globalTrackerReport.getStartDate(), retrieveTrackerReport.getStartDate());
        assertEquals(globalTrackerReport.getEndDate(), retrieveTrackerReport.getEndDate());
    }

    /**
     * Test retrieving all reports.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("type", "shipment");
        params.put("page_size", Fixture.pageSize());

        ReportCollection reports = Report.all(params);

        assertTrue(reports.getReports().size() <= Fixture.pageSize());
        assertNotNull(reports.getHasMore());
        for(Report report: reports.getReports()) {
            assertTrue(report instanceof Report);
        }
    }

    /**
     * Test throwing an error when creating a report with no report type set.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateNoType() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("type", "test");

        assertThrows(EasyPostException.class, () -> Report.create(params));
    }

    /**
     * Test throwing an error when retrieving all reports with no report type set.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllNoType() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("type", "test");

        assertThrows(EasyPostException.class, () -> Report.all(params));
    }
}
