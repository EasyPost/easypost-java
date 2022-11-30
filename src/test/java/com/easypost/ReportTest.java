package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ReportTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("report", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a report.
     *
     * @return Report object
     */
    private static Report createBasicReport() throws EasyPostException {
        Map<String, Object> reportParams = new HashMap<>();

        reportParams.put("start_date", Fixtures.reportDate());
        reportParams.put("end_date", Fixtures.reportDate());
        reportParams.put("type", Fixtures.reportType());

        return vcr.client.report.create(reportParams);
    }

    /**
     * Create a advanced report.
     *
     * @param parameters Map of parameters for create a advanced report.
     * @return Report object
     */
    private static Report createAdvancedReport(Map<String, Object> parameters) throws EasyPostException {
        parameters.put("start_date", Fixtures.reportDate());
        parameters.put("end_date", Fixtures.reportDate());
        parameters.put("type", Fixtures.reportType());

        return vcr.client.report.create(parameters);
    }

    /**
     * Test creating a report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReport() throws EasyPostException {
        vcr.setUpTest("create_report");

        Report report = createBasicReport();

        assertInstanceOf(Report.class, report);
        assertTrue(report.getId().startsWith("shprep_"));
    }

    /**
     * Test creating a report with additional columns.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReportWithAdditionalColumns() throws EasyPostException {
        vcr.setUpTest("create_report_with_additional_columns");

        Map<String, Object> reportWithAdditionalColumnsParams = new HashMap<>();

        List<String> additionalColumns = new ArrayList<>();
        additionalColumns.add("from_name");
        additionalColumns.add("from_company");
        reportWithAdditionalColumnsParams.put("additional_columns", additionalColumns);

        Report reportWithColumns = createAdvancedReport(reportWithAdditionalColumnsParams);

        // verify parameters by checking VCR cassette for correct URL
        // Some reports take a long time to generate, so we won't be able to
        // consistently pull the report
        // There's unfortunately no way to check if the columns
        // were included in the final report without parsing the CSV
        // so we assume, if we haven't gotten an error by this point, we've made the API
        // calls correctly
        // any failure at this point is a server-side issue
        assertInstanceOf(Report.class, reportWithColumns);
    }

    /**
     * Test creating a report with columns.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReportWithColumns() throws EasyPostException {
        vcr.setUpTest("create_report_with_columns");

        Map<String, Object> reportWithAdditionalColumnsParams = new HashMap<>();

        List<String> columns = new ArrayList<>();
        columns.add("usps_zone");
        reportWithAdditionalColumnsParams.put("columns", columns);

        Report reportWithColumns = createAdvancedReport(reportWithAdditionalColumnsParams);

        // verify parameters by checking VCR cassette for correct URL
        // Some reports take a long time to generate, so we won't be able to
        // consistently pull the report
        // There's unfortunately no way to check if the columns
        // were included in the final report without parsing the CSV
        // so we assume, if we haven't gotten an error by this point, we've made the API
        // calls correctly
        // any failure at this point is a server-side issue
        assertInstanceOf(Report.class, reportWithColumns);
    }

    /**
     * Test retrieving shipment report.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveReport() throws EasyPostException {
        vcr.setUpTest("retrieve_report");

        Report report = createBasicReport();

        Report retrievedReport = vcr.client.report.retrieve(report.getId());

        assertInstanceOf(Report.class, retrievedReport);
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
        vcr.setUpTest("all_reports");

        Map<String, Object> params = new HashMap<>();

        params.put("type", Fixtures.reportType());
        params.put("page_size", Fixtures.pageSize());

        ReportCollection reports = vcr.client.report.all(params);

        List<Report> reportsList = reports.getReports();

        assertTrue(reportsList.size() <= Fixtures.pageSize());
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
        vcr.setUpTest("create_report_no_type");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "test");

        // should throw EasyPostException,
        // but might throw NullPointerException due to a bug in the VCR grabbing
        // response content,
        // so we'll just check fo a generic exception
        assertThrows(Exception.class, () -> vcr.client.report.create(params));
    }

    /**
     * Test throwing an error when retrieving all reports with no report type set.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllNoType() throws EasyPostException {
        vcr.setUpTest("all_reports_no_type");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "test");

        // should throw EasyPostException,
        // but might throw NullPointerException due to a bug in the VCR grabbing
        // response content,
        // so we'll just check fo a generic exception
        assertThrows(Exception.class, () -> vcr.client.report.all(params));
    }

    /**
     * Test creating a report without type.
     * 
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateReportWithoutType() throws EasyPostException {
        Map<String, Object> reportParams = new HashMap<>();

        reportParams.put("start_date", Fixtures.reportDate());
        reportParams.put("end_date", Fixtures.reportDate());

        assertThrows(InvalidObjectError.class, () -> vcr.client.report.create(reportParams));
    }
}
