package com.easypost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {
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
     * Test creating a report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReport() throws EasyPostException {
        Map<String, Object> reportParams = new HashMap<>();

        reportParams.put("start_date", Fixture.reportDate());
        reportParams.put("end_date", Fixture.reportDate());
        reportParams.put("type", Fixture.reportType());

        Report report = Report.create(reportParams);

        assertTrue(report instanceof Report);
        assertTrue(report.getId().startsWith("shprep_"));
    }

    /**
     * Test creating a report with additional columns.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReportWithAdditionalColumns() throws EasyPostException {
        Map<String, Object> reportWithAdditionalColumnsParams = new HashMap<>();

        List<String> additionalColumns = new ArrayList<>(Arrays.asList("from_name", "from_company"));

        reportWithAdditionalColumnsParams.put("start_date", Fixture.reportDate());
        reportWithAdditionalColumnsParams.put("end_date", Fixture.reportDate());
        reportWithAdditionalColumnsParams.put("type", Fixture.reportType());
        reportWithAdditionalColumnsParams.put("additional_columns", additionalColumns);

        Report reportWithAdditionalColumns = Report.create(reportWithAdditionalColumnsParams);

        // Reports are queued, so we can't wait for completion. Verifying columns would
        // require parsing CSV.
        assertTrue(reportWithAdditionalColumns instanceof Report);
    }

    /**
     * Test creating a report with columns.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReportWithColumns() throws EasyPostException {
        Map<String, Object> reportWithAdditionalColumnsParams = new HashMap<>();

        List<String> columns = new ArrayList<>(Arrays.asList("usps_zone"));

        reportWithAdditionalColumnsParams.put("start_date", Fixture.reportDate());
        reportWithAdditionalColumnsParams.put("end_date", Fixture.reportDate());
        reportWithAdditionalColumnsParams.put("type", Fixture.reportType());
        reportWithAdditionalColumnsParams.put("columns", columns);

        Report reportWithColumns = Report.create(reportWithAdditionalColumnsParams);

        // Reports are queued, so we can't wait for completion. Verifying columns would
        // require parsing CSV.
        assertTrue(reportWithColumns instanceof Report);
    }

    /**
     * Test retrieving shipment report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveReport() throws EasyPostException {
        Map<String, Object> reportParams = new HashMap<>();

        reportParams.put("start_date", Fixture.reportDate());
        reportParams.put("end_date", Fixture.reportDate());
        reportParams.put("type", Fixture.reportType());

        Report report = Report.create(reportParams);

        Report retrievedReport = Report.retrieve(report.getId());

        assertTrue(retrievedReport instanceof Report);
        assertEquals(report.getStartDate(), retrievedReport.getStartDate());
        assertEquals(report.getEndDate(), retrievedReport.getEndDate());
    }

    /**
     * Test retrieving all reports.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("type", Fixture.reportType());
        params.put("page_size", Fixture.pageSize());

        ReportCollection reports = Report.all(params);

        List<Report> reportsList = reports.getReports();

        assertTrue(reportsList.size() <= Fixture.pageSize());
        assertNotNull(reports.getHasMore());
        assertTrue(reportsList.stream().allMatch(report -> report instanceof Report));
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
