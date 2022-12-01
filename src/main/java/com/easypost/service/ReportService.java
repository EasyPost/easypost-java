package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.exception.General.InvalidParameterError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;
import com.easypost.utils.Utilities;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReportService {
    private final EasyPostClient client;

    /**
     * ReportService constructor.
     * 
     * @param client The client object.
     */
    ReportService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Report from a map of parameters.
     *
     * @param params a map of parameters.
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public Report create(final Map<String, Object> params) throws EasyPostException {
        if (params.containsKey("type")) {
            HashMap<String, Object> paramsWithoutType = new HashMap<>(params);
            paramsWithoutType.remove("type");
            return Requestor.request(RequestMethod.POST,
                    reportURL((String) params.get("type")), paramsWithoutType, Report.class, client);
        } else {
            throw new InvalidObjectError(String.format(Constants.ErrorMessages.MISSING_REQUIRED_PARAMETER, "type"));
        }
    }

    /**
     * Generate a report URL.
     *
     * @param type the type of report to generate.
     * @return the URL to generate the report.
     * @throws EasyPostException when the request fails.
     */
    protected String reportURL(final String type) throws EasyPostException {
        try {
            String urlType = URLEncoder.encode(type, "UTF-8").toLowerCase();
            return String.format("%s/%s/reports/%s/", client.getApiBase(), client.getApiVersion(), urlType);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new InvalidParameterError(String.format(Constants.ErrorMessages.ENCODED_ERROR, "report type"), e);
        }
    }

    /**
     * Retrieve a Report from the API.
     *
     * @param id the ID of the Report to retrieve.
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public Report retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Report.class, id), null, Report.class,
                client);
    }

    /**
     * Get a list of Reports from the API.
     *
     * @param params a map of parameters.
     * @return ReportCollection object.
     * @throws EasyPostException when the request fails.
     */
    public ReportCollection all(final Map<String, Object> params) throws EasyPostException {
        String type = (String) params.get("type");
        return Requestor.request(RequestMethod.GET, reportURL(type), params, ReportCollection.class, client);
    }
}
